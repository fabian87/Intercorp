package com.intercorp.challenge.clients;

import com.intercorp.challenge.clients.api.Client;
import com.intercorp.challenge.clients.persistence.ClientEntity;
import com.intercorp.challenge.clients.persistence.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private Client mockClient;

    private ClientEntity storedEntity;

    @InjectMocks
    private ClientService clientService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        storedEntity = new ClientEntity(1L, "test Name", "test LstName", new Date());
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(storedEntity);
    }

    @Test
    public void shouldSaveAClient() {
        assertNotNull(clientService.save(mockClient));
        verify(clientRepository, times(1)).save(any(ClientEntity.class));
    }

    @Test
    public void shouldReturnAListOfClients() {
        List<Client> clients = clientService.getAll();
        assertNotNull(clients);
        assertTrue(clients.isEmpty());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void ifAreClientsStored_then_shouldReturnNonEmptyList() {
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(storedEntity));
        List<Client> clients = clientService.getAll();
        assertNotNull(clients);
        assertFalse(clients.isEmpty());
        assertEquals(storedEntity.getId(), clients.get(0).getId());
    }

    @Test
    public void ifNoClientStored_then_shouldBeEmpty() {
        assertEquals(Optional.empty(), clientService.get(10L));
        verify(clientRepository, times(1)).findById(10L);
    }

    @Test
    public void ifClientStored_then_shouldNotEmpty() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(storedEntity));
        Optional<Client> client = clientService.get(1L);
        assertTrue(client.isPresent());
        assertEquals(Optional.of(1L), client.map(Client::getId));
    }

}
