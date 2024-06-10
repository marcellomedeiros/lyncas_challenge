package com.lyncas.contas_a_pagar.domain.conta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Page<Conta> findContaById(Long id, Pageable pageable);

    //Contas a pagar com descricao
    Page<Conta> findContaByDescricaoAndDataVencimentoBetween(String descricao, Date startDate, Date endDate, Pageable pageable);

    //Contas a pagar sem descricao
    Page<Conta> findContasByDataVencimentoBetween(Date startDate, Date endDate, Pageable pageable);

    //Contas pagas
    Page<Conta> findContasByDataPagamentoBetween(Date startDate, Date endDate, Pageable pageable);

    //Contas pagas para somar
    List<Conta> findContasByDataPagamentoBetween(Date startDate, Date endDate);



}
