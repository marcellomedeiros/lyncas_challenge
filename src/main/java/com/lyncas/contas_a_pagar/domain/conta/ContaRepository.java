package com.lyncas.contas_a_pagar.domain.conta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Page<Conta> findById(Long id, Pageable pageable);

    Page<Conta> findByDescricao(String descricao, Pageable pageable);

    Page<Conta> findByDataVencimentoAndDescricao(Date dataVencimento, String descricao, Pageable pageable);

    Page<Conta> findByDataVencimento(Date dataVencimento, Pageable pageable);

    Page<Conta> findByDataPagamentoNotEmpty(Pageable pageable);

    Page<Conta> findByDataPagamentoBetween(Date dataInicio, Date dataFim, Pageable pageable);

}
