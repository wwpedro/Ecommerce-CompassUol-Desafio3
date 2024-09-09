package com.compassuol.comercio_desafio_tres.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.compassuol.comercio_desafio_tres.dto.ItemVendaDTO;
import com.compassuol.comercio_desafio_tres.service.ItemVendaService;

import java.util.List;

@RestController
@RequestMapping("/api/itens-venda")
public class ItemVendaController {

    @Autowired
    private ItemVendaService itemVendaService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public List<ItemVendaDTO> getAllItensVenda() {
        return itemVendaService.getAllItensVenda();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ItemVendaDTO> getItemVendaById(@PathVariable Long id) {
        ItemVendaDTO itemVendaDto = itemVendaService.getItemVendaById(id);
        return ResponseEntity.ok(itemVendaDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public ResponseEntity<ItemVendaDTO> createItemVenda(@RequestBody ItemVendaDTO itemVendaDto) {
        ItemVendaDTO novoItemVenda = itemVendaService.createItemVenda(itemVendaDto);
        return ResponseEntity.ok(novoItemVenda);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public ResponseEntity<ItemVendaDTO> updateItemVenda(@PathVariable Long id, @RequestBody ItemVendaDTO itemVendaDetails) {
        ItemVendaDTO updatedItemVenda = itemVendaService.updateItemVenda(id, itemVendaDetails);
        return ResponseEntity.ok(updatedItemVenda);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemVenda(@PathVariable Long id) {
        itemVendaService.deleteItemVenda(id);
        return ResponseEntity.noContent().build();
    }
}
