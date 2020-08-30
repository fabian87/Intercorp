package com.intercorp.challenge.metrics.utils;

import com.intercorp.challenge.metrics.api.ClientKPIs;

import java.util.List;
import java.util.Optional;

public class ClientKPIBuilder {

    private List<Integer> clientAges;

    public ClientKPIBuilder setClientAges(final List<Integer> clientAges) {
        this.clientAges = clientAges;
        return this;
    }

    public ClientKPIs build() {
        final double mean = getMean();
        final double sd = getStandardDeviation(mean);
        return new ClientKPIs(mean, sd);
    }

    private double getStandardDeviation(double mean) {
        final Optional<Double> sd = clientAges.stream()
                .map(a -> Math.pow(a - mean, 2))
                .reduce(Double::sum)
                .map(t -> t / clientAges.size())
                .map(Math::sqrt);
        return sd.orElse(0D);
    }

    private double getMean() {
        return clientAges.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0);
    }
}
