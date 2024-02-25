package br.com.agls.expensesauthapi.api.dto.request;

import lombok.Builder;

@Builder
public record AuthenticationRequestDTO(String email, String password) {
}
