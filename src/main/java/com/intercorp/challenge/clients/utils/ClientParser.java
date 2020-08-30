package com.intercorp.challenge.clients.utils;

import com.intercorp.challenge.clients.api.Client;
import com.intercorp.challenge.clients.persistence.ClientEntity;

public class ClientParser {

    public static ClientEntity parse(final Client baseClient) {
        final ClientEntity entity = new ClientEntity();
        entity.setId(baseClient.getId());
        entity.setName(baseClient.getName());
        entity.setLastName(baseClient.getLastName());
        entity.setBirthDate(baseClient.getBirthDate());
        return entity;
    }

    public static Client parse(final ClientEntity entity) {
        final Client client = new Client();
        client.setId(entity.getId());
        client.setName(entity.getName());
        client.setLastName(entity.getLastName());
        client.setBirthDate(entity.getBirthDate());
        return client;
    }
}
