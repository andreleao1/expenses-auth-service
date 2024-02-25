package br.com.agls.expensesauthapi.domain.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    UserDetails loadUserByEmail(String email);
}
