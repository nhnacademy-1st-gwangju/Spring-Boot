package com.nhnacademy.edu.springboot.student.domain;

import com.nhnacademy.edu.springboot.student.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NhnAccountServiceTest {

    @Autowired
    NhnAccountService nhnAccountService;

    @Test
    void getAccounts() {
        // when
        List<Account> accounts = nhnAccountService.getAccounts();

        // then
        assertThat(accounts).hasSize(2);
    }
}