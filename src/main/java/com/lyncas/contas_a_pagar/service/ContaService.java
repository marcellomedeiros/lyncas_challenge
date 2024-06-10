package com.lyncas.contas_a_pagar.service;

import com.lyncas.contas_a_pagar.domain.conta.Conta;
import com.lyncas.contas_a_pagar.domain.conta.ContaRepository;
import com.lyncas.contas_a_pagar.domain.conta.ContaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;


    public ResponseEntity<ContaResponse> detalharPorId(Long id) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isPresent()) {
            return ResponseEntity.ok(ContaResponse.converterUmaConta(contaOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
