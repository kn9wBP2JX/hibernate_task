package com.company.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClientEntity {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    private String name;
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    private List<AccountEntity> accountEntities = new ArrayList<>();

    public ClientEntity() {
    }

    public ClientEntity(Long id, String name, String email, List<AccountEntity> accountEntities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accountEntities = accountEntities;
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

    public List<AccountEntity> getAccountEntities() {
        return accountEntities;
    }

    public void setAccountEntities(List<AccountEntity> accountEntities) {
        this.accountEntities = accountEntities;
    }
}
