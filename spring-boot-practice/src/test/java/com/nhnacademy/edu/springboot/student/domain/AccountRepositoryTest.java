package com.nhnacademy.edu.springboot.student.domain;

import com.nhnacademy.edu.springboot.student.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void testAccountRepository() throws Exception {
        //given
        Account account = new Account(1L, "testAccountNumber", 200000);
        accountRepository.save(account);

        //when
        Optional<Account> actual = accountRepository.findById(1L);

        //then
        assertThat(actual).isPresent();
        assertThat(actual.get()).isEqualTo(account);
    }
    
    @Test
    void test() throws Exception {
        //given
        Account account = new Account(1L, "testAccountNumber", 200000);
        accountRepository.save(account);
        
        //when
        accountRepository.deleteById(1L);

        Optional<Account> optionalAccount = accountRepository.findById(1L);

        //then
        assertThat(optionalAccount).isEmpty();
    }
}