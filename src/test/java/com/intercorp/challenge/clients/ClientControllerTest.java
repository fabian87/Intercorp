package com.intercorp.challenge.clients;

import com.intercorp.challenge.clients.api.Client;
import com.intercorp.challenge.clients.api.ClientController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private Client mockClient;
    @Mock
    private Client storedClient;

    @InjectMocks
    ClientController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(storedClient.getId()).thenReturn(1L);
        when(clientService.save(any(Client.class))).thenReturn(storedClient);
    }

    @Test
    public void shouldReturnAClient() {
        Client response = controller.create(mockClient);
        verify(clientService, times(1)).save(mockClient);
        assertNotNull(response);
    }

    @Test
    public void ifAClientIsCreated_then_returnedClientShouldHaveAnId() {
        Client response = controller.create(mockClient);
        verify(clientService, times(1)).save(mockClient);
        assertNotNull(response);
        assertNotNull(response.getId());
    }

    @Test
    public void shouldReturnAListOfClients() {
        assertNotNull(controller.findAll());
        verify(clientService, times(1)).getAll();
    }

    @Test
    public void ifNoStoredClients_then_shouldReturnAnEmptyList() {
        List<Client> clients = controller.findAll();
        verify(clientService, times(1)).getAll();
        assertNotNull(clients);
        assertTrue(clients.isEmpty());
    }

    @Test
    public void shouldReturnAListOfClientsWithAllStoredClients() {
        when(clientService.getAll()).thenReturn(Collections.singletonList(storedClient));
        List<Client> clients = controller.findAll();
        verify(clientService, times(1)).getAll();
        assertNotNull(clients);
        assertFalse(clients.isEmpty());
        assertTrue(clients.contains(storedClient));
    }

    @Test
    public void ifNoClientFound_then_returnNull() {
        when(clientService.get(1L)).thenReturn(Optional.empty());
        assertNull(controller.find(1L));
    }

    @Test
    public void ifClientExist_then_returnClient() {
        when(clientService.get(1L)).thenReturn(Optional.of(storedClient));
        Client result = controller.find(1L);
        assertNotNull(result);
        assertEquals(storedClient, result);
    }
}
