package com.simplecash.service;

import java.math.BigDecimal;

public interface VirementService {

    void virement(Long compteSourceId, Long compteDestinationId, BigDecimal montant);
}
