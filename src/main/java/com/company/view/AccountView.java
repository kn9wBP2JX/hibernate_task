package com.company.view;

import java.io.IOException;

public interface AccountView {
    void createAccount() throws IOException;

    void updateAccount() throws IOException;

    void deleteAccount() throws IOException;
}
