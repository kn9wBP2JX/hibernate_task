package com.company.view;

import com.company.dto.AccountDTO;
import com.company.dto.ClientDTO;
import com.company.services.ClientService;
import com.company.view.io.Reader;
import com.company.view.io.Writer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class ClientViewImpl implements ClientView {
    private final static Logger log = LoggerFactory.getLogger(ClientViewImpl.class.getName());
    private Writer writer;
    private Reader reader;
    private ClientService clientService;

    public ClientViewImpl(Writer writer, Reader reader, ClientService clientService) {
        this.writer = writer;
        this.reader = reader;
        this.clientService = clientService;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void createClient() throws IOException {
        ClientDTO client = new ClientDTO();
        writer.printLine("Input client name: ");
        client.setName(reader.readString());
        writer.printLine("Input client e-mail: ");
        client.setEmail(reader.readString());
        writer.printLine(clientService.saveClient(client) ? "Client created." : "Client wasn't created.");
    }

    @Override
    public void deleteClient() throws IOException {
        writer.printLine("Input client id: ");
        Long clientId = reader.readLong();
        writer.printLine(clientService.deleteClient(clientId) ? "Client with id = " + clientId +
                " was deleted." : "Client wasn't found.");
    }

    @Override
    public void displayAllClientsInfo() {
        final Collection<ClientDTO> clients = clientService.getAllClients();
        StringBuilder output = new StringBuilder();
        if (clients != null && clients.size() > 0) {
            output.append(StringUtils.center("Clients", 55));
            output.append(StringUtils.center("Accounts", 85) + "\n");
            output.append(StringUtils.repeat("-", 140) + "\n");
            String columnsNames = String.format("%1$5s%2$25s%3$27s%4$3s%5$30s%6$25s%7$25s", "id", "e-mail", "name |",
                    "id", "created", "login", "password");
            output.append(columnsNames + "\n");
            output.append(StringUtils.repeat("=", 140) + "\n");
            for (ClientDTO client : clients) {
                output.append(String.format("%1$5d%2$25s%3$25s |", client.getId(), client.getEmail(),
                        client.getName()) + "\n");
                List<AccountDTO> accounts = client.getAccounts();
                if (accounts != null && accounts.size() > 0) {
                    for (AccountDTO ac : accounts) {
                        output.append(String.format("%1$57s%2$3d%3$30s%4$25s%5$25s", "|", ac.getId(),
                                ac.getCreated(), ac.getLogin(), ac.getPassword()) + "\n");
                    }
                }
                output.append(StringUtils.repeat("-", 140) + "\n");
            }
            writer.printLine(output);
        } else {
            writer.printLine("No data to display.");
            log.info("No data to display.");
        }
    }
}
