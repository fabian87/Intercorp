package com.intercorp.challenge.demoApp;

import java.util.Date;

public interface Client {
    Long getId();

    String getName();

    String getLastName();

    Date getBirthDate();

    int getAge();

    Date getEstimatedDateOfDeath();
}
