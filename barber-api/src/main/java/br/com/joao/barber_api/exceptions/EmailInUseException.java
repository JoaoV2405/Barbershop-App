package br.com.joao.barber_api.exceptions;

public class EmailInUseException extends RuntimeException{
    public EmailInUseException(String message) {
        super(message);
    }
}
