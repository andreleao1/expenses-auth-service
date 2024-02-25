package br.com.agls.expensesauthapi.domain.exceptions;

public class InvalidJWTException extends RuntimeException {

    public InvalidJWTException(String message) {
        super(message);
    }
}
