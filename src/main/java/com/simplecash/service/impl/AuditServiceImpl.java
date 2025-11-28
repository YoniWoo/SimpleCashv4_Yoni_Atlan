package com.simplecash.service.impl;

import com.simplecash.entity.Client;
import com.simplecash.entity.Compte;
import com.simplecash.repository.ClientRepository;
import com.simplecash.repository.CompteRepository;
import com.simplecash.service.AuditService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {

    private final ClientRepository clientRepository;
    private final CompteRepository compteRepository;

    public AuditServiceImpl(ClientRepository clientRepository,
                            CompteRepository compteRepository) {
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
    }

    @Override
    public List<Compte> getComptesParticuliersDebiteursAnormaux() {
        List<Client> clients = clientRepository.findByTypeClientIgnoreCase("PARTICULIER");
        return clients.stream()
                .flatMap(c -> c.getComptes().stream())
                .filter(c -> c.getSolde().compareTo(BigDecimal.valueOf(-5000)) < 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Compte> getComptesEntreprisesDebiteursAnormaux() {
        List<Client> clients = clientRepository.findByTypeClientIgnoreCase("ENTREPRISE");
        return clients.stream()
                .flatMap(c -> c.getComptes().stream())
                .filter(c -> c.getSolde().compareTo(BigDecimal.valueOf(-50000)) < 0)
                .collect(Collectors.toList());
    }

    @Override
    public AuditResult getAuditGlobal() {
        List<Compte> comptes = compteRepository.findAll();

        AuditResult result = new AuditResult();
        result.totalSolde = comptes.stream()
                .map(Compte::getSolde)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();

        // Version simplifiée : on n’a pas la notion de crédit/débit séparés,
        // donc on ne remplit que totalSolde
        result.totalCredits = 0;
        result.totalDebits = 0;

        return result;
    }
}
