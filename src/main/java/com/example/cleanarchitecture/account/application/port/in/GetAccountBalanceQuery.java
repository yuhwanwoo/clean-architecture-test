package com.example.cleanarchitecture.account.application.port.in;

import com.example.cleanarchitecture.account.domain.Account.AccountId;
import com.example.cleanarchitecture.account.domain.Money;

public interface GetAccountBalanceQuery {
    Money getAccountBalance(AccountId accountId);
}
