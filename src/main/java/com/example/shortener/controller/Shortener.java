package com.example.shortener.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
public class Shortener {

    @GetMapping(value="/get", produces = "application/json")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello! World");
    }
}
