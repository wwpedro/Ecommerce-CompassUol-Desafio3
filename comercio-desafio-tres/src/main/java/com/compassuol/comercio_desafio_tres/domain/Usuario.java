package com.compassuol.comercio_desafio_tres.domain;
import jakarta.persistence.*;
import java.util.Set;
@Table(name="Usuario")
@Entity
public class Usuario {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nome;
   private String email;
   private String senha;
   private String permissao;
   @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
   private Set<Venda> vendas;
   public Usuario() {}
   public Usuario(String nome, String email, String senha, String permissao) {
       this.nome = nome;
       this.email = email;
       this.senha = senha;
       this.permissao = permissao;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPermissao() {
		return permissao;
	}
	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	public Set<Venda> getVendas() {
		return vendas;
	}
	public void setVendas(Set<Venda> vendas) {
		this.vendas = vendas;
	}
}
