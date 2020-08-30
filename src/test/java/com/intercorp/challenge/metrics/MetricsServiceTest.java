package com.intercorp.challenge.metrics;


import com.intercorp.challenge.clients.ClientService;
import com.intercorp.challenge.clients.api.Client;
import com.intercorp.challenge.metrics.api.ClientKPIs;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MetricsServiceTest {

    @Mock
    private ClientService clientService;

    @Mock
    private Client client;

    @InjectMocks
    private MetricsService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAClientKPI() {
        ClientKPIs metrics = service.get();
        assertNotNull(metrics);
        assertEquals(0, metrics.getAverageAge(), 0.0000001);
        assertEquals(0, metrics.getStandardDeviation(), 0.0000001);
    }

    @Test
    public void shouldReturnAClientKPIWithNoVariance() {
        when(clientService.getAll()).thenReturn(Collections.singletonList(client));
        when(client.getAge()).thenReturn(5);
        ClientKPIs metrics = service.get();
        assertNotNull(metrics);
        assertEquals(5, metrics.getAverageAge(), 0.0000001);
        assertEquals(0, metrics.getStandardDeviation(), 0.0000001);

    }

}
