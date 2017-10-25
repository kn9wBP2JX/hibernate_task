package com.company.persistence;

import com.company.entities.ClientEntity;

import java.util.Collection;
import java.util.List;

public class ClientDaoImpl extends GenericDaoImpl<ClientEntity> implements ClientDao {
    @Override
    public Collection<ClientEntity> getAllClients() {
        return getEntityManager().createQuery("SELECT c FROM ClientEntity c", ClientEntity.class).getResultList();
    }

    @Override
    public ClientEntity findByAccountId(Long accountId) {
        return getEntityManager().createQuery(
                "SELECT c FROM ClientEntity c JOIN c.accountEntities a WHERE a.id = :accountId", ClientEntity.class)
                .setParameter("accountId", accountId)
                .getSingleResult();
    }
}
