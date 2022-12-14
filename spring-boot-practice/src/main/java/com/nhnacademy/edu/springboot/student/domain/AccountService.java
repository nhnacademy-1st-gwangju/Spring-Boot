package com.nhnacademy.edu.springboot.student.domain;

import com.nhnacademy.edu.springboot.student.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts();
    Account getAccount(Long id);
    Account createAccount(Account account);
    void deleteAccount(Long id);
}
