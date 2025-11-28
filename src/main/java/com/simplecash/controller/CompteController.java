package com.simplecash.controller;

import com.simplecash.dto.CompteCreateDTO;
import com.simplecash.dto.CompteDTO;
import com.simplecash.dto.CompteOperationDTO;
import com.simplecash.dto.CompteUpdateDTO;
import com.simplecash.service.CompteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;

    // Créer un compte pour un client
    @PostMapping("/client/{clientId}")
    public ResponseEntity<CompteDTO> create(@PathVariable Long clientId,
                                            @RequestBody @Valid CompteCreateDTO dto) {
        CompteDTO created = compteService.create(clientId, dto);
        return ResponseEntity.ok(created);
    }

    // Récupérer un compte par id
    @GetMapping("{id}")
    public ResponseEntity<CompteDTO> getById(@PathVariable Long id) {
        return compteService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Récupérer les comptes d'un client
    @GetMapping("/client/{clientId}")
    public List<CompteDTO> getByClient(@PathVariable Long clientId) {
        return compteService.getByClient(clientId);
    }

    // Modifier des infos du compte (numéro, type)
    @PatchMapping("{id}")
    public ResponseEntity<CompteDTO> update(@PathVariable Long id,
                                            @RequestBody @Valid CompteUpdateDTO dto) {
        return compteService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créditer un compte
    @PostMapping("/credit")
    public ResponseEntity<CompteDTO> credit(@RequestBody @Valid CompteOperationDTO dto) {
        CompteDTO result = compteService.credit(dto.compteId(), dto.montant());
        return ResponseEntity.ok(result);
    }

    // Débiter un compte
    @PostMapping("/debit")
    public ResponseEntity<CompteDTO> debit(@RequestBody @Valid CompteOperationDTO dto) {
        CompteDTO result = compteService.debit(dto.compteId(), dto.montant());
        return ResponseEntity.ok(result);
    }

    // Supprimer un compte (seulement si solde = 0)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        compteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
