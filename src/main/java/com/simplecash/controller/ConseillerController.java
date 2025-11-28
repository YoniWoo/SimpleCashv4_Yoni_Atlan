package com.simplecash.controller;

import com.simplecash.dto.ConseillerCreateDTO;
import com.simplecash.dto.ConseillerDTO;
import com.simplecash.dto.ConseillerUpdateDTO;
import com.simplecash.service.ConseillerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conseillers")
@RequiredArgsConstructor
public class ConseillerController {

    private final ConseillerService conseillerService;

    @GetMapping
    public List<ConseillerDTO> getAll() {
        return conseillerService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ConseillerDTO> getById(@PathVariable Long id) {
        return conseillerService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConseillerDTO> create(@RequestBody @Valid ConseillerCreateDTO dto) {
        ConseillerDTO created = conseillerService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ConseillerDTO> update(@PathVariable Long id,
                                                @RequestBody @Valid ConseillerUpdateDTO dto) {
        return conseillerService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        conseillerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
