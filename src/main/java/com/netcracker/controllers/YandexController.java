package com.netcracker.controllers;

import com.netcracker.CustomException.*;
import com.netcracker.DTO.CodeDTO;
import com.netcracker.DTO.InfoToThankPassengerDTO;
import com.netcracker.DTO.responses.StatusResponse;
import com.netcracker.entities.Route;
import com.netcracker.services.PaidJourneyService;
import com.netcracker.services.RouteService;
import com.netcracker.services.YandexService;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/yandex")
public class YandexController {
    private static final Logger LOG = LoggerFactory.getLogger(YandexController.class);

    private YandexService yandexService;
    private RouteService routeService;
    private PaidJourneyService paidJourneyService;

    @Autowired
    public YandexController(YandexService yandexService,
                            RouteService routeService,
                            PaidJourneyService paidJourneyService) {
        this.yandexService = yandexService;
        this.routeService = routeService;
        this.paidJourneyService = paidJourneyService;
    }

    @Value("${yandex.client.id}")
    private String clientID;

    @Value("${back.url}")
    private String baseUrl;

    @Value("${yandex.redirect}")
    private String redirect;

    @Value("https://money.yandex.ru/oauth/token")
    private String tokenUri;

    @RequestMapping("/callback")
    public void callback() {
    }


    @GetMapping("clientId")
    public  Map<String, String> getClient(@RequestParam(name = "who") String whoUser) {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("clientId", clientID);
        maps.put("redirect", redirect);
        if (whoUser.equals("driver")) {
            maps.put("scope", "account-info");
        }
        if (whoUser.equals("passenger")) {
            maps.put("scope", "payment-p2p");
        }
        return maps;
    }

    @GetMapping("/accessToken")
    public Map<String, String> oauhToken(@RequestParam(name = "code") String code) {
        LOG.debug("oathToken");

        yandexService.getToken(code);

        Map<String, String> maps = new HashMap<>();
        return maps;
    }

    @GetMapping("/route/cash/{id}")
    public StatusResponse checkConnectCash(@PathVariable(name = "id") Long id) {
        Route route = routeService.getRoutesBy(id);
        LOG.debug("route : {}", route);
        LOG.debug("route cash {}", route.getAccountNumber());

        if (route.getAccountNumber() == null )
            return new StatusResponse("failure");
        else
            return new StatusResponse("success");
    }

    @PutMapping("/connectCash")
    public StatusResponse connectCash(@RequestParam(name = "code") String code,
                                      @RequestParam(name = "routeId") Long routeId ) {
        LOG.debug("connect Cash!");
        try {
            yandexService.connectCash(code, routeId);
            return new StatusResponse("success");
        }
        catch (ParseException ex) {
            LOG.debug("Parser Error!");
            return new StatusResponse("failure");
        } catch (Exception e) {
            e.printStackTrace();
            return new StatusResponse("failure");
        }
    }

    @PostMapping("/pay/route/{id}")
    public StatusResponse payRoute(
            @PathVariable(name = "id") Long routeId,
            @RequestParam(name = "code") String code
    )
    {
        LOG.debug("Route id : {}", routeId);
        LOG.debug("code : {}", code);

        try {
            yandexService.payRoute(code, routeId);
            return new StatusResponse("success");
        } catch (Exception ex) {
            LOG.error(" Eroror " , ex);
            LOG.debug("{}", ex.toString());
            LOG.debug("Exception : {}", ex.getMessage());
            LOG.debug("failure request");
            return new StatusResponse("failure");
        }
    }

    @GetMapping("check/connect/purse")
    public StatusResponse getConnectYandexPurse() {
        if ( yandexService.validYandexPurse())
            return new StatusResponse("success");
        else
            return new StatusResponse("failure");
    }

    @PostMapping("thank/driver")
    public StatusResponse passengerThankDriver(
            @RequestBody InfoToThankPassengerDTO infoToThankPassengerDTO
    ) {
        try {
            yandexService.thanksPassengerForJourney(infoToThankPassengerDTO);
            return new StatusResponse("success");
        } catch (NotFoundById | JourneyNotFound | FailureAccessToken | ParseException | FailureRequestPayment | PayFailure notFoundById) {
            LOG.error("error : {}", notFoundById.getMessage(), notFoundById);
            return new StatusResponse("failure");
        } catch (Exception e) {
            e.printStackTrace();
            return new StatusResponse("failure");
        }
    }

    @PutMapping("/purse")
    public StatusResponse connectYandexPurse(@RequestParam(name = "code") String code) {
        LOG.debug("connect Cash!");
        try {
            yandexService.connectYandexPurse(code);
            return new StatusResponse("success");
        }
        catch (FailureAccessToken | NumberFormatException | YandexAccountFailure ex) {
            LOG.error("error", ex);
            return new StatusResponse("failure");
        }
        catch (ParseException ex) {
            LOG.debug("Parser Error!");
            return new StatusResponse("failure");
        } catch (Exception e) {
            e.printStackTrace();
            return new StatusResponse("failure");
        }
    }
}