package com.nhn.edu.springboot.starter;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * TODO (2)
 * 1) DoorayHookSender.class 가 Classpath 에 존재할때,
 * 2) dooray.hook-url 속성이 정의되어 있을때,
 * 설정 내용이 동작하도록 수정 하세요.
 */

@Configuration
@ConditionalOnClass(DoorayHookSender.class)
@ConditionalOnProperty("dooray.hook-url")
@EnableConfigurationProperties(DoorayProperties.class)
public class DoorayAutoConfiguration {

    @Autowired
    private DoorayProperties doorayProperties;

    /**
     * TODO (3) RestTemplate type의 빈이 선언되어 있지 않으면 RestTemplate Bean을 생성하도록 @ConditionalOnMissingBean 을 이용해서 코드를 작성하세요.
     */
    @ConditionalOnMissingBean(RestTemplate.class)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * TODO (4) DoorayHookSender Bean 이 생성되도록 코드를 작성해 주세요. DoorayHookSender 두번째 인자는 doorayProperties.getHookUrl() 입니다.
     */
    @Bean
    public DoorayHookSender doorayHookSender(RestTemplate restTemplate) {
        return new DoorayHookSender(restTemplate, doorayProperties.getHookUrl());
    }

}
