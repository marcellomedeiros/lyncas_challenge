package com.lyncas.contas_a_pagar.domain.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.sql.Date;

public record ContaRegisterDTO(

        @NotBlank
        Date dataVencimento,
        Date dataPagamento,
        @NotNull
        BigDecimal valor,
        @NotBlank
        String descricao,
        @NotBlank
        String situacao
){}
