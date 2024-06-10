package com.lyncas.contas_a_pagar.service;

import com.lyncas.contas_a_pagar.domain.conta.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;
    @InjectMocks
    private ContaService underTest;

    @Nested
    class WhenListaring {
        @Mock
        private Pageable paginacao;

        @BeforeEach
        void setup() {
        }
    }

    @Nested
    class WhenDetalharingPorId {
        @BeforeEach
        void setup() {
        }
    }
}