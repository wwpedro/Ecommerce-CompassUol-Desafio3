package com.compassuol.comercio_desafio_tres.dto;
public class UsuarioDTO {
   private Long id;
   private String nome;
   private String email;
   private String senha;
   private String permissao;
   public UsuarioDTO() {}
   public UsuarioDTO(Long id, String nome, String email, String senha, String permissao) {
       this.id = id;
       this.nome = nome;
       this.email = email;
       this.senha = senha;
       this.permissao = permissao;
   }
  
   public UsuarioDTO(Long id, String nome, String email, String permissao) {
       this.id = id;
       this.nome = nome;
       this.email = email;
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
}
