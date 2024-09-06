package com.compassuol.comercio_desafio_tres.service;

import com.compassuol.comercio_desafio_tres.domain.Venda;
import com.compassuol.comercio_desafio_tres.exception.ResourceNotFoundException;
import com.compassuol.comercio_desafio_tres.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public List<Venda> getAllVendas() {
        return vendaRepository.findAll();
    }

    public Venda getVendaById(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada com id: " + id));
    }

    public Venda createVenda(Venda venda) {
        return vendaRepository.save(venda);
    }

    public Venda updateVenda(Long id, Venda vendaDetails) {
        Venda venda = getVendaById(id);
        venda.setData(vendaDetails.getData());
        venda.setTotal(vendaDetails.getTotal());
        venda.setUsuario(vendaDetails.getUsuario());
        venda.setItensVenda(vendaDetails.getItensVenda());
        return vendaRepository.save(venda);
    }

    public void deleteVenda(Long id) {
        Venda venda = getVendaById(id);
        vendaRepository.delete(venda);
    }
}
