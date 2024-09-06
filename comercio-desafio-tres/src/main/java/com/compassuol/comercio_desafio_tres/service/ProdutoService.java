package com.compassuol.comercio_desafio_tres.service;

import com.compassuol.comercio_desafio_tres.domain.Produto;
import com.compassuol.comercio_desafio_tres.exception.ResourceNotFoundException;
import com.compassuol.comercio_desafio_tres.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
    }

    public Produto createProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(Long id, Produto produtoDetails) {
        Produto produto = getProdutoById(id);
        produto.setNome(produtoDetails.getNome());
        produto.setDescricao(produtoDetails.getDescricao());
        produto.setPreco(produtoDetails.getPreco());
        return produtoRepository.save(produto);
    }

    public void deleteProduto(Long id) {
        Produto produto = getProdutoById(id);
        produtoRepository.delete(produto);
    }
}
