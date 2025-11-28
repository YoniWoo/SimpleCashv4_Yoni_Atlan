package com.simplecash.service;

import com.simplecash.dto.CompteCreateDTO;
import com.simplecash.dto.CompteDTO;
import com.simplecash.dto.CompteUpdateDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CompteService {

    // Créer un compte pour un client donné
    CompteDTO create(Long clientId, CompteCreateDTO dto);

    Optional<CompteDTO> getById(Long id);

    List<CompteDTO> getByClient(Long clientId);

    Optional<CompteDTO> update(Long id, CompteUpdateDTO dto);

    CompteDTO credit(Long compteId, BigDecimal montant);

    CompteDTO debit(Long compteId, BigDecimal montant);

    void delete(Long id); // suppression seulement si solde = 0
}
