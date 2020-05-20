package com.netcracker.controllers;

import com.netcracker.services.YandexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Controller
public class YandexMoneyController {

    private static final Logger LOG = LoggerFactory.getLogger(YandexMoneyController.class);

    @Autowired
    private YandexService yandexService;

    @GetMapping("/yandex/callback")
    public void callbackSuccess(
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "error_description", required = false) String description,
            final HttpServletResponse resp
            ) throws IOException {
        LOG.debug("callbackSuccess");
        LOG.debug("Code : {}", code);
        if (Objects.nonNull(code)) {
            yandexService.getToken(code);
        }

        //resp.sendRedirect("http://localhost:4200");
    }

    @PostMapping("/yandex/callback")
    public void callbackPost() {
    }
}
