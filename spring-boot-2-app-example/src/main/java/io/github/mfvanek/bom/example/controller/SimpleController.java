package io.github.mfvanek.bom.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/")
public class SimpleController {

    @GetMapping("/test")
    public LocalDateTime test() {
        return LocalDateTime.now();
    }
}
