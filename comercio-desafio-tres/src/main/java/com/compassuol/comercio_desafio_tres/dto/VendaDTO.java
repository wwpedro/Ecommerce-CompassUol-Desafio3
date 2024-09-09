package com.compassuol.comercio_desafio_tres.dto;
import java.time.LocalDateTime;
import java.util.List;
public class VendaDTO {
   private Long id;
   private LocalDateTime data;
   private double total;
   private UsuarioDTO usuario; 
   private List<ItemVendaDTO> itensVenda;
   public VendaDTO() {}
   public VendaDTO(Long id, LocalDateTime data, double total, UsuarioDTO usuario, List<ItemVendaDTO> itensVenda) {
       this.id = id;
       this.data = data;
       this.total = total;
       this.usuario = usuario;
       this.itensVenda = itensVenda;
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
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public List<ItemVendaDTO> getItensVenda() {
		return itensVenda;
	}
	public void setItensVenda(List<ItemVendaDTO> itensVenda) {
		this.itensVenda = itensVenda;
	}
}
