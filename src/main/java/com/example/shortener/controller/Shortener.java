package com.example.shortener.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/v1")
public class Shortener {

    @GetMapping(value = "/get", produces = "application/json")
    public void get(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("https://www.netflix.com");
    }
}
