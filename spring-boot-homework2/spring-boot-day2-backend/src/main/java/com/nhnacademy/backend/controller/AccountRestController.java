package com.nhnacademy.backend.controller;

import com.nhnacademy.backend.domain.Account;
import com.nhnacademy.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    // GET /accounts : 전체 조회
    @GetMapping
    public ResponseEntity<List<Account>> findAllAccounts() {
        List<Account> accounts = accountService.getAccounts();
        return ResponseEntity.ok(accounts);
    }

    // GET /accounts/{id} : 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        Account account = accountService.getAccount(id);
        return ResponseEntity.ok(account);
    }

    // POST /accounts : 등록
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Account> registerAccount(@RequestBody Account request) {
        Account account = accountService.createAccount(request);
        return ResponseEntity.ok(account);
    }

    // DELETE /accounts/{id} : 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("delete success - account id : " + id);
    }
}
