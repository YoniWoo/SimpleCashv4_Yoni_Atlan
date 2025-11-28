package com.simplecash.service;

import com.simplecash.dto.VirementDTO;
import com.simplecash.dto.VirementResultDTO;

public interface VirementService {

    VirementResultDTO virement(VirementDTO request);
}
