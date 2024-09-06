package com.compassuol.comercio_desafio_tres.service;

import com.compassuol.comercio_desafio_tres.domain.ItemVenda;
import com.compassuol.comercio_desafio_tres.exception.ResourceNotFoundException;
import com.compassuol.comercio_desafio_tres.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public List<ItemVenda> getAllItensVenda() {
        return itemVendaRepository.findAll();
    }

    public ItemVenda getItemVendaById(Long id) {
        return itemVendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ItemVenda não encontrado com id: " + id));
    }

    public ItemVenda createItemVenda(ItemVenda itemVenda) {
        return itemVendaRepository.save(itemVenda);
    }

    public ItemVenda updateItemVenda(Long id, ItemVenda itemVendaDetails) {
        ItemVenda itemVenda = getItemVendaById(id);
        itemVenda.setQuantidade(itemVendaDetails.getQuantidade());
        itemVenda.setPrecoUnitario(itemVendaDetails.getPrecoUnitario());
        itemVenda.setProduto(itemVendaDetails.getProduto());
        itemVenda.setVenda(itemVendaDetails.getVenda());
        return itemVendaRepository.save(itemVenda);
    }

    public void deleteItemVenda(Long id) {
        ItemVenda itemVenda = getItemVendaById(id);
        itemVendaRepository.delete(itemVenda);
    }
}
