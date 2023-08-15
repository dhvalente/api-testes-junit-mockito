package br.com.apitestesjunitmockito.exceptions;

import br.com.apitestesjunitmockito.records.ExceptionRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    public static final String EXCEPTION_MESSAGE = "Objeto não encontrado!";

    public static final String EMAIL_EXCEPTION_MESSAGE = "E-mail já cadastrado no sistema.";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<ExceptionRecord> response =globalExceptionHandler
                .objectNotFoundException(new ObjectNotFoundException(EXCEPTION_MESSAGE),
                        new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionRecord.class, response.getBody().getClass());
        assertEquals(EXCEPTION_MESSAGE, response.getBody().messageError());
        assertEquals(404 , response.getBody().statusCodeError());
    }

    @Test
    void whenDataIntegrityViolationException() {
        ResponseEntity<ExceptionRecord> response =globalExceptionHandler
                .dataIntegrityViolationException(new DataIntegratyViolationException(EMAIL_EXCEPTION_MESSAGE),
                        new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ExceptionRecord.class, response.getBody().getClass());
        assertEquals(EMAIL_EXCEPTION_MESSAGE, response.getBody().messageError());
        assertEquals(400 , response.getBody().statusCodeError());
    }
}