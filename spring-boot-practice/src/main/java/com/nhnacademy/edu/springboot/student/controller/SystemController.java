package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.config.SystemAuthorProperties;
import com.nhnacademy.edu.springboot.student.config.SystemVersionProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SystemController {

//    @Value("${system.author.name}")
//    private String author;

    private final SystemAuthorProperties systemAuthorProperties;
    private final SystemVersionProperties systemVersionProperties;

    @GetMapping("/system/author")
    public String getAuthor() {
        return "Name : " + systemAuthorProperties.getName();
    }

    @GetMapping("/system/version")
    public String version() {
        return "version : " + systemVersionProperties.getVersion();
    }
}
