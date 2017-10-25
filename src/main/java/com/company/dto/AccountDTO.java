package com.company.dto;

import java.util.Date;
import java.util.Objects;

public class AccountDTO {
    private Long id;
    private String login;
    private String password;
    private Date created;

    public AccountDTO() {
    }

    public AccountDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AccountDTO(Long id, String login, String password, Date created) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof AccountDTO)) return false;
        final AccountDTO other = (AccountDTO) obj;
        return Objects.equals(id, other.id) && Objects.equals(login, other.login)
                && Objects.equals(password, other.password) && Objects.equals(created, other.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, created);
    }
}
