package com.lyncas.contas_a_pagar.domain.conta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;


public record ContaResponse (
        Long id,
        Date dataVencimento,
        Date dataPagamento,
        BigDecimal valor,
        String descricao,
        String situacao
) {
    public ContaResponse (Conta conta) {
        this (
                conta.getId(),
                conta.getDataVencimento(),
                conta.getDataPagamento(),
                conta.getValor(),
                conta.getDescricao(),
                conta.getSituacao()
        );
    }

    public static Page<ContaResponse> converter(Page<Conta> contas) {
        return contas.map(ContaResponse::new);
    }

    public static ContaResponse converterUmaConta(Conta conta) {
        return new ContaResponse(conta);
    }
}
