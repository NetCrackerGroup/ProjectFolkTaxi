package com.netcracker.services;

import com.netcracker.DTO.YandexAccessTokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
        }

        return Optional.empty();
    }
}