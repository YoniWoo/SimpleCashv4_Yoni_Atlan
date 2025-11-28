package com.simplecash.controller;

import com.simplecash.dto.AuditGlobalDTO;
import com.simplecash.dto.CompteDTO;
import com.simplecash.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @GetMapping("/comptes/particuliers-anormaux")
    public List<CompteDTO> getComptesParticuliersAnormaux() {
        return auditService.getComptesParticuliersDebiteursAnormaux();
    }

    @GetMapping("/comptes/entreprises-anormaux")
    public List<CompteDTO> getComptesEntreprisesAnormaux() {
        return auditService.getComptesEntreprisesDebiteursAnormaux();
    }

    @GetMapping("/global")
    public AuditGlobalDTO getAuditGlobal() {
        return auditService.getAuditGlobal();
    }
}
