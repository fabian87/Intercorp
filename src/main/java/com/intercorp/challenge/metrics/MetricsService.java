package com.intercorp.challenge.metrics;

import com.intercorp.challenge.clients.ClientService;
import com.intercorp.challenge.clients.api.Client;

import com.intercorp.challenge.metrics.api.ClientKPIs;
import com.intercorp.challenge.metrics.utils.ClientKPIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetricsService {

    private ClientService clientService;

    @Autowired
    public MetricsService(ClientService clientService) {
        this.clientService = clientService;
    }

    public ClientKPIs get() {
        return new ClientKPIBuilder()
                .setClientAges(getClientAges())
                .build();
    }

    private List<Integer> getClientAges() {
        return clientService.getAll()
                .parallelStream()
                .mapToInt(Client::getAge)
                .boxed()
                .collect(Collectors.toList());
    }
}
