package com.example.cleanarchitecture.account.application.service;

import com.example.cleanarchitecture.account.application.port.out.AccountLock;
import com.example.cleanarchitecture.account.domain.Account;
import org.springframework.stereotype.Component;

@Component
public class NoOpAccountLock implements AccountLock {
    @Override
    public void lockAccount(Account.AccountId accountId) {

    }

    @Override
    public void releaseAccount(Account.AccountId accountId) {

    }
}
