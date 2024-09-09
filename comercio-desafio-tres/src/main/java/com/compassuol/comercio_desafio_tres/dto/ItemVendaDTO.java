package com.compassuol.comercio_desafio_tres.dto;
public class ItemVendaDTO {
   private Long id;
   private ProdutoDTO produto;
   private VendaDTO venda;
   private int quantidade;
   private double precoUnitario;
   public ItemVendaDTO() {}
	public ItemVendaDTO(Long id, ProdutoDTO produto, VendaDTO venda, int quantidade, double precoUnitario) {
		super();
		this.id = id;
		this.produto = produto;
		this.venda = venda;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProdutoDTO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}
	public VendaDTO getVenda() {
		return venda;
	}
	public void setVenda(VendaDTO venda) {
		this.venda = venda;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
  
}
