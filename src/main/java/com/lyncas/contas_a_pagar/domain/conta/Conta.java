package com.lyncas.contas_a_pagar.domain.conta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @DateTimeFormat
    private Date dataVencimento;

    @DateTimeFormat
    private Date dataPagamento;

    @NumberFormat
    private BigDecimal valor;

    private String descricao;

    private String situacao;

    public Conta(Date dataVencimento, Date dataPagamento, BigDecimal valor, String descricao, String situacao) {
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.descricao = descricao;
        this.situacao = situacao;
    }
}
