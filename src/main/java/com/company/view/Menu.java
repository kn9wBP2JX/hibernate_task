package com.company.view;

import com.company.utils.HibernateUtil;
import com.company.view.io.Reader;
import com.company.view.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class Menu {
    private static final Logger log = LoggerFactory.getLogger(Menu.class.getName());
    public static boolean toContinue = true;
    private static int selection = 0;
    private static int MAX_NUMBER_ITEM = 7;
    private static int MIN_NUMBER_ITEM = 1;
    private Writer writer;
    private Reader reader;
    private ClientView clientView;
    private AccountView accountView;

    public Menu() {
    }

    public Menu(Writer writer, Reader reader, ClientView clientView, AccountView accountView) {
        this.writer = writer;
        this.reader = reader;
        this.clientView = clientView;
        this.accountView = accountView;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public void setAccountView(AccountView accountView) {
        this.accountView = accountView;
    }

    public void displayMenu() {
        writer.printLine("1) Create client.");
        writer.printLine("2) Delete client.");
        writer.printLine("3) Create account.");
        writer.printLine("4) Change account.");
        writer.printLine("5) Delete account.");
        writer.printLine("6) Display list of clients and their accounts.");
        writer.printLine("7) EXIT.");
    }

    public void tryToSelectMenuItem() {
        try {
            selection = inputMenuItemNumber(MIN_NUMBER_ITEM, MAX_NUMBER_ITEM);
        } catch (Exception e) {
            log.error("Error in input.", e);
        }
    }

    public int inputMenuItemNumber(int min, int max) throws IOException {
        int selection = 0;
        while (selection < min || selection > max) {
            writer.printLine("To continue, enter the menu item number: ");
            log.info("The wrong menu item number was entered.");
            selection = reader.readInteger();
        }
        return selection;
    }

    public void processSelectedMenuItem() {
        try {
            switch (selection) {
                case 1: {
                    clientView.createClient();
                    break;
                }
                case 2: {
                    clientView.deleteClient();
                    break;
                }
                case 3: {
                    accountView.createAccount();
                    break;
                }
                case 4: {
                    accountView.updateAccount();
                    break;
                }
                case 5: {
                    accountView.deleteAccount();
                    break;
                }
                case 6: {
                    clientView.displayAllClientsInfo();
                    break;
                }
                case 7: {
                    toContinue = false;
                    HibernateUtil.closeEntityManagerFactory();
                    break;
                }
            }
        } catch (Exception e) {
            log.error("Error in Menu.", e);
        }
        selection = 0;
    }
}
