package br.com.agls.expensesauthapi.api.controller;

import br.com.agls.expensesauthapi.domain.entity.user.User;
import br.com.agls.expensesauthapi.domain.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid User user) {
        this.registerService.execute(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
