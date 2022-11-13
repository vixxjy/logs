package com.example.logistics.controller;

import com.example.logistics.Repository.AccountRepository;
import com.example.logistics.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    @RolesAllowed({"Admin", "Founder"})
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @PostMapping
    @RolesAllowed("Admin")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountRepository.save(account);
        URI accountUrl = URI.create("/account/" + savedAccount.getId());

        return ResponseEntity.created(accountUrl).body(savedAccount);
    }
}
