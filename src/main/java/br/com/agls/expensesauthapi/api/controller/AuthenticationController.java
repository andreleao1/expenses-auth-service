package br.com.agls.expensesauthapi.api.controller;

import br.com.agls.expensesauthapi.api.dto.request.AuthenticationRequestDTO;
import br.com.agls.expensesauthapi.api.dto.response.AuthenticationResponseDTO;
import br.com.agls.expensesauthapi.config.security.TokenService;
import br.com.agls.expensesauthapi.domain.entity.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody @Valid AuthenticationRequestDTO request) {
        var password = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = this.authenticationManager.authenticate(password);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(AuthenticationResponseDTO.builder().token(token).build());
    }
}
