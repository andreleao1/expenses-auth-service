package br.com.agls.expensesauthapi.domain.exceptions;

public class RegisterUserException extends RuntimeException {

    public RegisterUserException(String message) {
        super(message);
    }
}
