package com.example.cleanarchitecture.account.adapter.out.persistence;

import com.example.cleanarchitecture.account.application.port.out.LoadAccountPort;
import com.example.cleanarchitecture.account.application.port.out.UpdateAccountStatePort;
import com.example.cleanarchitecture.account.domain.Account;
import com.example.cleanarchitecture.account.domain.Activity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {
    private final SpringDataAccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;
    @Override
    public Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate) {
        AccountJpaEntity account = accountRepository.findById(accountId.getValue())
                .orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activities = activityRepository.findByOwnerSince(
                accountId.getValue(),
                baselineDate
        );

        Long withdrawBalance = orZero(activityRepository
                .getWithdrawalBalanceUntil(
                        accountId.getValue(),
                        baselineDate
                )
        );

        Long depositBalance = orZero(activityRepository
                .getDepositBalanceUntil(
                        accountId.getValue(),
                        baselineDate
                )
        );

        return accountMapper.mapToDomainEntity(
                account,
                activities,
                withdrawBalance,
                depositBalance
        );
    }

    private Long orZero(Long value) {
        return value == null ? 0L : value;
    }

    @Override
    public void updateActivities(Account account) {
        for (Activity activity : account.getActivityWindow().getActivities()) {
            if (activity.getId() == null) {
                activityRepository.save(accountMapper.mapToJpaEntity(activity));
            }
        }
    }
}
