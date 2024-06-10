package com.lyncas.contas_a_pagar.controller;

import com.lyncas.contas_a_pagar.domain.conta.ContaRegister;
import com.lyncas.contas_a_pagar.domain.conta.ContaResponse;
import com.lyncas.contas_a_pagar.service.ContaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.sql.Date;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public Page<ContaResponse> listarContas(@RequestParam(required = false) Long idConta,
                                              @PageableDefault(sort = "id", direction = Sort.Direction.DESC,
                                                      page = 0, size = 10) Pageable paginacao) {
        return contaService.listar(idConta, paginacao);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ContaResponse> cadastrar(@RequestBody @Validated ContaRegister contaRegister,
                                                   UriComponentsBuilder uriComponentsBuilder) throws Exception {
        return contaService.cadastrarConta(contaRegister, uriComponentsBuilder);
    }

    @PutMapping("/{idConta}")
    @Transactional
    public ResponseEntity<ContaResponse> atualizar(@PathVariable Long idConta,
                                                   @RequestBody @Validated ContaRegister contaRegister)
                                                   throws Exception {
        return contaService.atualizar(idConta, contaRegister);
    }

    @PutMapping("/{idConta}/situacao")
    @Transactional
    public ResponseEntity<ContaResponse> alterarSituacao(@PathVariable Long idConta,
                                                   @RequestBody @Validated ContaRegister contaRegister)
            throws Exception {
        return contaService.alterarSituacaoConta(idConta, contaRegister);
    }

    @GetMapping("/contas_a_pagar")
    public Page<ContaResponse> listarContasAPagar(@RequestParam(required = false) String descricao,
                                                  @RequestParam Date dataInicial,
                                                  @RequestParam Date dataFinal,
                                                  @PageableDefault(sort = "dataVencimento",
                                                          direction = Sort.Direction.ASC,
                                                          page = 0, size = 10) Pageable paginacao){

        return contaService.listarContasAPagar(descricao, dataInicial, dataFinal, paginacao);
    }

    @GetMapping("/valor_pago_por_periodo")
    public BigDecimal valorPagoPeriodo(@RequestParam Date dataInicio,
                                       @RequestParam Date dataFim) throws Exception {

        return contaService.valorPagoPeriodo(dataFim, dataInicio);
    }

}
