package br.com.payment.domain.core.domain.identifier;

import br.com.payment.domain.core.exception.EmailInvalidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private static final String EMAIL_PATTERN =
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private final String email;

    public Email(final String email) {
        if (!isValidEmail(email)) {
            throw new EmailInvalidException();
        }
        this.email = email;

    }

    public String getValue() {
        return email;
    }

    public static boolean isValidEmail(final String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
