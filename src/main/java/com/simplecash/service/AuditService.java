package com.simplecash.service;

import com.simplecash.entity.Compte;

import java.util.List;

public interface AuditService {

    List<Compte> getComptesParticuliersDebiteursAnormaux();

    List<Compte> getComptesEntreprisesDebiteursAnormaux();

    AuditResult getAuditGlobal();

    class AuditResult {
        public double totalCredits;
        public double totalDebits;
        public double totalSolde;
    }
}
