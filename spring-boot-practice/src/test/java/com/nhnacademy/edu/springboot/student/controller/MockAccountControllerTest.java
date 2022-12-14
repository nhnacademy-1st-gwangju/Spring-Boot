package com.nhnacademy.edu.springboot.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.student.domain.AccountService;
import com.nhnacademy.edu.springboot.student.model.Account;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class MockAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Order(1)
    void testGetAccounts() throws Exception {
        given(accountService.getAccounts()).willReturn(List.of(new Account(1L, "1", 100)));

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].number", equalTo("1")));
    }

    @Test
    @Order(2)
    void testGetAccount() throws Exception {
        long id = 1L;
        given(accountService.getAccount(id)).willReturn(new Account(id, "1", 100));

        mockMvc.perform(get("/accounts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", equalTo("1")));
    }

    @Test
    @Order(3)
    void testCreateAccount() throws Exception {
        Account account = new Account(1L, "1", 100);

        given(accountService.createAccount(account)).willReturn(account);

        mockMvc.perform(post("/accounts")
                .content(objectMapper.writeValueAsString(account))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", equalTo("1")));

    }

    @Test
    @Order(4)
    void testDeleteAccount() throws Exception {
        willDoNothing().given(accountService).deleteAccount(1L);

        mockMvc.perform(delete("/accounts/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }
}