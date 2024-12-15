package br.com.payment.application.infra;

import br.com.payment.application.infra.ApiErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorMessageTest {

    @Test
    void testApiErrorMessageWithArgsConstructor() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = Arrays.asList("Error 1", "Error 2");

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(status, errors);

        assertEquals(HttpStatus.BAD_REQUEST, apiErrorMessage.getStatus());
        assertEquals(2, apiErrorMessage.getErrors().size());
        assertTrue(apiErrorMessage.getErrors().contains("Error 1"));
        assertTrue(apiErrorMessage.getErrors().contains("Error 2"));
    }

    @Test
    void testApiErrorMessageNoArgsConstructor() {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();

        assertNull(apiErrorMessage.getStatus());
        assertNull(apiErrorMessage.getErrors());
    }

    @Test
    void testSettersAndGetters() {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();

        apiErrorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        apiErrorMessage.setErrors(Arrays.asList("Internal Error"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, apiErrorMessage.getStatus());
        assertEquals(1, apiErrorMessage.getErrors().size());
        assertEquals("Internal Error", apiErrorMessage.getErrors().get(0));
    }
}
