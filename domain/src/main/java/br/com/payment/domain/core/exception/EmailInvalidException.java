package br.com.payment.domain.core.exception;

public class EmailInvalidException extends CoreExceptionRuntime{
    private static final String MESSAGE = "Email is invalid";

    public EmailInvalidException() {
        super(MESSAGE);
    }
}
