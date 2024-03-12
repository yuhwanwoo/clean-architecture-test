package com.example.cleanarchitecture.account.application.port.out;

import com.example.cleanarchitecture.account.domain.Account;

public interface AccountLock {
    void lockAccount(Account.AccountId accountId);

    void releaseAccount(Account.AccountId accountId);
}
