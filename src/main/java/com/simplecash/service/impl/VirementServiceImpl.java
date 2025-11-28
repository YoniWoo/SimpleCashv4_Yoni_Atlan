package com.simplecash.service.impl;

import com.simplecash.entity.Compte;
import com.simplecash.repository.CompteRepository;
import com.simplecash.service.VirementService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class VirementServiceImpl implements VirementService {

    private final CompteRepository compteRepository;

    public VirementServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    // @LogVirement  // à activer quand tu auras créé l'annotation AOP
    @Override
    public void virement(Long compteSourceId, Long compteDestinationId, BigDecimal montant) {
        if (compteSourceId.equals(compteDestinationId)) {
            throw new IllegalArgumentException("Les comptes source et destination doivent être différents");
        }
        if (montant.signum() <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }

        Compte source = compteRepository.findById(compteSourceId)
                .orElseThrow(() -> new RuntimeException("Compte source introuvable"));

        Compte destination = compteRepository.findById(compteDestinationId)
                .orElseThrow(() -> new RuntimeException("Compte destination introuvable"));

        if (source.getSolde().compareTo(montant) < 0) {
            throw new RuntimeException("Solde insuffisant pour le virement");
        }

        source.setSolde(source.getSolde().subtract(montant));
        destination.setSolde(destination.getSolde().add(montant));

        compteRepository.save(source);
        compteRepository.save(destination);
    }
}
