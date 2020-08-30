package com.intercorp.challenge.clients;

import com.intercorp.challenge.clients.api.Client;
import com.intercorp.challenge.clients.persistence.ClientEntity;
import com.intercorp.challenge.clients.persistence.ClientRepository;
import com.intercorp.challenge.clients.utils.ClientParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(final Client client) {
        final ClientEntity entity = clientRepository.save(ClientParser.parse(client));
        return ClientParser.parse(entity);
    }

    public List<Client> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientParser::parse)
                .collect(Collectors.toList());
    }

    public Optional<Client> get(Long id) {
        return clientRepository.findById(id).map(ClientParser::parse);
    }
}
