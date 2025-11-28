package com.simplecash.controller;

import com.simplecash.dto.AgenceCreateDTO;
import com.simplecash.dto.AgenceDTO;
import com.simplecash.dto.AgenceUpdateDTO;
import com.simplecash.service.AgenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final AgenceService agenceService;

    @GetMapping
    public List<AgenceDTO> getAll() {
        return agenceService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<AgenceDTO> getById(@PathVariable String id) {
        return agenceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AgenceDTO> create(@RequestBody @Valid AgenceCreateDTO dto) {
        AgenceDTO created = agenceService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AgenceDTO> update(@PathVariable String id,
                                            @RequestBody @Valid AgenceUpdateDTO dto) {
        return agenceService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        agenceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
