package br.com.payment.domain.core.domain.identifier;

import br.com.payment.domain.core.exception.EmailInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

    @Test
    public void testIsValidEmail() {
        assertTrue(Email.isValidEmail("example@domain.com"), "O e-mail válido não deveria falhar na validação.");
        assertTrue(Email.isValidEmail("valid_email+test@domain.co"), "O e-mail válido não deveria falhar na validação.");

        assertFalse(Email.isValidEmail("invalid-email.com"), "O e-mail inválido deveria falhar na validação.");
        assertFalse(Email.isValidEmail("another@invalid"), "O e-mail inválido deveria falhar na validação.");
        assertFalse(Email.isValidEmail(""), "O e-mail vazio deveria falhar na validação.");
    }

    @Test
    public void testEmail() {
        Email email = new Email("example@domain.com");
        assertFalse(email.getValue().isEmpty());
    }

    @Test
    public void testCreateInvalidEmail_ShouldThrowEmailInvalidException() {
        assertThrows(EmailInvalidException.class, () -> new Email(null),
                "Deveria lançar EmailInvalidException para um e-mail inválido.");
    }

    @Test
    public void testCreateInvalidEmail() {
        String invalidEmail = "invalid-email.com";

        assertThrows(EmailInvalidException.class, () -> new Email(invalidEmail), "Deveria lançar EmailInvalidException para um e-mail inválido.");
    }
}
