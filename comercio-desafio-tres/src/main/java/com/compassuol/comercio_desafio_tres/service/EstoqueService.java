package com.compassuol.comercio_desafio_tres.service;

import com.compassuol.comercio_desafio_tres.domain.Estoque;
import com.compassuol.comercio_desafio_tres.exception.ResourceNotFoundException;
import com.compassuol.comercio_desafio_tres.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Estoque> getAllEstoques() {
        return estoqueRepository.findAll();
    }

    public Estoque getEstoqueById(Long id) {
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado com id: " + id));
    }

    public Estoque createEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Estoque updateEstoque(Long id, Estoque estoqueDetails) {
        Estoque estoque = getEstoqueById(id);
        estoque.setQuantidadeAtual(estoqueDetails.getQuantidadeAtual());
        return estoqueRepository.save(estoque);
    }

    public void deleteEstoque(Long id) {
        Estoque estoque = getEstoqueById(id);
        estoqueRepository.delete(estoque);
    }
}
