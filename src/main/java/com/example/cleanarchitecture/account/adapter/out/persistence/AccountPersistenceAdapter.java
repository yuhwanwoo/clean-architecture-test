package com.example.cleanarchitecture.account.adapter.out.persistence;

import com.example.cleanarchitecture.account.application.port.out.LoadAccountPort;
import com.example.cleanarchitecture.account.application.port.out.UpdateAccountStatePort;
import com.example.cleanarchitecture.account.domain.Account;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {
    @Override
    public Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate) {
        return null;
    }
}
