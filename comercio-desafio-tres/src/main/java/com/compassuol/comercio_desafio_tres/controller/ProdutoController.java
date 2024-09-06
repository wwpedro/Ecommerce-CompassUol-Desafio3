package com.compassuol.comercio_desafio_tres.controller;

import com.compassuol.comercio_desafio_tres.domain.Produto;
import com.compassuol.comercio_desafio_tres.dto.ProdutoDTO;
import com.compassuol.comercio_desafio_tres.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoService.getAllProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable Long id) {
        Produto produto = produtoService.getProdutoById(id);
        ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco());

        return ResponseEntity.ok(produtoDTO);
    }

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.createProduto(produto);
        return ResponseEntity.ok(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoDetails) {
        Produto updatedProduto = produtoService.updateProduto(id, produtoDetails);
        return ResponseEntity.ok(updatedProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}
