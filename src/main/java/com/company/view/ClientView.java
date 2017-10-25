package com.company.view;

import java.io.IOException;

public interface ClientView {
    void createClient() throws IOException;

    void displayAllClientsInfo();

    void deleteClient() throws IOException;
}
