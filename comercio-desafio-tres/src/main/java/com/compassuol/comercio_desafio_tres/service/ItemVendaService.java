package com.compassuol.comercio_desafio_tres.service;
import com.compassuol.comercio_desafio_tres.domain.ItemVenda;
import com.compassuol.comercio_desafio_tres.domain.Produto;
import com.compassuol.comercio_desafio_tres.dto.ItemVendaDTO;
import com.compassuol.comercio_desafio_tres.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ItemVendaService {
   @Autowired
   private ItemVendaRepository itemVendaRepository;
   public List<ItemVendaDTO> getAllItensVenda() {
       return itemVendaRepository.findAll().stream()
           .map(this::convertToDTO)
           .collect(Collectors.toList());
   }
   public ItemVendaDTO getItemVendaById(Long id) {
       ItemVenda itemVenda = itemVendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Item de venda não encontrado"));
       return convertToDTO(itemVenda);
   }
   public ItemVendaDTO createItemVenda(ItemVendaDTO itemVendaDto) {
       ItemVenda itemVenda = convertToEntity(itemVendaDto);
       ItemVenda novoItemVenda = itemVendaRepository.save(itemVenda);
       return convertToDTO(novoItemVenda);
   }
   public ItemVendaDTO updateItemVenda(Long id, ItemVendaDTO itemVendaDto) {
       ItemVenda itemVenda = itemVendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Item de venda não encontrado"));
       itemVenda.setQuantidade(itemVendaDto.getQuantidade());
       itemVenda.setPrecoUnitario(itemVendaDto.getPrecoUnitario());
       itemVenda = itemVendaRepository.save(itemVenda);
       return convertToDTO(itemVenda);
   }
   public void deleteItemVenda(Long id) {
       itemVendaRepository.deleteById(id);
   }
   private ItemVendaDTO convertToDTO(ItemVenda itemVenda) {
       return new ItemVendaDTO(itemVenda.getId(), null, null, itemVenda.getQuantidade(), itemVenda.getPrecoUnitario());
   }
   private ItemVenda convertToEntity(ItemVendaDTO itemVendaDto) {
       Produto produto = new Produto();
       produto.setId(itemVendaDto.getProduto().getId());
       return new ItemVenda(produto, itemVendaDto.getQuantidade(), itemVendaDto.getPrecoUnitario());
   }
}