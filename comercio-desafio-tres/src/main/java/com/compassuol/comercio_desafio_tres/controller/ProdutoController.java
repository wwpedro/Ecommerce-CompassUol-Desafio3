package com.compassuol.comercio_desafio_tres.controller;
import com.compassuol.comercio_desafio_tres.dto.ProdutoDTO;
import com.compassuol.comercio_desafio_tres.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
  
   @Autowired
   private ProdutoService produtoService;
  
   @GetMapping
   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
   public ResponseEntity<List<ProdutoDTO>> getAllProdutos() {
       return ResponseEntity.ok(produtoService.getAllProdutos());
   }
  
   @GetMapping("/{id}")
   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
   public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable Long id) {
       return ResponseEntity.ok(produtoService.getProdutoById(id));
   }
  
   @PostMapping
   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
   public ResponseEntity<ProdutoDTO> createProduto(@RequestBody ProdutoDTO produtoDto) {
       return ResponseEntity.ok(produtoService.createProduto(produtoDto));
   }
  
   @PutMapping("/{id}")
   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
   public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDto) {
       return ResponseEntity.ok(produtoService.updateProduto(id, produtoDto));
   }
  
   @DeleteMapping("/{id}")
   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
   public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
       produtoService.deleteProduto(id);
       return ResponseEntity.noContent().build();
   }
}
