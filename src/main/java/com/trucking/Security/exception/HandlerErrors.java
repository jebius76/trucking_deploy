package com.trucking.security.exception;

import com.trucking.security.dto.ErrorMsgDto;
import jakarta.mail.AuthenticationFailedException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class HandlerErrors extends Throwable {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        ErrorMsgDto response = new ErrorMsgDto();
        response.setError("Error de validacion de DTO");
        response.setDetails(e.getFieldErrors().stream().map((a)->a.getDefaultMessage()).toList());
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(ValidationIntegrity.class)
    public ResponseEntity errorHandlerValidacionesIntegridad(Exception e){
        ErrorMsgDto response = new ErrorMsgDto();
        response.setError("Error de validación");
        response.setDetails(Arrays.asList(e.getMessage()));
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacionesDeNegocio(Exception e){
        Map<String, Object> response = new HashMap<>();
        response.put("Error", "Error de validación");

        List<String> errores = new ArrayList<>();
        errores.add(e.getMessage());
        response.put("Details", errores);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity errorHandlerMailAuthentication(Exception e){
        ErrorMsgDto response = new ErrorMsgDto();
        response.setError("Error de autenticacion en servidor de correo");
        response.setDetails(Arrays.asList(e.getMessage()));
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    private record ErrorValidationData(String campo, String error){
        public ErrorValidationData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
