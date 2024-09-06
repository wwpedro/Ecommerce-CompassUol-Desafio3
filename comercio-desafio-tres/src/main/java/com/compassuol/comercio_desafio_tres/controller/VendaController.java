package com.compassuol.comercio_desafio_tres.controller;

import com.compassuol.comercio_desafio_tres.domain.Venda;
import com.compassuol.comercio_desafio_tres.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<Venda> getAllVendas() {
        return vendaService.getAllVendas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getVendaById(@PathVariable Long id) {
        Venda venda = vendaService.getVendaById(id);
        return ResponseEntity.ok(venda);
    }

    @PostMapping
    public ResponseEntity<Venda> createVenda(@RequestBody Venda venda) {
        Venda novaVenda = vendaService.createVenda(venda);
        return ResponseEntity.ok(novaVenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> updateVenda(@PathVariable Long id, @RequestBody Venda vendaDetails) {
        Venda updatedVenda = vendaService.updateVenda(id, vendaDetails);
        return ResponseEntity.ok(updatedVenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenda(@PathVariable Long id) {
        vendaService.deleteVenda(id);
        return ResponseEntity.noContent().build();
    }
}
