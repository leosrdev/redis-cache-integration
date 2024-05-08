package com.demo.redis.integration.service;

import com.demo.redis.integration.dto.AccountRequest;
import com.demo.redis.integration.dto.AccountResponse;
import com.demo.redis.integration.model.Account;
import com.demo.redis.integration.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    @Cacheable("accounts")
    public AccountResponse create(AccountRequest accountRequest) {
        Account account = mapToEntity(accountRequest);
        Account newAccount = accountRepository.save(account);
        return mapToResponse(newAccount);
    }

    public List<AccountResponse> findAll() {
        Iterable<Account> accounts = accountRepository.findAll();
        List<AccountResponse> list = new ArrayList<>();
        accounts.forEach(account -> list.add(mapToResponse(account)));
        return list;
    }

    private static AccountResponse mapToResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .address(account.getAddress())
                .phone(account.getPhone())
                .build();
    }

    private static Account mapToEntity(AccountRequest accountRequest) {
        return Account.builder()
                .address(accountRequest.getAddress())
                .phone(accountRequest.getPhone())
                .build();
    }
}
