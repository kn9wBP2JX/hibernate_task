package com.company.services;

import com.company.dto.AccountDTO;

public interface AccountService {
    boolean createAccount(AccountDTO dto, Long clientId);

    boolean deleteAccount(Long accountId);

    boolean changeAccount(AccountDTO account);

    AccountDTO findAccount(Long accountId);
}