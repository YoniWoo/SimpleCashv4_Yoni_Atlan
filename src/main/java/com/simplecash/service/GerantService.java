package com.simplecash.service;

import com.simplecash.dto.*;

import java.util.List;
import java.util.Optional;

public interface GerantService {

    List<GerantDTO> getAll();

    GerantDTO create(GerantCreateDTO dto);

    Optional<GerantDTO> getById(Long id);

    Optional<GerantDTO> update(Long id, GerantUpdateDTO dto);

    void delete(Long id);

    // Cas métier : le gérant ajoute un conseiller dans son agence
    ConseillerDTO addConseiller(Long gerantId, ConseillerCreateDTO dto);
}
