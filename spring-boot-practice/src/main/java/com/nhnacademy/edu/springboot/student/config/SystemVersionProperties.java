package com.nhnacademy.edu.springboot.student.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "com.nhn.account.system")
public class SystemVersionProperties {
    private String version;
}
