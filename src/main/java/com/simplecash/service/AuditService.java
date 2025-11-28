package com.simplecash.service;

import com.simplecash.dto.AuditGlobalDTO;
import com.simplecash.dto.CompteDTO;

import java.util.List;

public interface AuditService {

    List<CompteDTO> getComptesParticuliersDebiteursAnormaux();

    List<CompteDTO> getComptesEntreprisesDebiteursAnormaux();

    AuditGlobalDTO getAuditGlobal();
}
