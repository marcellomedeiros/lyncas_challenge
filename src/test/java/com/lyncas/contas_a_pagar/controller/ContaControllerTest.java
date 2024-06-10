package com.lyncas.contas_a_pagar.controller;

import com.lyncas.contas_a_pagar.service.ContaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaControllerTest {

    @Mock
    private ContaService contaService;
    @InjectMocks
    private ContaController underTest;

    @Test
    void listarContas() {
    }

    @Test
    void cadastrar() {
    }

    @Test
    void atualizar() {
    }

    @Nested
    class WhenListaringContas {
        @Mock
        private Pageable paginacao;

        @BeforeEach
        void setup() {
        }
    }
}