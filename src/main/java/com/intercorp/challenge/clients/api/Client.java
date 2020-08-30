package com.intercorp.challenge.clients.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intercorp.challenge.clients.utils.DateUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Component
public class Client implements com.intercorp.challenge.demoApp.Client {

    private Long id;
    private String name;
    private String lastName;
    private Date birthDate;

    public Client() {
    }

    public Client(Long id, String name, String lastName, Date birthDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty
    public int getAge() {
        return DateUtil.yearsSince(birthDate);
    }

    @JsonProperty
    public Date getEstimatedDateOfDeath() {
        //Life expectancy in Latin America is 80 years and 10 months approximately
        LocalDate estimated = DateUtil.from(birthDate).plusYears(80).plusMonths(10);
        return DateUtil.from(estimated);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                name.equals(client.name) &&
                lastName.equals(client.lastName) &&
                birthDate.equals(client.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, birthDate);
    }
}
