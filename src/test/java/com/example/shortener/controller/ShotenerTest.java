package com.example.shortener.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;


@ExtendWith(MockitoExtension.class)
public class ShotenerTest {

    @InjectMocks
    private Shortener shortener;


    @Test
    void should_return_response_200_with_response_hello_world() throws IOException {

    }
}
