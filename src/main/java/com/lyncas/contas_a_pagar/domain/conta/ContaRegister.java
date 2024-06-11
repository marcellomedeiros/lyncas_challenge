package com.lyncas.contas_a_pagar.domain.conta;

import java.math.BigDecimal;
import java.sql.Date;

public record ContaRegister(
        String id,
        Date dataVencimento,
        Date dataPagamento,
        BigDecimal valor,
        String descricao,
        String situacao
){}
