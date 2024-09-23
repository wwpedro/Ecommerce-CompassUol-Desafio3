package com.compassuol.comercio_desafio_tres.service;
import com.compassuol.comercio_desafio_tres.domain.Produto;
import com.compassuol.comercio_desafio_tres.dto.ProdutoDTO;
import com.compassuol.comercio_desafio_tres.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProdutoService {
   @Autowired
   private ProdutoRepository produtoRepository;
   
   public List<ProdutoDTO> getAllProdutos() {
       return produtoRepository.findAll().stream()
           .filter(Produto::isAtivo) 
           .map(this::convertToDTO)
           .collect(Collectors.toList());
   }
   
   public ProdutoDTO getProdutoById(Long id) {
       Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
       return convertToDTO(produto);
   }
   
   public ProdutoDTO createProduto(ProdutoDTO produtoDto) {
       Produto produto = convertToEntity(produtoDto);
       Produto novoProduto = produtoRepository.save(produto);
       return convertToDTO(novoProduto);
   }
   
   public ProdutoDTO updateProduto(Long id, ProdutoDTO produtoDto) {
       Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
       produto.setNome(produtoDto.getNome());
       produto.setDescricao(produtoDto.getDescricao());
       produto.setPreco(produtoDto.getPreco());
       produto = produtoRepository.save(produto);
       return convertToDTO(produto);
   }
   
   public void deleteProduto(Long id) {
       Produto produto = produtoRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
      
       
       if (!produto.getItensVenda().isEmpty()) {
           
           produto.setAtivo(false);
           produtoRepository.save(produto);
       } else {
           
           produtoRepository.deleteById(id);
       }
   }
   
   private ProdutoDTO convertToDTO(Produto produto) {
       return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.isAtivo());
   }
   
   private Produto convertToEntity(ProdutoDTO produtoDto) {
       return new Produto(produtoDto.getNome(), produtoDto.getDescricao(), produtoDto.getPreco(), produtoDto.isAtivo());
   }
}
