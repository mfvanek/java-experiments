package io.github.mfvanek.pg.cluster.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ExceptionHandlerTest {

    private ExceptionHandler exceptionHandler;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new ExceptionHandler();
    }

    @Test
    public void testHandleNoSuchElementException() {
        NoSuchElementException ex = new NoSuchElementException("Employee not found");
        ResponseEntity<Object> response = exceptionHandler.handleNoSuchElementException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Employee not found: Employee not found", response.getBody());
    }

    @Test
    public void testHandleInvalidUUIDFormat() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid UUID");
        ResponseEntity<Object> response = exceptionHandler.handleInvalidUUIDFormat(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid UUID format: Invalid UUID", response.getBody());
    }

    @Test
    public void testHandleValidationExceptions() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);

        FieldError fieldError = new FieldError("employee", "name", "must not be blank");
        when(ex.getBindingResult().getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<Object> response = exceptionHandler.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("name", "must not be blank");

        //assertEquals(HttpStatus.OK, response.getBody());
        assertEquals(expectedErrors, response.getBody());
    }

    @Test
    public void testHandleGlobalException() {
        Exception ex = new Exception("Unexpected error");
        ResponseEntity<Object> response = exceptionHandler.handleGlobalException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred: Unexpected error", response.getBody());
    }
}
