package com.intercorp.challenge.clients.api;

import com.intercorp.challenge.clients.ClientService;
import com.intercorp.challenge.demoApp.ClientInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController implements ClientInteractor<Client> {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client newClient) {
        return clientService.save(newClient);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Client> findAll() {
        return clientService.getAll();
    }

    @RequestMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client find(@PathVariable Long id) {
        return clientService.get(id).orElse(null);
    }
}
