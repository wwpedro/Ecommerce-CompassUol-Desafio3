package com.compassuol.comercio_desafio_tres.domain;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
@Table(name="Venda")
@Entity
public class Venda {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private LocalDateTime data;
   private double total;
   @ManyToOne
   @JoinColumn(name = "usuario_id")
   private Usuario usuario;
   @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
   private Set<ItemVenda> itensVenda;
   public Venda() {}
   public Venda(LocalDateTime data, double total, Usuario usuario) {
       this.data = data;
       this.total = total;
       this.usuario = usuario;
   }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Set<ItemVenda> getItensVenda() {
		return itensVenda;
	}
	public void setItensVenda(Set<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
}
