package com.compassuol.comercio_desafio_tres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.compassuol.comercio_desafio_tres.dto.EstoqueDTO;
import com.compassuol.comercio_desafio_tres.service.EstoqueService;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<EstoqueDTO> getAllEstoques() {
        return estoqueService.getAllEstoques();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDTO> getEstoqueById(@PathVariable Long id) {
        EstoqueDTO estoqueDto = estoqueService.getEstoqueById(id);
        return ResponseEntity.ok(estoqueDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EstoqueDTO> createEstoque(@RequestBody EstoqueDTO estoqueDto) {
        EstoqueDTO novoEstoque = estoqueService.createEstoque(estoqueDto);
        return ResponseEntity.ok(novoEstoque);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EstoqueDTO> updateEstoque(@PathVariable Long id, @RequestBody EstoqueDTO estoqueDetails) {
        EstoqueDTO updatedEstoque = estoqueService.updateEstoque(id, estoqueDetails);
        return ResponseEntity.ok(updatedEstoque);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstoque(@PathVariable Long id) {
        estoqueService.deleteEstoque(id);
        return ResponseEntity.noContent().build();
    }
}
