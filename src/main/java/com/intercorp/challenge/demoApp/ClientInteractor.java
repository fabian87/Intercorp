package com.intercorp.challenge.demoApp;

import java.util.List;

public interface ClientInteractor<T extends Client> {
    List<T> findAll();

    T find(Long id);

    T create(T client);
}
