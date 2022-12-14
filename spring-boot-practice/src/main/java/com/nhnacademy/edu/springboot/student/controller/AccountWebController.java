package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.domain.AccountService;
import com.nhnacademy.edu.springboot.student.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AccountWebController {

    private final AccountService accountService;

    @GetMapping("/web/accounts/{id}")
    public String getStudent(@PathVariable Long id,
                             Model model){
        Account account = accountService.getAccount(id);
        model.addAttribute("account", account);
        return "account";
    }
}
