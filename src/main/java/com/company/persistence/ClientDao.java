package com.company.persistence;

import com.company.entities.ClientEntity;

import java.util.Collection;
import java.util.List;

public interface ClientDao extends GenericDao<ClientEntity> {
    Collection<ClientEntity> getAllClients();

    ClientEntity findByAccountId(Long accountId);
}

