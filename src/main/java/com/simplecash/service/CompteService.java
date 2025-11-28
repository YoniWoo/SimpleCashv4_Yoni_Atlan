package com.simplecash.service;

import com.simplecash.entity.Compte;

import java.math.BigDecimal;
import java.util.List;

public interface CompteService {

    Compte create(Compte compte, Long clientId);

    Compte getById(Long id);

    List<Compte> getByClient(Long clientId);

    Compte credit(Long compteId, BigDecimal montant);

    Compte debit(Long compteId, BigDecimal montant);

    void delete(Long compteId);
}
