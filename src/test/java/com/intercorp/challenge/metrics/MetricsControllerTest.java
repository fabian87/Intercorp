package com.intercorp.challenge.metrics;

import com.intercorp.challenge.metrics.api.ClientKPIs;
import com.intercorp.challenge.metrics.api.MetricsController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MetricsControllerTest {

    @Mock
    private MetricsService service;

    @Mock
    private ClientKPIs clientKPIs;

    @InjectMocks
    private MetricsController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(service.get()).thenReturn(clientKPIs);
    }

    @Test
    public void shouldReturnAClientKPI() {
        ClientKPIs result = controller.get();
        assertNotNull(result);
        assertEquals(clientKPIs, result);
    }

}
