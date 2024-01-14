package com.rodrigo.helpdesk.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(ObjectNotFoundException.class)  // Excesao no qual vai ser capturada
    public ResponseEntity<StandarError> objNotFoundException
            (ObjectNotFoundException ex, HttpServletRequest request) { // parametro do tipo ObjectNotFoundException

        StandarError error= new StandarError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),  // resposta para a escessao
                "Object Not Found",   // mensagem para excessao
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);  // retorno para a excessao
    }
    @ExceptionHandler(DataIntegrityViolationException.class)  // Excesao no qual vai ser capturada
    public ResponseEntity<StandarError> dataIntegrityViolationException
            (DataIntegrityViolationException ex, HttpServletRequest request) {  // parametro do tipo DataIntegrityViolationException

        StandarError error= new StandarError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),  // resposta para a escessao
                "Violacao de Dados", // mensagem para excessao
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error); // retorno para a excessao
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationErrors(MethodArgumentNotValidException ex,
                                                            HttpServletRequest request) {

        ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Validation error", "Erro na validação dos campos", request.getRequestURI());

        for(FieldError x : ex.getBindingResult().getFieldErrors()) {
            errors.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
