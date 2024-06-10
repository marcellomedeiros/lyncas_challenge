package com.lyncas.contas_a_pagar.domain.conta;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaTest {

    private static final String DESCRICAO = "DESCRICAO";
    private static final String SITUACAO = "SITUACAO";
    @Mock
    private Date dataVencimento;
    @Mock
    private Date dataPagamento;
    @Mock
    private BigDecimal valor;
    @InjectMocks
    private Conta underTest;
}