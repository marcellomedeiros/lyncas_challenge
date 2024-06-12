package com.lyncas.contas_a_pagar.domain.login;

public record RegisterDTO(
        String login,
        String password,
        UserRole role
) {
}
