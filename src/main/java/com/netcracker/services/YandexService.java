package com.netcracker.services;

import com.google.common.collect.HashMultimap;
import com.netcracker.CustomException.*;
import com.netcracker.DTO.InfoToThankPassengerDTO;
import com.netcracker.DTO.YandexAccessTokenDTO;
import com.netcracker.entities.Journey;
import com.netcracker.entities.PaidJourney;
import com.netcracker.entities.Route;
import com.netcracker.repositories.RouteRepository;
import com.netcracker.services.Channels.ApplicationSenderService;
import com.netcracker.services.Channels.EmailServiceImpl;
import com.netcracker.services.Channels.FillInfoContent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.netcracker.entities.User;

import java.util.*;

@Service
public class YandexService {

    private static final Logger LOG = LoggerFactory.getLogger(YandexService.class);

    @Value("${yandex.client.id}")
    private String clientID;

    @Value("${yandex.redirect}")
    private String redirect;

    @Value("${yandex.client.secret}")
    private String secret;

    @Value("${baseUrl}")
    private String baseUrl;

    @Value("${frontUrl}")
    private String frontUrl;

    private RouteService routeService;
    private RouteRepository routeRepository;
    private NotificationService notificationService;
    private AuthUserComponent authUserComponent;
    private InfoContentService infoContentService;
    private ApplicationSenderService applicationSenderService;
    private UsersService usersService;
    private EmailServiceImpl emailService;
    private PaidJourneyService paidJourneyService;
    private JourneyService journeyService;

    @Autowired
    public YandexService(
            RouteService routeService,
            RouteRepository routeRepository,
            AuthUserComponent authUserComponent,
            NotificationService notificationService,
            InfoContentService infoContentService,
            ApplicationSenderService applicationSenderService,
            UsersService usersService,
            EmailServiceImpl emailService,
            PaidJourneyService paidJourneyService,
            JourneyService journeyService
    ) {
        this.routeService = routeService;
        this.routeRepository = routeRepository;
        this.notificationService = notificationService;
        this.authUserComponent = authUserComponent;
        this.infoContentService = infoContentService;
        this.applicationSenderService = applicationSenderService;
        this.usersService = usersService;
        this.emailService = emailService;
        this.paidJourneyService = paidJourneyService;
        this.journeyService = journeyService;
    }

    public boolean validYandexPurse() {
        return !Objects.isNull(authUserComponent.getUser().getYandexAccount());
    }

    public void thanksPassengerForJourney(InfoToThankPassengerDTO infoToThankPassengerDTO) throws Exception {
        LOG.debug("info : {}",infoToThankPassengerDTO );
        User user = usersService.getUser(infoToThankPassengerDTO.getUserID());
        LOG.debug("user : {}", user);
        Journey journey = journeyService.getJourney(infoToThankPassengerDTO.getJourneyID());
        LOG.debug("journey : {}", journey);
        PaidJourney paidJourney = paidJourneyService.getPaidJourney(user, journey);
        LOG.debug("paidJourney : {}", paidJourney);

        Route currentRoute = journey.getRoute();
        LOG.debug("currentRoute : {}" , currentRoute);

        Optional<String> optionalAccessToken = getToken(infoToThankPassengerDTO.getCode());
        if (!optionalAccessToken.isPresent())
            throw new FailureAccessToken();

        String accessToken = optionalAccessToken.get();

        MultiValueMap<String, Object> maps = new LinkedMultiValueMap<String, Object>();
        maps.put("pattern_id", Collections.singletonList("p2p"));
        maps.put("to", Collections.singletonList(currentRoute.getDriverId().getYandexAccount().toString()));
        maps.put("amount_due", Collections.singletonList(infoToThankPassengerDTO.getPrice()));
        maps.put("message", Collections.singletonList(String.format("%s поблагодарил за поездку!", user.getEmail())));
        maps.put("comment", Collections.singletonList("Оплата поездки в Folk Taxi"));

        LOG.debug("{}", maps);

        LOG.debug("request payment");

        requestPayment(maps, accessToken);

        paidJourneyService.changeStatusPaidJourney(paidJourney);

        driverNotify(currentRoute.getDriverId(), infoToThankPassengerDTO.getPrice());
    }

    public void driverNotify(User user, double price) throws Exception {
        Map<String, String> infoMaps = new HashMap<>();
        infoMaps.put("username", user.getFio());
        infoMaps.put("price", String.valueOf(price));
        FillInfoContent fillInfoContent = new FillInfoContent(new HashMap<>());
        notificationService.notify(
                infoContentService.getInfoContentByKey("thank_driver"),
                applicationSenderService,
                user,
                fillInfoContent
        );
    }

    public HttpHeaders generateHttpHeaders( HttpHeaders headers, String accessToken) {
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    public void requestPayment(MultiValueMap<String, Object> maps, String accessToken) throws ParseException, FailureRequestPayment, PayFailure {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://money.yandex.ru/api/request-payment";
        LOG.debug("url : {}", url);
        HttpHeaders headers = generateHttpHeaders(new HttpHeaders(), accessToken);

        HttpEntity<MultiValueMap<String, Object>> body = new HttpEntity<>(maps, headers);
        ResponseEntity<String> resp = restTemplate.postForEntity(url, body, String.class);

        String requestId = handleResponseRequestPayment(resp);

        MultiValueMap<String, Object> bodyProccessPayment = new LinkedMultiValueMap<>();
        bodyProccessPayment.put("request_id", Collections.singletonList(requestId));

        proccessPayment(new HttpEntity<>(bodyProccessPayment, headers));
    }

    public void proccessPayment(HttpEntity<MultiValueMap<String, Object>> body) throws PayFailure, ParseException, FailureRequestPayment {
        String url = "https://money.yandex.ru/api/process-payment";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> resp = restTemplate.postForEntity(url, body, String.class);

        LOG.debug("resp status : {}", resp.getStatusCode());
        LOG.debug("proccess body : {} " , resp.getBody());

        handleResponseProcessPayment(resp);
    }

    public void handleResponseProcessPayment(ResponseEntity<String> resp ) throws ParseException, FailureRequestPayment {
        LOG.debug("response request payment status : {}", resp.getStatusCode());
        LOG.debug("response request payment body : {}", resp.getBody());
        if (resp.getStatusCode() != HttpStatus.OK)
            throw new FailureRequestPayment();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(resp.getBody());
        String status = (String) jsonObject.get("status");
        LOG.debug("status : {}", status);
        if (!status.equals("success"))
            throw new FailureRequestPayment();
    }

    public String handleResponseRequestPayment(ResponseEntity<String> resp) throws ParseException, FailureRequestPayment {
        LOG.debug("response request payment status : {}", resp.getStatusCode());
        LOG.debug("response request payment body : {}", resp.getBody());
        if (resp.getStatusCode() != HttpStatus.OK)
            throw new FailureRequestPayment();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(resp.getBody());
        String status = (String) jsonObject.get("status");
        LOG.debug("status : {}", status);
        if (!status.equals("success"))
            throw new FailureRequestPayment();
        return (String) jsonObject.get("request_id");
    }

    public void connectYandexPurse(String code) throws Exception {
        LOG.debug("start method");
        Optional<String> optionalAccessToken = getToken(code);
        LOG.debug("connectYandexPurse");
        if (!optionalAccessToken.isPresent()) {
            throw new FailureAccessToken();
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://money.yandex.ru/api/account-info";
        String accessToken = optionalAccessToken.get();
        LOG.debug("url : {}", url);

        MultiValueMap<String, String> maps = new LinkedMultiValueMap<String, String>();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(maps, headers);
        ResponseEntity<String> resp = restTemplate.postForEntity(url, body, String.class);

        LOG.debug("Info response : {}", resp);
        LOG.debug("status : {}", resp.getStatusCode());
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(resp.getBody());
        String account = (String) jsonObject.get("account");
        User user = usersService.addingYandexPurse(account);

        FillInfoContent fillInfoContent = new FillInfoContent(new HashMap<>());
        notificationService.notify(
                infoContentService.getInfoContentByKey("added_cash_to_user"),
                applicationSenderService,
                user,
                fillInfoContent
        );
    }

    /**
     * Метод добавляет в список пассажиров, которые совершили поездку. Этот список будет фиксировать
     * насколько пассажир чистен перед водителем
     * @param currentRoute
     * @param journey
     * @throws Exception
     */
    public void thanksPasseger(Route currentRoute, Journey journey) throws Exception {
        if ( Objects.isNull(currentRoute.getDriverId().getYandexAccount())) {
            LOG.debug("Driver Not have yandex account");
            return;
        }
        for (User user : currentRoute.getUsers()) {
            if (user.equals(currentRoute.getDriverId()))
                continue;
            paidJourneyService.addingUserInPaidJourneys(user, journey);
            Map<String, String> map = new HashMap<>();
            map.put("url", frontUrl + String.format("/thanks?journeyId=%d&userId=%d", journey.getJourneyId(), user.getUserId()));
            FillInfoContent fillInfoContent = new FillInfoContent(map);
            notificationService.notify(
                    infoContentService.getInfoContentByKey("passenger_thanks"),
                    emailService,
                    user,
                    fillInfoContent);
        }
    }

    public void payRoute(String code, Long routeId) throws FailureAccessToken, PayFailure, Exception
    {
        LOG.debug("Method pauRoute");
        Optional<String> optionalAccessToken = getToken(code);
        LOG.debug("end access token");
        if (optionalAccessToken.isPresent()) {
            LOG.debug("Optional not empty");
            String accessToken = optionalAccessToken.get();
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://money.yandex.ru/api/request-payment";
            LOG.debug("url : {}", url);

            User user = authUserComponent.getUser();
            Route route = routeRepository.findRouteByRouteId(routeId);

            MultiValueMap<String, Object> maps = new LinkedMultiValueMap<String, Object>();
            maps.put("pattern_id", Collections.singletonList("p2p"));
            maps.put("to", Collections.singletonList(route.getAccountNumber().toString()));
            maps.put("amount_due", Collections.singletonList(route.getPrice()));
            maps.put("message", Collections.singletonList(String.format("%s оплатил поездку!", user.getEmail())));
            maps.put("comment", Collections.singletonList("Оплата поездки в Folk Taxi"));

            LOG.debug("Body : {}" , maps);

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            LOG.debug("Send request");
            LOG.debug("headers : {}", headers);

            HttpEntity<MultiValueMap<String, Object>> body = new HttpEntity<>(maps, headers);
            ResponseEntity<String> resp = restTemplate.postForEntity(url, body, String.class);

            LOG.debug("Response : {}", resp);
            LOG.debug("Response status code : {}", resp.getStatusCode());

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(resp.getBody());
            String status = (String) jsonObject.get("status");

            if (status.equals("success")) {
                String requestId = (String) jsonObject.get("request_id");
                url = "https://money.yandex.ru/api/process-payment";

                maps = new LinkedMultiValueMap<>();
                maps.put("request_id", Collections.singletonList(requestId));

                body = new HttpEntity<>(maps, headers);

                resp = restTemplate.postForEntity(url, body, String.class, new ParameterizedTypeReference<List<String>>() { });

                LOG.debug("Response : {}", resp);
                LOG.debug("Response status code : {}", resp.getStatusCode());

                jsonParser = new JSONParser();
                jsonObject = (JSONObject) jsonParser.parse(resp.getBody());
                String stat = (String) jsonObject.get("status");

                if (stat.equals("success")) {
                    Map<String, String> infoMaps = new HashMap<>();
                    infoMaps.put("username", user.getFio());
                    FillInfoContent fillInfoContent = new FillInfoContent(infoMaps);
                    notificationService.notify(
                            infoContentService.getInfoContentByKey("user_pay_route"),
                            applicationSenderService,
                            user,
                            fillInfoContent
                    );
                }
            }
            else {
                throw new PayFailure();
            }
        }
        else {
            throw new FailureAccessToken();
        }
    }

    public void connectCash(String code, Long routeId) throws Exception {
        Optional<String> optionalAccessToken = getToken(code);
        LOG.debug("connect Cash");
        if (optionalAccessToken.isPresent()) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://money.yandex.ru/api/account-info";
            String accessToken = optionalAccessToken.get();
            LOG.debug("url : {}", url);

            MultiValueMap<String, String> maps = new LinkedMultiValueMap<String, String>();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(maps, headers);
            ResponseEntity<String> resp = restTemplate.postForEntity(url, body, String.class);

            LOG.debug("Info response : {}", resp);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(resp.getBody());
            String account = (String) jsonObject.get("account");

            LOG.debug("account : {}", account);

            if (Objects.nonNull(account)) {
                Route route = routeService.getRoutesBy(routeId);
                route.setAccountNumber(Long.decode(account));
                routeRepository.save(route);
                User user = authUserComponent.getUser();
                FillInfoContent fillInfoContent = new FillInfoContent(new HashMap<>());
                notificationService.notify(
                    infoContentService.getInfoContentByKey("added_cash_to_route"),
                        applicationSenderService,
                        user,
                        fillInfoContent
                );
            }
        }
        else {

        }
    }

    public Optional<String> getToken(String code) {
        String url = "https://money.yandex.ru/oauth/token";
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> maps = new LinkedMultiValueMap<String, String>();
        maps.put("client_id", Collections.singletonList(clientID));
        maps.put("client_secret", Collections.singletonList(secret));
        maps.put("grant_type", Collections.singletonList("authorization_code"));
        maps.put("redirect_uri", Collections.singletonList(redirect));
        maps.put("code", Collections.singletonList(code));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(maps, headers);
        ResponseEntity<YandexAccessTokenDTO> resp = restTemplate.postForEntity(url, body, YandexAccessTokenDTO.class);

        LOG.debug("Resp code : {}", resp.getStatusCode());
        LOG.debug("Resp body : {}", resp.getBody());
        LOG.debug("Resp headers : {}", resp.getHeaders());
        if (resp.getStatusCode() == HttpStatus.OK) {
            LOG.debug(Objects.requireNonNull(resp.getBody()).toString());
            return Optional.of(resp.getBody().access_token);
        }
        return Optional.empty();
    }
}