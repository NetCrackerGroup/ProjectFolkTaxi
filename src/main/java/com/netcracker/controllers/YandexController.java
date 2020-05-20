package com.netcracker.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/yandex")
public class YandexController {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleController.class);

    @Value("${yandex.client.id}")
    private String clientID;


    @Value("${back.url}")
    private String baseUrl;

    @Value("${yandex.redirect}")
    private String redirect;

    @RequestMapping("/callback")
    public void callback() {
    }

    @GetMapping("clientId")
    public  Map<String, String> getClient() {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("clientId", clientID);
        maps.put("redirect", redirect);
        return maps;
    }

    @GetMapping("/paramet")
    public Map<String, String> getParametrs() {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("clientId", clientID);
        maps.put("scope", "account-info");

        return maps;
    }
}
