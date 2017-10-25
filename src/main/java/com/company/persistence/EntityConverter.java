package com.company.persistence;

import com.company.dto.AccountDTO;
import com.company.dto.ClientDTO;
import com.company.entities.AccountEntity;
import com.company.entities.ClientEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EntityConverter {

    public static AccountDTO toDto(AccountEntity entity) {
        return new AccountDTO(entity.getId(), entity.getLogin(), entity.getPassword(), entity.getCreated());
    }

    public static ClientDTO toDto(ClientEntity entity) {
        List<AccountDTO> accounts = entity.getAccountEntities().stream()
                .map(EntityConverter::toDto)
                .collect(Collectors.toCollection(() -> new ArrayList<>(entity.getAccountEntities().size())));
        return new ClientDTO(entity.getId(), entity.getName(), entity.getEmail(), accounts);
    }

    public static Collection<ClientDTO> toDto(Collection<ClientEntity> clientEntities) {
        return clientEntities.stream()
                .map(EntityConverter::toDto)
                .collect(Collectors.toCollection(() -> new ArrayList<>(clientEntities.size())));
    }

    public static AccountEntity toEntity(AccountDTO dto, Long clientId) {
        return new AccountEntity(dto.getId(), clientId, dto.getLogin(), dto.getPassword(), dto.getCreated());
    }

    public static ClientEntity toEntity(ClientDTO dto) {
        List<AccountEntity> accounts = dto.getAccounts().stream()
                .map(accountDTO -> toEntity(accountDTO, dto.getId()))
                .collect(Collectors.toCollection(() -> new ArrayList<>(dto.getAccounts().size())));
        return new ClientEntity(dto.getId(), dto.getName(), dto.getEmail(), accounts);
    }
}
