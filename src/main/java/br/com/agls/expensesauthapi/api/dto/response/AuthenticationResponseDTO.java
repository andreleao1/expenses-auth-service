package br.com.agls.expensesauthapi.api.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthenticationResponseDTO {

    public String token;
}
