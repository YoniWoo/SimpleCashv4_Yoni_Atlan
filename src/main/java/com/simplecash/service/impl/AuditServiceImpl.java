package com.simplecash.service.impl;

import com.simplecash.dto.AuditGlobalDTO;
import com.simplecash.dto.CompteDTO;
import com.simplecash.entity.Compte;
import com.simplecash.mapper.CompteMapper;
import com.simplecash.repository.ClientRepository;
import com.simplecash.repository.CompteRepository;
import com.simplecash.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;
    private final CompteMapper compteMapper;

    // Seuils d'anomalie (tu peux les documenter dans ton rapport)
    private static final BigDecimal SEUIL_PARTICULIER = BigDecimal.valueOf(-5000);
    private static final BigDecimal SEUIL_ENTREPRISE = BigDecimal.valueOf(-50000);

    @Override
    public List<CompteDTO> getComptesParticuliersDebiteursAnormaux() {
        List<Compte> comptes = compteRepository
                .findByClientTypeClientIgnoreCaseAndSoldeLessThan("PARTICULIER", SEUIL_PARTICULIER);
        return comptes.stream()
                .map(compteMapper::toDto)
                .toList();
    }

    @Override
    public List<CompteDTO> getComptesEntreprisesDebiteursAnormaux() {
        List<Compte> comptes = compteRepository
                .findByClientTypeClientIgnoreCaseAndSoldeLessThan("ENTREPRISE", SEUIL_ENTREPRISE);
        return comptes.stream()
                .map(compteMapper::toDto)
                .toList();
    }

    @Override
    public AuditGlobalDTO getAuditGlobal() {
        var tousLesComptes = compteRepository.findAll();

        long nbClients = clientRepository.count();
        long nbComptes = tousLesComptes.size();

        // somme des soldes
        var soldeTotal = tousLesComptes.stream()
                .map(Compte::getSolde)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long nbParticuliersAnormaux = tousLesComptes.stream()
                .filter(c -> "PARTICULIER".equalsIgnoreCase(c.getClient().getTypeClient()))
                .filter(c -> c.getSolde().compareTo(SEUIL_PARTICULIER) < 0)
                .count();

        long nbEntreprisesAnormaux = tousLesComptes.stream()
                .filter(c -> "ENTREPRISE".equalsIgnoreCase(c.getClient().getTypeClient()))
                .filter(c -> c.getSolde().compareTo(SEUIL_ENTREPRISE) < 0)
                .count();

        return new AuditGlobalDTO(
                nbClients,
                nbComptes,
                soldeTotal,
                nbParticuliersAnormaux,
                nbEntreprisesAnormaux
        );
    }
}
