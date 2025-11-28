package com.simplecash.controller;

import com.simplecash.dto.*;
import com.simplecash.service.GerantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gerants")
@RequiredArgsConstructor
public class GerantController {

    private final GerantService gerantService;

    // --- CRUD Gérant ---

    @GetMapping
    public List<GerantDTO> getAll() {
        return gerantService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<GerantDTO> getById(@PathVariable Long id) {
        return gerantService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GerantDTO> create(@RequestBody @Valid GerantCreateDTO dto) {
        GerantDTO created = gerantService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("{id}")
    public ResponseEntity<GerantDTO> update(@PathVariable Long id,
                                            @RequestBody @Valid GerantUpdateDTO dto) {
        return gerantService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gerantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --- Cas métier : un gérant ajoute un conseiller dans son agence ---

    @PostMapping("{gerantId}/conseillers")
    public ResponseEntity<ConseillerDTO> addConseiller(@PathVariable Long gerantId,
                                                       @RequestBody @Valid ConseillerCreateDTO dto) {
        ConseillerDTO created = gerantService.addConseiller(gerantId, dto);
        return ResponseEntity.ok(created);
    }
}
