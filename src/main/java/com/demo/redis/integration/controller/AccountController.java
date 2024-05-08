package com.demo.redis.integration.controller;

import com.demo.redis.integration.dto.AccountRequest;
import com.demo.redis.integration.dto.AccountResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.redis.integration.service.AccountService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody AccountRequest accountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(accountRequest));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll());
    }
}
