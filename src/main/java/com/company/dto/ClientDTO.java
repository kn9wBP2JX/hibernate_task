package com.company.dto;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private List<AccountDTO> accounts = new ArrayList<>();

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String email, List<AccountDTO> accounts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(AccountDTO account) {
        if (account != null) {
            accounts.add(account);
        }
    }

    public void removeAccount(Long id) {
        accounts.removeIf(account -> account.getId().equals(id));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof ClientDTO)) return false;
        final ClientDTO other = (ClientDTO) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name)
                && Objects.equals(email, other.email) && CollectionUtils.subtract(accounts, other.accounts).size() == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, accounts);
    }
}
