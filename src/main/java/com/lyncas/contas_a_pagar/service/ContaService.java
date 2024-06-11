package com.lyncas.contas_a_pagar.service;

import com.lyncas.contas_a_pagar.domain.conta.Conta;
import com.lyncas.contas_a_pagar.domain.conta.ContaRegisterDTO;
import com.lyncas.contas_a_pagar.domain.conta.ContaRepository;
import com.lyncas.contas_a_pagar.domain.conta.ContaResponseDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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


    public Page<ContaResponseDTO> listar(String idConta, Pageable paginacao) {
        if(idConta == null || idConta.isEmpty()){
            List<Conta> listaContas = contaRepository.findAll();
            return ContaResponseDTO.converterListToPageDTO(listaContas);
        }else{
            Page<Conta> listaContas = contaRepository.findContaById(idConta, paginacao);
            return ContaResponseDTO.converterToDTO(listaContas);
        }
    }

    public ResponseEntity<ContaResponseDTO> cadastrarConta(@NotNull ContaRegisterDTO contaRegister, @NotNull UriComponentsBuilder uriBuilder) throws Exception{
        Conta conta = new Conta(contaRegister.dataVencimento(),
                                contaRegister.dataPagamento(),
                                contaRegister.valor(),
                                contaRegister.descricao(),
                                contaRegister.situacao());
        contaRepository.save(conta);

        URI uri = uriBuilder.path("/conta/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(ContaResponseDTO.converterContaToDTo(conta));
    }

    public ResponseEntity<ContaResponseDTO> atualizar(String idConta, ContaRegisterDTO contaRegister) {
        Optional<Conta> contaOptional = contaRepository.findById(idConta);
        if (contaOptional.isPresent()){
            Conta conta = contaOptional.get();
            conta.setDescricao(contaRegister.descricao());
            conta.setValor(contaRegister.valor());
            conta.setDataVencimento(contaRegister.dataVencimento());
            conta.setDataPagamento(contaRegister.dataPagamento());
            conta.setValor(contaRegister.valor());
            conta.setSituacao(contaRegister.situacao());

            return ResponseEntity.ok(new ContaResponseDTO(conta));
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<ContaResponseDTO> alterarSituacaoConta(String idConta, ContaRegisterDTO contaRegister) {
        Optional<Conta> contaOptional = contaRepository.findById(idConta);
        if (contaOptional.isPresent()){
            Conta conta = contaOptional.get();
            conta.setSituacao(contaRegister.situacao());
            return ResponseEntity.ok(new ContaResponseDTO(conta));
        }
        return ResponseEntity.notFound().build();
    }

    public Page<ContaResponseDTO> listarContasAPagar(String descricao, Date dataInicial, Date dataFinal, Pageable paginacao) {

        if ( descricao.isEmpty() || descricao.isBlank()) {
            Page<Conta> listaContas = contaRepository.findContasByDataVencimentoBetween(dataInicial, dataFinal, paginacao);

            return ContaResponseDTO.converterToDTO(listaContas);
        }else{
            Page<Conta> listaContas = contaRepository
                    .findContaByDescricaoAndDataVencimentoBetween(descricao, dataInicial, dataFinal, paginacao);
            return ContaResponseDTO.converterToDTO(listaContas);
        }
    }

    public Page<ContaResponseDTO> listarContasPagas(String descricao, Date dataInicio, Date datafim, Pageable paginacao) {

        if ( descricao.isEmpty() || descricao.isBlank()) {
            Page<Conta> listaContas = contaRepository
                    .findContasByDataPagamentoBetween(dataInicio, datafim, paginacao);
            return ContaResponseDTO.converterToDTO(listaContas);
        }else{
            Page<Conta> listaContas = contaRepository
            .findContaByDescricaoAndDataVencimentoBetween(descricao, dataInicio, datafim, paginacao);
            return ContaResponseDTO.converterToDTO(listaContas);
        }
    }

    public ResponseEntity<ContaResponseDTO> obterConta(String id) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isPresent()) {
            return ResponseEntity.ok(new ContaResponseDTO(contaOptional.get()));
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
