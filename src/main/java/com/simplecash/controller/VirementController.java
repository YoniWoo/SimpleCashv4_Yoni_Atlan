package com.simplecash.controller;

import com.simplecash.dto.VirementDTO;
import com.simplecash.dto.VirementResultDTO;
import com.simplecash.service.VirementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/virements")
@RequiredArgsConstructor
public class VirementController {

    private final VirementService virementService;

    @PostMapping
    public ResponseEntity<VirementResultDTO> virement(@RequestBody @Valid VirementDTO dto) {
        VirementResultDTO result = virementService.virement(dto);
        return ResponseEntity.ok(result);
    }
}
