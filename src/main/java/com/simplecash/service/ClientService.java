package com.simplecash.service;

import com.simplecash.dto.ClientCreateDTO;
import com.simplecash.dto.ClientDTO;
import com.simplecash.dto.ClientUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<ClientDTO> getAll();

    ClientDTO create(ClientCreateDTO createDTO);

    Optional<ClientDTO> getById(Long id);

    Optional<ClientDTO> update(Long id, ClientUpdateDTO updateDTO);

    void delete(Long id);

    List<ClientDTO> getByConseiller(Long conseillerId);
}
