package com.netcracker.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/yandex")
public class YandexController {

    @Value("${yandex.client.id}")
    private String clientId;

    @GetMapping("clientId")
    public Map<String, String> getClientId() {
        Map<String, String> resp = new HashMap<>();
        resp.put("clientId", clientId);
        return resp;
    }

    @GetMapping("")
    public String get() {

    }

}
