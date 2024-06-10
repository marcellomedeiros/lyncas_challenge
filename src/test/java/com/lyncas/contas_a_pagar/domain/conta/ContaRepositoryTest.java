package com.lyncas.contas_a_pagar.domain.conta;

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
class ContaRepositoryTest {

    @InjectMocks
    private ContaRepository underTest;

    @Nested
    class WhenFindingById {
        @Mock
        private Pageable pageable;

        @BeforeEach
        void setup() {
        }
    }
/*
    @Nested
    class WhenFindingById {
        private final String NOME = "NOME";

        @BeforeEach
        void setup() {
        }
    }

 */
}