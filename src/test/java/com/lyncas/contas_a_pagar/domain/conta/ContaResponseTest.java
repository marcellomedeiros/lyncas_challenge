package com.lyncas.contas_a_pagar.domain.conta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaResponseTest {


    private static final String DESCRICAO = "DESCRICAO";
    private static final String SITUACAO = "SITUACAO";
    @Mock
    private Date dataVencimento;
    @Mock
    private Date dataPagamento;
    @Mock
    private BigDecimal valor;
    @InjectMocks
    private ContaResponse underTest;

    @Nested
    class WhenConvertering {
        @Mock
        private Page<Conta> contas;

        @BeforeEach
        void setup() {
        }
    }

    @Nested
    class WhenConverteringUmaConta {
        @Mock
        private Conta conta;

        @BeforeEach
        void setup() {
        }
    }

    @Nested
    class WhenIding {
        @BeforeEach
        void setup() {
        }
    }

    @Nested
    class WhenDataingVencimento {
        @BeforeEach
        void setup() {
        }
    }

    @Nested
    class WhenDataingPagamento {
        @BeforeEach
        void setup() {
        }
    }

    @Nested
    class WhenValoring {
        @BeforeEach
        void setup() {
        }
    }

    @Nested
    class WhenDescricaoing {
        @BeforeEach
        void setup() {
        }
    }

    @Nested
    class WhenSituacaoing {
        @BeforeEach
        void setup() {
        }
    }
}