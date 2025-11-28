package com.simplecash.service.impl;

import com.simplecash.dto.CompteDTO;
import com.simplecash.dto.VirementDTO;
import com.simplecash.dto.VirementResultDTO;
import com.simplecash.entity.Compte;
import com.simplecash.mapper.CompteMapper;
import com.simplecash.repository.CompteRepository;
import com.simplecash.service.VirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class VirementServiceImpl implements VirementService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    @Override
    public VirementResultDTO virement(VirementDTO request) {
        Long sourceId = request.compteSourceId();
        Long destId = request.compteDestinationId();
        BigDecimal montant = request.montant();

        if (sourceId == null || destId == null) {
            throw new IllegalArgumentException("Les comptes source et destination sont obligatoires");
        }

        if (sourceId.equals(destId)) {
            throw new IllegalArgumentException("Les comptes source et destination doivent être différents");
        }

        if (montant == null || montant.signum() <= 0) {
            throw new IllegalArgumentException("Le montant du virement doit être strictement positif");
        }

        // Récupérer les comptes
        Compte source = compteRepository.findById(sourceId)
                .orElseThrow(() -> new RuntimeException("Compte source introuvable avec id " + sourceId));

        Compte destination = compteRepository.findById(destId)
                .orElseThrow(() -> new RuntimeException("Compte destination introuvable avec id " + destId));

        // Vérifier le solde du compte source (pas de découvert autorisé)
        if (source.getSolde().compareTo(montant) < 0) {
            throw new RuntimeException("Solde insuffisant sur le compte source");
        }

        // Débit / Crédit
        source.setSolde(source.getSolde().subtract(montant));
        destination.setSolde(destination.getSolde().add(montant));

        // Pas besoin de save explicite : les entités sont managées (mais tu peux le faire si tu veux être explicite)
        // compteRepository.save(source);
        // compteRepository.save(destination);

        CompteDTO sourceDto = compteMapper.toDto(source);
        CompteDTO destDto = compteMapper.toDto(destination);

        return new VirementResultDTO(sourceDto, destDto);
    }
}
