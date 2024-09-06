package com.compassuol.comercio_desafio_tres.controller;

import com.compassuol.comercio_desafio_tres.domain.ItemVenda;
import com.compassuol.comercio_desafio_tres.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-venda")
public class ItemVendaController {

    @Autowired
    private ItemVendaService itemVendaService;

    @GetMapping
    public List<ItemVenda> getAllItensVenda() {
        return itemVendaService.getAllItensVenda();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemVenda> getItemVendaById(@PathVariable Long id) {
        ItemVenda itemVenda = itemVendaService.getItemVendaById(id);
        return ResponseEntity.ok(itemVenda);
    }

    @PostMapping
    public ResponseEntity<ItemVenda> createItemVenda(@RequestBody ItemVenda itemVenda) {
        ItemVenda novoItemVenda = itemVendaService.createItemVenda(itemVenda);
        return ResponseEntity.ok(novoItemVenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemVenda> updateItemVenda(@PathVariable Long id, @RequestBody ItemVenda itemVendaDetails) {
        ItemVenda updatedItemVenda = itemVendaService.updateItemVenda(id, itemVendaDetails);
        return ResponseEntity.ok(updatedItemVenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemVenda(@PathVariable Long id) {
        itemVendaService.deleteItemVenda(id);
        return ResponseEntity.noContent().build();
    }
}
