package br.com.apitestesjunitmockito.exceptions;

import br.com.apitestesjunitmockito.records.ExceptionRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ExceptionRecord> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionRecord(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI())
        );
    }

    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<ExceptionRecord> dataIntegrityViolationException(DataIntegratyViolationException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionRecord(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI())
        );
    }


}