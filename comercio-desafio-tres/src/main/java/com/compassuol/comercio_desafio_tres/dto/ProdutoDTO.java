package com.compassuol.comercio_desafio_tres.dto;
public class ProdutoDTO {
   private Long id;
   private String nome;
   private String descricao;
   private double preco;
   private boolean ativo=true;
   public ProdutoDTO() {}
   public ProdutoDTO(Long id, String nome, String descricao, double preco, boolean ativo) {
       this.id = id;
       this.nome = nome;
       this.descricao = descricao;
       this.preco = preco;
       this.ativo = ativo;
   }
   public Long getId() {
       return id;
   }
   public void setId(Long id) {
       this.id = id;
   }
   public String getNome() {
       return nome;
   }
   public void setNome(String nome) {
       this.nome = nome;
   }
   public String getDescricao() {
       return descricao;
   }
   public void setDescricao(String descricao) {
       this.descricao = descricao;
   }
   public double getPreco() {
       return preco;
   }
   public void setPreco(double preco) {
       this.preco = preco;
   }
  
   public boolean isAtivo() {
       return ativo;
   }
   public void setAtivo(boolean ativo) {
       this.ativo = ativo;
   }
}
