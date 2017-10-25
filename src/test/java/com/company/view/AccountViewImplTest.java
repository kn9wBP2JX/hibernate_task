package com.company.view;

import com.company.dto.AccountDTO;
import com.company.services.AccountService;
import com.company.view.io.Reader;
import com.company.view.io.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.*;

public class AccountViewImplTest {
    private Writer writer;
    private Reader reader;
    private AccountService accountService;
    private AccountView accountView;

    @BeforeEach
    void setUp() {
        writer = mock(Writer.class);
        reader = mock(Reader.class);
        accountService = mock(AccountService.class);
        accountView = new AccountViewImpl(writer, reader, accountService);
    }

    @Test
    void createAccount_correctInput_callServiceWithThatInput() throws IOException, ParseException {
        // Given
        AccountDTO accountDTO = new AccountDTO("login1", "passw0rd");
        when(reader.readLong()).thenReturn(1L);
        when(reader.readString()).thenReturn("login1", "passw0rd");
        when(accountService.createAccount(notNull(), eq(1L))).thenReturn(true);
        // When
        accountView.createAccount();
        // Then
        verify(accountService).createAccount(accountDTO, 1L);
        verify(writer).printLine("Account was created.");
    }

    @Test
    void updateAccount_correctInput_callServiceWithThatInput() throws IOException, ParseException {
        // Given
        DateFormat df = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        AccountDTO account = new AccountDTO(1L, "login", "password",
                df.parse("10:15:30 24.10.2017"));
        when(accountService.findAccount(eq(1L))).thenReturn(account);
        when(reader.readLong()).thenReturn(1L);
        when(reader.readString()).thenReturn("NewLogin", "NewPassword");
        account.setLogin("NewLogin");
        account.setPassword("NewPassword");
        when(accountService.changeAccount(account)).thenReturn(true);
        // When
        accountView.updateAccount();
        // Then
        verify(accountService).findAccount(1L);
        verify(accountService).changeAccount(account);
        verify(writer).printLine("Account was updated.");
    }

    @Test
    void updateAccount_badInputAccountId_PrintError() throws IOException, ParseException {
        // Given
        when(reader.readLong()).thenReturn(1L);
        when(accountService.findAccount(eq(1L))).thenReturn(null);
        // When
        accountView.updateAccount();
        // Then
        verify(accountService).findAccount(1L);
        verify(writer).printLine("Account wasn't found.");
    }

    @Test
    void deleteAccount_correctInput_callServiceWithThatInput() throws IOException {
        // Given
        when(reader.readLong()).thenReturn(1L);
        when(accountService.deleteAccount(eq(1L))).thenReturn(true);
        // When
        accountView.deleteAccount();
        // Then
        verify(accountService).deleteAccount(1L);
        verify(writer).printLine("Account with id = " + 1 + " was deleted.");
    }

    @Test
    void deleteAccount_badInputAccountId_PrintError() throws IOException {
        // Given
        when(reader.readLong()).thenReturn(1L);
        when(accountService.deleteAccount(eq(1L))).thenReturn(false);
        // When
        accountView.deleteAccount();
        // Then
        verify(accountService).deleteAccount(1L);
        verify(writer).printLine("Account wasn't deleted. Check id.");
    }
}