package com.lyncas.contas_a_pagar.service;

import com.lyncas.contas_a_pagar.domain.conta.Conta;
import com.lyncas.contas_a_pagar.domain.conta.ContaRegister;
import com.lyncas.contas_a_pagar.domain.conta.ContaRepository;
import com.lyncas.contas_a_pagar.domain.conta.ContaResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;


    public Page<ContaResponse> listar(String idConta, Pageable paginacao) {
        if(idConta == null || idConta.isEmpty()){
            Page<Conta> listaContas = contaRepository.findAll(paginacao);
            return ContaResponse.converter(listaContas);
        }else{
            Page<Conta> listaContas = contaRepository.findContaById(idConta, paginacao);
            return ContaResponse.converter(listaContas);
        }
    }

    public ResponseEntity<ContaResponse> cadastrarConta(@NotNull ContaRegister contaRegister, @NotNull UriComponentsBuilder uriBuilder) throws Exception{
        Conta conta = new Conta(contaRegister.dataVencimento(),
                                contaRegister.dataPagamento(),
                                contaRegister.valor(),
                                contaRegister.descricao(),
                                contaRegister.situacao());
        contaRepository.save(conta);

        URI uri = uriBuilder.path("/conta/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContaResponse(conta));
    }

    public ResponseEntity<ContaResponse> atualizar(Long idConta, ContaRegister contaRegister) {
        Optional<Conta> contaOptional = contaRepository.findById(idConta);
        if (contaOptional.isPresent()){
            Conta conta = contaOptional.get();
            conta.setDescricao(contaRegister.descricao());
            conta.setValor(contaRegister.valor());
            conta.setDataVencimento(contaRegister.dataVencimento());
            conta.setDataPagamento(contaRegister.dataPagamento());
            conta.setValor(contaRegister.valor());
            conta.setSituacao(contaRegister.situacao());

            return ResponseEntity.ok(new ContaResponse(conta));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<ContaResponse> alterarSituacaoConta(Long idConta, ContaRegister contaRegister) {
        Optional<Conta> contaOptional = contaRepository.findById(idConta);
        if (contaOptional.isPresent()){
            Conta conta = contaOptional.get();
            conta.setSituacao(contaRegister.situacao());
            return ResponseEntity.ok(new ContaResponse(conta));
        }
        return ResponseEntity.notFound().build();
    }

    public Page<ContaResponse> listarContasAPagar(String descricao, Date dataInicial, Date dataFinal, Pageable paginacao) {

        if ( descricao.isEmpty() || descricao.isBlank()) {
            Page<Conta> listaContas = contaRepository.findContasByDataVencimentoBetween(dataInicial, dataFinal, paginacao);

            return ContaResponse.converter(listaContas);
        }else{
            Page<Conta> listaContas = contaRepository
                    .findContaByDescricaoAndDataVencimentoBetween(descricao, dataInicial, dataFinal, paginacao);
            return ContaResponse.converter(listaContas);
        }
    }

    public Page<ContaResponse> listarContasPagas(String descricao, Date dataInicio, Date datafim, Pageable paginacao) {

        if ( descricao.isEmpty() || descricao.isBlank()) {
            Page<Conta> listaContas = contaRepository
                    .findContasByDataPagamentoBetween(dataInicio, datafim, paginacao);
            return ContaResponse.converter(listaContas);
        }else{
            Page<Conta> listaContas = contaRepository
            .findContaByDescricaoAndDataVencimentoBetween(descricao, dataInicio, datafim, paginacao);
            return ContaResponse.converter(listaContas);
        }
    }

    public ResponseEntity<ContaResponse> obterConta(Long id) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isPresent()) {
            return ResponseEntity.ok(ContaResponse.converterUmaConta(contaOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    public BigDecimal valorPagoPeriodo(@NotNull Date dataInicial, @NotNull Date dataFinal) {
        BigDecimal valorPago = BigDecimal.ZERO;
        if (dataInicial.before(dataFinal)){
            List<Conta> contasPagas = contaRepository.findContasByDataPagamentoBetween(dataFinal, dataFinal);
            for(Conta conta : contasPagas){
                valorPago.add(conta.getValor());
            }
        }
        return valorPago;
    }
}
