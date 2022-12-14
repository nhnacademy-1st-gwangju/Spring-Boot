package com.nhn.edu.springboot.starter.application;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    /**
     * TODO (9) @Autowired 로 DoorayHookSender 의존성을 주입합니다.
     */
    @Autowired
    private DoorayHookSender doorayHookSender;


    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


    /**
     * TODO (10) ApplicationReady 일때!, DoorayHookSender 로 Hook 을 발송하는 빈을 선언해 주세요.
     */
    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationListener() {
        return (event) -> {
            doorayHookSender.send(DoorayHook.builder()
                    .botName("")
                    .text("")
                    .build());
        };
    }
}
