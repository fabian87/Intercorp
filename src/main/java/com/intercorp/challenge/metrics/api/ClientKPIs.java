package com.intercorp.challenge.metrics.api;

import org.springframework.stereotype.Component;

@Component
public class ClientKPIs implements com.intercorp.challenge.demoApp.ClientKPIs {

    private double averageAge;
    private double standardDeviation;

    public ClientKPIs() {
    }

    public ClientKPIs(double averageAge, double standardDeviation) {
        this.averageAge = averageAge;
        this.standardDeviation = standardDeviation;
    }

    @Override
    public double getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(double averageAge) {
        this.averageAge = averageAge;
    }

    @Override
    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}
