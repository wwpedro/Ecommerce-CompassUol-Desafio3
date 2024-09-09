package com.compassuol.comercio_desafio_tres.controller;
import com.compassuol.comercio_desafio_tres.dto.VendaDTO;
import com.compassuol.comercio_desafio_tres.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/vendas")
public class VendaController {
   @Autowired
   private VendaService vendaService;
   
   @GetMapping
   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
   public List<VendaDTO> getAllVendas() {
       return vendaService.getAllVendas();
   }
   
   @GetMapping("/{id}")
   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
   public ResponseEntity<VendaDTO> getVendaById(@PathVariable Long id) {
       VendaDTO vendaDto = vendaService.getVendaById(id);
       return ResponseEntity.ok(vendaDto);
   }
   
   @PostMapping
   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
   public ResponseEntity<VendaDTO> createVenda(@RequestBody VendaDTO vendaDto) {
       VendaDTO novaVenda = vendaService.createVenda(vendaDto);
       return ResponseEntity.ok(novaVenda);
   }
   
   @PutMapping("/{id}")
   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
   public ResponseEntity<VendaDTO> updateVenda(@PathVariable Long id, @RequestBody VendaDTO vendaDetails) {
       VendaDTO updatedVenda = vendaService.updateVenda(id, vendaDetails);
       return ResponseEntity.ok(updatedVenda);
   }
   
   @DeleteMapping("/{id}")
   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
   public ResponseEntity<Void> deleteVenda(@PathVariable Long id) {
       vendaService.deleteVenda(id);
       return ResponseEntity.noContent().build();
   }
}
