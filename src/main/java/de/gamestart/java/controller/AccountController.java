package de.gamestart.java.controller;

import de.gamestart.java.data.Account;
import de.gamestart.java.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestParam String userName, @RequestParam String passwordHash) {
        Optional<Account> account = accountRepository.findByUserName(userName).stream().findFirst();
        if(account.isPresent()) {
            Account foundAccount = account.get();
            if(foundAccount.passwordHash.equals(passwordHash)) {
                return ResponseEntity.ok(foundAccount.accessToken);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String userName,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String passwordHash) {
        if(accountRepository.findByUserName(userName).size() > 0) {
            return ResponseEntity.badRequest().build();
        }

        Account account = new Account(userName, firstName, lastName passwordHash);

        accountRepository.save(account);
        return ResponseEntity.ok(account.accessToken);
    }
}
