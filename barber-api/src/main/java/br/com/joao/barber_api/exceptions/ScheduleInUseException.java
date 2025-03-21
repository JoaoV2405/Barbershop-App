package br.com.joao.barber_api.exceptions;

public class ScheduleInUseException extends RuntimeException{
    public ScheduleInUseException(String message) {
        super(message);
    }
}
