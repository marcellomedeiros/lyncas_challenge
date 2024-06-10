package com.lyncas.contas_a_pagar.domain.conta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Page<Conta> findById(Long id, Pageable pageable);

    Optional<Conta> findById(String nome);

}
