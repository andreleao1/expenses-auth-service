package br.com.agls.expensesauthapi.domain.exceptions;

public class GenerateTokenException extends RuntimeException {

    public GenerateTokenException(String message) {
        super(message);
    }
}
