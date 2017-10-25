package com.company.view;

import com.company.dto.AccountDTO;
import com.company.dto.ClientDTO;
import com.company.services.ClientService;
import com.company.view.io.Reader;
import com.company.view.io.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientViewImplTest {
    private Writer writer;
    private Reader reader;
    private ClientService clientService;
    private ClientViewImpl clientView;

    @BeforeEach
    void setUp() {
        writer = mock(Writer.class);
        reader = mock(Reader.class);
        clientService = mock(ClientService.class);
        clientView = new ClientViewImpl(writer, reader, clientService);
    }

    @Test
    void createClient_correctInput_callServiceWithThatInput() throws IOException {
        // Given
        ClientDTO client = new ClientDTO();
        client.setName("William");
        client.setEmail("William@company.com");
        when(reader.readString()).thenReturn("William", "William@company.com");
        when(clientService.saveClient(notNull())).thenReturn(true);
        // When
        clientView.createClient();
        // Then
        verify(clientService).saveClient(client);
        verify(writer).printLine("Client created.");
    }

    @Test
    void deleteClient_correctInput_callServiceWithThatInput() throws IOException {
        // Given
        when(reader.readLong()).thenReturn(1L);
        when(clientService.deleteClient(eq(1L))).thenReturn(true);
        // When
        clientView.deleteClient();
        // Then
        verify(clientService).deleteClient(1L);
        verify(writer).printLine("Client with id = " + 1L + " was deleted.");
    }

    @SuppressWarnings("unchecked")
    @Test
    void displayAllClientsInfo_correctInput_callWriter() throws ParseException {
        // Given
        DateFormat df = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        List<ClientDTO> clients = new ArrayList<>();
        clients.add(new ClientDTO(1L, "John Smith", "client@example.com", Arrays.asList(
                new AccountDTO(10L, "JSmith1", "zzwvp0d9", df.parse("10:15:30 20.10.2017")),
                new AccountDTO(20L, "JSmith2", "mhjnbgfv", df.parse("10:15:30 5.5.2017")),
                new AccountDTO(30L, "JSmith3", "ytersds1", df.parse("15:00:30 12.10.2017"))
        )));
        clients.add(new ClientDTO(2L, "Jack Black", "jack@example.com", new ArrayList<>()));
        when(clientService.getAllClients()).thenReturn(clients);
        ArgumentCaptor<Object> argument = ArgumentCaptor.forClass(Object.class);
        // When
        clientView.displayAllClientsInfo();
        // Then
        verify(writer).printLine(argument.capture());
        final String output = argument.getValue().toString();
        assertAll(
                // Client
                () -> assertTrue(output.contains(Long.toString(1))),
                () -> assertTrue(output.contains("client@example.com")),
                () -> assertTrue(output.contains("John Smith")),
                // Accounts
                () -> assertTrue(output.contains(Long.toString(10))),
                () -> assertTrue(output.contains("JSmith1")),
                () -> assertTrue(output.contains("zzwvp0d9")),
                () -> assertTrue(output.contains(df.parse("10:15:30 20.10.2017").toString())),
                () -> assertTrue(output.contains(Long.toString(20))),
                () -> assertTrue(output.contains("JSmith2")),
                () -> assertTrue(output.contains("mhjnbgfv")),
                () -> assertTrue(output.contains(df.parse("10:15:30 5.5.2017").toString())),
                () -> assertTrue(output.contains(Long.toString(30))),
                () -> assertTrue(output.contains("JSmith3")),
                () -> assertTrue(output.contains("ytersds1")),
                () -> assertTrue(output.contains(df.parse("15:00:30 12.10.2017").toString())),
                // Client
                () -> assertTrue(output.contains(Long.toString(2))),
                () -> assertTrue(output.contains("jack@example.com")),
                () -> assertTrue(output.contains("Jack Black"))
        );
    }
}