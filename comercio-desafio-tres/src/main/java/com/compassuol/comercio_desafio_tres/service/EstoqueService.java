package com.compassuol.comercio_desafio_tres.service;
import com.compassuol.comercio_desafio_tres.domain.Estoque;
import com.compassuol.comercio_desafio_tres.domain.Produto;
import com.compassuol.comercio_desafio_tres.dto.EstoqueDTO;
import com.compassuol.comercio_desafio_tres.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EstoqueService {
   @Autowired
   private EstoqueRepository estoqueRepository;
   public List<EstoqueDTO> getAllEstoques() {
       return estoqueRepository.findAll().stream()
           .map(this::convertToDTO)
           .collect(Collectors.toList());
   }
   public EstoqueDTO getEstoqueById(Long id) {
       Estoque estoque = estoqueRepository.findById(id).orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
       return convertToDTO(estoque);
   }
   public EstoqueDTO createEstoque(EstoqueDTO estoqueDto) {
       Estoque estoque = convertToEntity(estoqueDto);
       Estoque novoEstoque = estoqueRepository.save(estoque);
       return convertToDTO(novoEstoque);
   }
   public EstoqueDTO updateEstoque(Long id, EstoqueDTO estoqueDto) {
       Estoque estoque = estoqueRepository.findById(id).orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
       estoque.setQuantidadeAtual(estoqueDto.getQuantidadeAtual());
       estoque = estoqueRepository.save(estoque);
       return convertToDTO(estoque);
   }
   public void deleteEstoque(Long id) {
       estoqueRepository.deleteById(id);
   }
   private EstoqueDTO convertToDTO(Estoque estoque) {
       return new EstoqueDTO(estoque.getId(), null, estoque.getQuantidadeAtual());
   }
   private Estoque convertToEntity(EstoqueDTO estoqueDto) {
       Produto produto = new Produto();
       produto.setId(estoqueDto.getProduto().getId());
       return new Estoque(produto, estoqueDto.getQuantidadeAtual());
   }
}
