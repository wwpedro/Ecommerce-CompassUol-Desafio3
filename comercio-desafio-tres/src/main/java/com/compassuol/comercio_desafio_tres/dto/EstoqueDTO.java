package com.compassuol.comercio_desafio_tres.dto;
public class EstoqueDTO {
   private Long id;
   private ProdutoDTO produto;
   private int quantidadeAtual;
  
   public EstoqueDTO() {}
   public EstoqueDTO(Long id,  ProdutoDTO produto, int quantidadeAtual) {
       this.id = id;
       this.produto = produto;
       this.quantidadeAtual = quantidadeAtual;
   }
   // Getters e Setters
   public Long getId() {
       return id;
   }
   public void setId(Long id) {
       this.id = id;
   }
   public int getQuantidadeAtual() {
       return quantidadeAtual;
   }
   public void setQuantidadeAtual(int quantidadeAtual) {
       this.quantidadeAtual = quantidadeAtual;
   }
   public ProdutoDTO getProduto() {
       return produto;
   }
   public void setProduto(ProdutoDTO produto) {
       this.produto = produto;
   }
}
