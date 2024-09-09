package com.compassuol.comercio_desafio_tres.domain;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@Table(name="Produto")
@Entity
public class Produto {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nome;
   private String descricao;
   private double preco;
   @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
   @JsonIgnore
   private Estoque estoque;
   @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
   private Set<ItemVenda> itensVenda;
  
   private boolean ativo=true;
   public Produto() {}
   public Produto(String nome, String descricao, double preco, boolean ativo) {
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
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	public Set<ItemVenda> getItensVenda() {
		return itensVenda;
	}
	public void setItensVenda(Set<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	
	public boolean isAtivo() {
       return ativo;
   }
   public void setAtivo(boolean ativo) {
       this.ativo = ativo;
   }
  
}
