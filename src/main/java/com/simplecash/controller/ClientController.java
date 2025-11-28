package com.simplecash.controller;

import com.simplecash.dto.ClientCreateDTO;
import com.simplecash.dto.ClientDTO;
import com.simplecash.dto.ClientUpdateDTO;
import com.simplecash.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> getAll() {
        return clientService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientDTO> getById(@PathVariable Long id) {
        return clientService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody @Valid ClientCreateDTO dto) {
        ClientDTO created = clientService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id,
                                            @RequestBody @Valid ClientUpdateDTO dto) {
        return clientService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/conseiller/{conseillerId}")
    public List<ClientDTO> getByConseiller(@PathVariable Long conseillerId) {
        return clientService.getByConseiller(conseillerId);
    }
}
