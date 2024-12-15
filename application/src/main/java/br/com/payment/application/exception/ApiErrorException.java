package br.com.payment.application.exception;

public abstract class ApiErrorException extends RuntimeException {

    public abstract int getCode();
    public abstract String getMessage();

}
