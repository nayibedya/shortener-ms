package com.example.shortener.controller;

import com.example.shortener.Entity.ShortenerEntity;
import com.example.shortener.RequestEntity.GenerateRequest;
import com.example.shortener.service.ShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@CrossOrigin("*")
public class Shortener {

    @Autowired
    private ShortenerService shortenerService;


    @PostMapping(value = "/generate", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> generate(@RequestBody GenerateRequest generateRequest) {
        if (isValidUrl(generateRequest.getUrl())) {
            log.info("generating shortened URL");
            ShortenerEntity shortenerEntity = shortenerService.save(buildShortener(generateRequest.getUrl()));
            String shortenedUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(shortenerEntity.getShortenerKey()).toUriString();
            return ResponseEntity.ok(shortenedUrl);
        } else {
            log.warn("Entered Malformed URL: {}", generateRequest.getUrl());
            return ResponseEntity.ok("Please enter valid url");
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
            shortenerService.save(entity.get().buildShortenerSave());
            log.info("Number of times the url used : {}", entity.get().getCounter());
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
