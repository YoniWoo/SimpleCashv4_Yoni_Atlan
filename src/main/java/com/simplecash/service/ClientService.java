package com.simplecash.service;

import com.simplecash.entity.Client;

import java.util.List;

public interface ClientService {

    Client create(Client client);

    Client update(Long id, Client client);

    Client getById(Long id);

    List<Client> getAll();

    void delete(Long id);

    List<Client> getByConseiller(Long conseillerId);
}
