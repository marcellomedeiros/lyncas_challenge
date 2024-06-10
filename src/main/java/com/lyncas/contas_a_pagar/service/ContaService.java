package com.lyncas.contas_a_pagar.service;

import com.lyncas.contas_a_pagar.domain.conta.Conta;
import com.lyncas.contas_a_pagar.domain.conta.ContaRegister;
import com.lyncas.contas_a_pagar.domain.conta.ContaRepository;
import com.lyncas.contas_a_pagar.domain.conta.ContaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.Date;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;


    public Page<ContaResponse> listar(Long idConta, Pageable paginacao) {
        if(idConta == null){
            Page<Conta> listaContas = contaRepository.findAll(paginacao);
            //return listaContas.map(conta -> new ContaResponse(conta));
            return ContaResponse.converter(listaContas);
        }else{
            Page<Conta> listaContas = contaRepository.findById(idConta, paginacao);
            return ContaResponse.converter(listaContas);
        }
    }

    public ResponseEntity<ContaResponse> cadastrarConta(ContaRegister contaRegister, UriComponentsBuilder uriBuilder) throws Exception{
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

    public Page<ContaResponse> listarContasAPagar(Date vencimento, String descricao, Pageable paginacao) {

        if ( descricao.isEmpty() || descricao.isBlank()) {
            Page<Conta> listaContas = contaRepository.findByDataVencimento(vencimento, paginacao);
            //return listaContas.map(conta -> new ContaResponse(conta));
            return ContaResponse.converter(listaContas);
        }else{
            Page<Conta> listaContas = contaRepository.findByDataVencimentoAndDescricao(vencimento, descricao, paginacao);
            return ContaResponse.converter(listaContas);
        }
    }

    public Page<ContaResponse> listarContasPagas(Date vencimento, String descricao, Pageable paginacao) {

        if ( descricao.isEmpty() || descricao.isBlank()) {
            Page<Conta> listaContas = contaRepository.findByDataVencimento(vencimento, paginacao);
            //return listaContas.map(conta -> new ContaResponse(conta));
            return ContaResponse.converter(listaContas);
        }else{
            Page<Conta> listaContas = contaRepository.findByDataVencimentoAndDescricao(vencimento, descricao, paginacao);
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
}
