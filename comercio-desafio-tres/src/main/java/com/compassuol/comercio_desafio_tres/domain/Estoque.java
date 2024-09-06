package com.compassuol.comercio_desafio_tres.domain;

import jakarta.persistence.*;

@Table(name="Estoque")
@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int quantidadeAtual;

    public Estoque() {}

    public Estoque(Produto produto, int quantidadeAtual) {
        this.produto = produto;
        this.quantidadeAtual = quantidadeAtual;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(int quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}
}
