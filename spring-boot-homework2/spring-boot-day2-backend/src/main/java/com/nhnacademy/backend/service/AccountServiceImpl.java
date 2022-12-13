package com.nhnacademy.backend.service;

import com.nhnacademy.backend.domain.Account;
import com.nhnacademy.backend.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAccounts() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("no account of id : " + id));
    }

    @Override
    public Account createAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        repository.deleteById(id);
    }
}
