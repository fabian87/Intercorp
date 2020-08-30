package com.intercorp.challenge.metrics.api;

import com.intercorp.challenge.demoApp.MetricsInteractor;
import com.intercorp.challenge.metrics.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class MetricsController implements MetricsInteractor {

    private MetricsService metricsService;

    @Autowired
    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clients")
    @ResponseStatus(HttpStatus.OK)
    public ClientKPIs get() {
        return metricsService.get();
    }
}
