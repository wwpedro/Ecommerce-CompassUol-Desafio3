package com.compassuol.comercio_desafio_tres.controller;

import com.compassuol.comercio_desafio_tres.domain.Estoque;
import com.compassuol.comercio_desafio_tres.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public List<Estoque> getAllEstoques() {
        return estoqueService.getAllEstoques();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> getEstoqueById(@PathVariable Long id) {
        Estoque estoque = estoqueService.getEstoqueById(id);
        return ResponseEntity.ok(estoque);
    }

    @PostMapping
    public ResponseEntity<Estoque> createEstoque(@RequestBody Estoque estoque) {
        Estoque novoEstoque = estoqueService.createEstoque(estoque);
        return ResponseEntity.ok(novoEstoque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> updateEstoque(@PathVariable Long id, @RequestBody Estoque estoqueDetails) {
        Estoque updatedEstoque = estoqueService.updateEstoque(id, estoqueDetails);
        return ResponseEntity.ok(updatedEstoque);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstoque(@PathVariable Long id) {
        estoqueService.deleteEstoque(id);
        return ResponseEntity.noContent().build();
    }
}
