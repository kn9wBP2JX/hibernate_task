package com.company;

import com.company.persistence.AccountDao;
import com.company.persistence.AccountDaoImpl;
import com.company.persistence.ClientDao;
import com.company.persistence.ClientDaoImpl;
import com.company.services.AccountService;
import com.company.services.AccountServiceImpl;
import com.company.services.ClientService;
import com.company.services.ClientServiceImpl;
import com.company.view.*;
import com.company.view.io.ConsoleReaderImpl;
import com.company.view.io.ConsoleWriterImpl;
import com.company.view.io.Reader;
import com.company.view.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private final static Logger log = LoggerFactory.getLogger(App.class.getName());

    public static void main(String[] args) {
        log.info("App started.");
        ClientDao clientDao = new ClientDaoImpl();
        AccountDao accountDao = new AccountDaoImpl();
        AccountService accountService = new AccountServiceImpl(clientDao, accountDao);
        ClientService clientService = new ClientServiceImpl(clientDao);
        Writer writer = new ConsoleWriterImpl();
        Reader reader = new ConsoleReaderImpl();
        ClientView clientView = new ClientViewImpl(writer, reader, clientService);
        AccountView accountView = new AccountViewImpl(writer, reader, accountService);
        Menu menu = new Menu(writer, reader, clientView, accountView);
        while (Menu.toContinue) {
            menu.displayMenu();
            menu.tryToSelectMenuItem();
            menu.processSelectedMenuItem();
        }
        log.info("App closed.");
    }
}
