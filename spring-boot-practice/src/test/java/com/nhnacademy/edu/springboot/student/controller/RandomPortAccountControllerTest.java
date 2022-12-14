package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.model.Account;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RandomPortAccountControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    void testGetAccounts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Account>> exchange = testRestTemplate.exchange(
                "/accounts",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Account>>() {
                });

        assertThat(exchange.getBody())
                .contains(new Account(1L, "testAccountNumber", 200000));
    }

    @Test
    @Order(2)
    void testGetAccount() {
        ResponseEntity<Account> result = testRestTemplate.getForEntity(
                "/accounts/{id}",
                Account.class,
                1L);

        assertThat(result.getBody())
                .isEqualTo(new Account(1L, "testAccountNumber", 200000));
    }

    @Test
    @Order(3)
    void testCreateAccount() {
        Account account = new Account(100L, "test", 100);
        ResponseEntity<Account> result = testRestTemplate.postForEntity(
                "/accounts",
                account,
                Account.class);

        assertThat(result.getBody())
                .isEqualTo(account);
    }

    @Test
    @Order(4)
    void testDeleteAccount() {
        testRestTemplate.delete(
                "/accounts/{id}",
                100L);
    }
}