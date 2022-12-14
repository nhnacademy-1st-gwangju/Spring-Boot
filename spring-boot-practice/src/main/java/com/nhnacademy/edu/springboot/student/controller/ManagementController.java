package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.configuration.actuator.health.CustomHealthIndicator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
@RequiredArgsConstructor
public class ManagementController {

    private final CustomHealthIndicator indicator;

    @PostMapping("/fail")
    public String fail() {
        indicator.setUp(false);
        return "OK";
    }

    @PostMapping("/success")
    public String success() {
        indicator.setUp(true);
        return "OK";
    }
}
