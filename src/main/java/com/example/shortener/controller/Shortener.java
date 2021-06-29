package com.example.shortener.controller;

import com.example.shortener.service.ShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

import static com.example.shortener.Entity.ShortenerEntity.buildShortener;

@Slf4j
@RestController
public class Shortener {

    @Autowired
    private ShortenerService shortenerService;

    @GetMapping(value = "/get", produces = "application/json")
    public void get(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("https://www.netflix.com");
    }

    @GetMapping(value = "/generate", produces = "application/json")
    public ResponseEntity<?> generate(@RequestParam String url) {
        if (isValidUrl(url)) {
            return ResponseEntity.ok(shortenerService.save(buildShortener(url)));
        } else {
            return ResponseEntity.badRequest().body("Please enter valid url");
        }
    }

    private boolean isValidUrl(String url) {
        try {
            URL u = new URL(url);
            u.toURI();
            return true;
        } catch (Exception e) {
            log.info("Not a valid URL " + e.getMessage());
            return false;
        }


    }
}
