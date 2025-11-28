package com.simplecash.service;

import com.simplecash.dto.AgenceCreateDTO;
import com.simplecash.dto.AgenceDTO;
import com.simplecash.dto.AgenceUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface AgenceService {

    List<AgenceDTO> getAll();

    AgenceDTO create(AgenceCreateDTO dto);

    Optional<AgenceDTO> getById(String id);

    Optional<AgenceDTO> update(String id, AgenceUpdateDTO dto);

    void delete(String id);
}
