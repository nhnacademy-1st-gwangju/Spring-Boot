package com.nhnacademy.edu.springboot.student.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "system.author")
public class SystemAuthorProperties {
    private String name;
}
