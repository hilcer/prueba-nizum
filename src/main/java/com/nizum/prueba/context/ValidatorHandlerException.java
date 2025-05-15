package com.nizum.prueba.context;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidatorHandlerException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        StringBuilder descripcionError = new StringBuilder();
        List<ObjectError> allerrors = e.getBindingResult().getAllErrors();
        allerrors.forEach(err -> {
            FieldError fe = (FieldError) err;
            descripcionError.append(fe.getField()).append(":").append(fe.getDefaultMessage()).append(", ");
        });
        errors.put("mensaje", descripcionError.substring(0, descripcionError.length()-2));
        return errors;
    }
}
