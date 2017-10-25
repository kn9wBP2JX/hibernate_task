package com.company.services;

import com.company.dto.ClientDTO;

import java.util.Collection;

public interface ClientService {
    boolean deleteClient(Long clientId);

    boolean saveClient(ClientDTO dto);

    Collection<ClientDTO> getAllClients();
}