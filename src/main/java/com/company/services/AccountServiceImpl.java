package com.company.services;

import com.company.dto.AccountDTO;
import com.company.dto.ClientDTO;
import com.company.persistence.AccountDao;
import com.company.persistence.ClientDao;
import com.company.persistence.EntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountServiceImpl implements AccountService {
    private final static Logger log = LoggerFactory.getLogger(AccountServiceImpl.class.getName());
    private ClientDao clientDao;
    private AccountDao accountDao;

    public AccountServiceImpl(ClientDao clientDao, AccountDao accountDao) {
        this.clientDao = clientDao;
        this.accountDao = accountDao;
    }

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public boolean createAccount(AccountDTO dto, Long clientId) {
        try {
            ClientDTO client = EntityConverter.toDto(clientDao.find(clientId));
            client.addAccount(dto);
            clientDao.update(EntityConverter.toEntity(client));
            return true;
        } catch (Exception e) {
            log.error("Account wasn't created.", e);
            return false;
        }
    }

    @Override
    public boolean deleteAccount(Long accountId) {
        try {
            ClientDTO client = EntityConverter.toDto(clientDao.findByAccountId(accountId));
            client.removeAccount(accountId);
            clientDao.update(EntityConverter.toEntity(client));
            return true;
        } catch (Exception e) {
            log.error("Account wasn't deleted.", e);
            return false;
        }
    }

    @Override
    public boolean changeAccount(AccountDTO account) {
        Long accountId = account.getId();
        try {
            accountDao.update(EntityConverter.toEntity(account, clientDao.findByAccountId(accountId).getId()));
            return true;
        } catch (Exception e) {
            log.error("Account wasn't changed.", e);
            return false;
        }
    }

    @Override
    public AccountDTO findAccount(Long accountId) {
        AccountDTO account = null;
        try {
            account = EntityConverter.toDto(accountDao.find(accountId));
        } catch (Exception e) {
            log.error("Account wasn't found.", e);
        }
        return account;
    }
}