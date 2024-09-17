package br.com.fiap.localweb.exceptions;

public class EmailLimitExceededException extends RuntimeException {
    public EmailLimitExceededException(String message) {
        super(message);
    }
}
