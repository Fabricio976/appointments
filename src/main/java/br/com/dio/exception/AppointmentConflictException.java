package br.com.dio.exception;

public class AppointmentConflictException extends RuntimeException {
    public AppointmentConflictException(String msg) {
        super(msg);
    }
}