package com.example.shortener.controller;

import com.example.shortener.Entity.ShortenerEntity;
import com.example.shortener.service.ShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static com.example.shortener.Entity.ShortenerEntity.buildShortener;

@Slf4j
@RestController
public class Shortener {

    @Autowired
    private ShortenerService shortenerService;


    @GetMapping(value = "/generate", produces = "application/json")
    public ResponseEntity<String> generate(@RequestParam String url) {
        if (isValidUrl(url)) {
            log.info("generating shortened URL");
            ShortenerEntity shortenerEntity = shortenerService.save(buildShortener(url));
            String shortenedUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(shortenerEntity.getShortenerKey()).toUriString();
            return ResponseEntity.ok(shortenedUrl);
        } else {
            log.warn("Entered Malformed URL: {}", url);
            return ResponseEntity.badRequest().body("Please enter valid url");
        }
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<List<ShortenerEntity>> getAll() {
        log.info("Getting List of all Shortener URL's");
        return ResponseEntity.ok(shortenerService.getAll());
    }

    @GetMapping(value = "/{urlKey}", produces = "application/json")
    public void redirect(@PathVariable String urlKey, HttpServletResponse resp) throws Exception {
        Optional<ShortenerEntity> entity = shortenerService.get(urlKey);
        if (entity.isPresent()) {
            log.info("key {} is present", urlKey);
            resp.sendRedirect(entity.get().getActualUrl());
            log.info("Successfully Redirected to URL: {} ", entity.get().getActualUrl());
        } else {
            log.warn("shortener key {} not present", urlKey);
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
