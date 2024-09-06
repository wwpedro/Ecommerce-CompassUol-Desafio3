package com.compassuol.comercio_desafio_tres.repository;

import com.compassuol.comercio_desafio_tres.domain.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
