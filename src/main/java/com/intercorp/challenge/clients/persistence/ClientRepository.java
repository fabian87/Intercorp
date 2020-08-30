package com.intercorp.challenge.clients.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository()
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    List<ClientEntity> findByName(String name);

    List<ClientEntity> findByNameAndLastName(String name, String lastName);

    List<ClientEntity> findByBirthDate(Date birthDate);
}
