package com.compassuol.comercio_desafio_tres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compassuol.comercio_desafio_tres.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
