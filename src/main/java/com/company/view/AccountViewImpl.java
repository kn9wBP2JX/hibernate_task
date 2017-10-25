package com.company.view;

import com.company.dto.AccountDTO;
import com.company.services.AccountService;
import com.company.view.io.Reader;
import com.company.view.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AccountViewImpl implements AccountView {
    private final static Logger log = LoggerFactory.getLogger(AccountViewImpl.class.getName());
    private Writer writer;
    private Reader reader;
    private AccountService accountService;

    public AccountViewImpl(Writer writer, Reader reader, AccountService accountService) {
        this.writer = writer;
        this.reader = reader;
        this.accountService = accountService;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void createAccount() throws IOException {
        writer.printLine("Input client id: ");
        Long clientId = reader.readLong();
        writer.printLine("Input login: ");
        String login = reader.readString();
        writer.printLine("Input password: ");
        String password = reader.readString();
        AccountDTO account = new AccountDTO(login, password);
        writer.printLine(accountService.createAccount(account, clientId) ? "Account was created." :
                "Account wasn't created.");
    }

    @Override
    public void updateAccount() throws IOException {
        writer.printLine("Input account id: ");
        Long accountId = reader.readLong();
        AccountDTO account = accountService.findAccount(accountId);
        if (account == null) {
            writer.printLine("Account wasn't found.");
            return;
        }
        writer.printLine("Input new login: ");
        account.setLogin(reader.readString());
        writer.printLine("Input new password: ");
        account.setPassword(reader.readString());
        writer.printLine(accountService.changeAccount(account) ? "Account was updated." : "Account wasn't changed.");
        log.info("AccountDTO updated.");
    }

    @Override
    public void deleteAccount() throws IOException {
        writer.printLine("Input account id: ");
        Long accountId = reader.readLong();
        writer.printLine(accountService.deleteAccount(accountId) ? "Account with id = " + accountId +
                " was deleted." : "Account wasn't deleted. Check id.");
    }
}
