package br.com.payment.application.infra;

import br.com.payment.application.exception.MercadoPagoIntegrationException;
import br.com.payment.application.exception.ResourceNotFound;
import br.com.payment.application.infra.ApiErrorMessage;
import br.com.payment.application.infra.CustomExceptionHandler;
import br.com.payment.domain.core.exception.CoreExceptionNegocial;
import br.com.payment.infra.exception.PaymentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandler;

    @Mock
    private WebRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customExceptionHandler = new CustomExceptionHandler();
    }

    @Test
    void testHandleBadRequestWithIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");

        ResponseEntity<Object> response = customExceptionHandler.handleBadRequest(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiErrorMessage body = (ApiErrorMessage) response.getBody();
        assert body != null;
        assertEquals(List.of("Invalid argument"), body.getErrors());
    }

    @Test
    void testHandleBadRequestWithCoreExceptionNegocial() {
        CoreExceptionNegocial ex = new CoreExceptionNegocial("Negocial error");

        ResponseEntity<Object> response = customExceptionHandler.handleBadRequest(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiErrorMessage body = (ApiErrorMessage) response.getBody();
        assert body != null;
        assertEquals(List.of("Negocial error"), body.getErrors());
    }

    @Test
    void testHandleInternal() {
        RuntimeException ex = new RuntimeException("Internal error");

        ResponseEntity<Object> response = customExceptionHandler.handleInternal(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ApiErrorMessage body = (ApiErrorMessage) response.getBody();
        assert body != null;
        assertEquals(List.of("Internal error"), body.getErrors());
    }

    @Test
    void testPaymentNotFoundException() {
        PaymentNotFoundException ex = new PaymentNotFoundException("Payment not found");

        ResponseEntity<Object> response = customExceptionHandler.paymentNotFoundException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ApiErrorMessage body = (ApiErrorMessage) response.getBody();
        assert body != null;
        assertEquals(List.of("Payment not found"), body.getErrors());
    }

    @Test
    void testMercadoPagoIntegrationException() {
        MercadoPagoIntegrationException ex = new MercadoPagoIntegrationException("Service unavailable");

        ResponseEntity<Object> response = customExceptionHandler.mercadoPagoIntegrationException(ex, request);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        ApiErrorMessage body = (ApiErrorMessage) response.getBody();
        assert body != null;
        assertEquals(List.of("Service unavailable"), body.getErrors());
    }

    @Test
    void testHandleNotFoundWithNoSuchElementException() {
        NoSuchElementException ex = new NoSuchElementException("Element not found");

        ResponseEntity<Object> response = customExceptionHandler.handleNotFound(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ApiErrorMessage body = (ApiErrorMessage) response.getBody();
        assert body != null;
        assertEquals(List.of("Element not found"), body.getErrors());
    }

    @Test
    void testHandleNotFoundWithResourceNotFound() {
        ResourceNotFound ex = new ResourceNotFound("Resource not found");

        ResponseEntity<Object> response = customExceptionHandler.handleNotFound(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ApiErrorMessage body = (ApiErrorMessage) response.getBody();
        assert body != null;
        assertEquals(List.of("Resource not found"), body.getErrors());
    }

    @Test
    void testHandleBadRequestWithMethodArgumentNotValidException() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);

        ResponseEntity<Object> response = customExceptionHandler.handleBadRequest(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiErrorMessage body = (ApiErrorMessage) response.getBody();
        assert body != null;
    }
}
