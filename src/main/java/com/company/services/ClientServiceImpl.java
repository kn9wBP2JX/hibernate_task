package com.company.services;

import com.company.dto.ClientDTO;
import com.company.persistence.ClientDao;
import com.company.persistence.EntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class ClientServiceImpl implements ClientService {
    private final static Logger log = LoggerFactory.getLogger(ClientServiceImpl.class.getName());
    private ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Collection<ClientDTO> getAllClients() {
        try {
            return EntityConverter.toDto(clientDao.getAllClients());
        } catch (Exception e) {
            log.error("Can't get all clients.", e);
            return null;
        }
    }

    @Override
    public boolean deleteClient(Long clientId) {
        try {
            clientDao.delete(clientDao.find(clientId));
            log.info("Client with id = " + clientId + " was deleted.");
            return true;
        } catch (Exception e) {
            log.error("Client wasn't found.", e);
            return false;
        }
    }

    @Override
    public boolean saveClient(ClientDTO dto) {
        try {
            clientDao.save(EntityConverter.toEntity(dto));
            log.info("Client saved. ID = " + dto.getId());
            return true;
        } catch (Exception e) {
            log.error("Client wasn't saved.", e);
            return false;
        }
    }
}