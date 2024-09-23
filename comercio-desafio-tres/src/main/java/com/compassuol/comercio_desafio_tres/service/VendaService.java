package com.compassuol.comercio_desafio_tres.service;
import com.compassuol.comercio_desafio_tres.dto.ItemVendaDTO;
import com.compassuol.comercio_desafio_tres.dto.VendaDTO;
import com.compassuol.comercio_desafio_tres.domain.Estoque;
import com.compassuol.comercio_desafio_tres.domain.ItemVenda;
import com.compassuol.comercio_desafio_tres.domain.Produto;
import com.compassuol.comercio_desafio_tres.domain.Usuario;
import com.compassuol.comercio_desafio_tres.domain.Venda;
import com.compassuol.comercio_desafio_tres.repository.EstoqueRepository;
import com.compassuol.comercio_desafio_tres.repository.ItemVendaRepository;
import com.compassuol.comercio_desafio_tres.repository.ProdutoRepository;
import com.compassuol.comercio_desafio_tres.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class VendaService {
   @Autowired
   private VendaRepository vendaRepository;
  
   @Autowired
   private EstoqueRepository estoqueRepository;
  
   @Autowired
   private ProdutoRepository produtoRepository;
   public List<VendaDTO> getAllVendas() {
       return vendaRepository.findAll().stream()
           .map(this::convertToDTO)
           .collect(Collectors.toList());
   }
   public VendaDTO getVendaById(Long id) {
       Venda venda = vendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
       return convertToDTO(venda);
   }
   @Autowired
   private ItemVendaRepository itemVendaRepository;

   public VendaDTO createVenda(VendaDTO vendaDto) {
	    Venda venda = convertToEntity(vendaDto);

	    venda.setData(LocalDateTime.now());

	    double totalVenda = 0.0;

	    List<ItemVenda> itensVenda = new ArrayList<>();


	    for (ItemVendaDTO itemVendaDto : vendaDto.getItensVenda()) {

	        Produto produto = produtoRepository.findById(itemVendaDto.getProduto().getId())
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

	        Estoque estoque = produto.getEstoque();
	        if (estoque.getQuantidadeAtual() < itemVendaDto.getQuantidade()) {
	            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
	        }

	        estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() - itemVendaDto.getQuantidade());
	        estoqueRepository.save(estoque); 

	        ItemVenda itemVenda = new ItemVenda();
	        itemVenda.setProduto(produto);
	        itemVenda.setVenda(venda);
	        itemVenda.setQuantidade(itemVendaDto.getQuantidade());
	        itemVenda.setPrecoUnitario(itemVendaDto.getPrecoUnitario());

	        itensVenda.add(itemVenda);

	        totalVenda += itemVendaDto.getQuantidade() * itemVendaDto.getPrecoUnitario();
	    }

	    venda.setTotal(totalVenda);

	    Venda novaVenda = vendaRepository.save(venda);

	    for (ItemVenda itemVenda : itensVenda) {
	        itemVendaRepository.save(itemVenda);
	    }

	    return convertToDTO(novaVenda);
	}
   
   public VendaDTO updateVenda(Long id, VendaDTO vendaDto) {
       Venda venda = vendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
       venda.setData(vendaDto.getData());
       venda.setTotal(vendaDto.getTotal());
       venda = vendaRepository.save(venda);
       return convertToDTO(venda);
   }
   public void deleteVenda(Long id) {
       vendaRepository.deleteById(id);
   }
   private VendaDTO convertToDTO(Venda venda) {
       return new VendaDTO(venda.getId(), venda.getData(), venda.getTotal(), null, null);
   }
   private Venda convertToEntity(VendaDTO vendaDto) {
       Usuario usuario = new Usuario();
       usuario.setId(vendaDto.getUsuario().getId()); 
       return new Venda(vendaDto.getData(), vendaDto.getTotal(), usuario);
   }
}
