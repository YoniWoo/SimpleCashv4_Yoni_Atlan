package com.simplecash.service;

import com.simplecash.dto.ConseillerCreateDTO;
import com.simplecash.dto.ConseillerDTO;
import com.simplecash.dto.ConseillerUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface ConseillerService {

    List<ConseillerDTO> getAll();

    ConseillerDTO create(ConseillerCreateDTO dto);

    Optional<ConseillerDTO> getById(Long id);

    Optional<ConseillerDTO> update(Long id, ConseillerUpdateDTO dto);

    void delete(Long id);
}
