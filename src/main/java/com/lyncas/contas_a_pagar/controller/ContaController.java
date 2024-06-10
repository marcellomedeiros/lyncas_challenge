package com.lyncas.contas_a_pagar.controller;

import com.lyncas.contas_a_pagar.domain.conta.ContaResponse;
import com.lyncas.contas_a_pagar.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    public Page<ContaResponse> detalhesContas(@RequestParam(required = false) Long idConta,
                                              @PageableDefault(sort = "id", direction = Sort.Direction.DESC,
                                                      page = 0, size = 10) Pageable paginacao) {
        return contaService.listar(idConta, paginacao);
    }
}
