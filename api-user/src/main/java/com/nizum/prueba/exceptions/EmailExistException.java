package com.nizum.prueba.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class EmailExistException extends RuntimeException {

    private String description;
    private HttpStatus httpStatus;

    public EmailExistException(String description, HttpStatus status) {
        super(description);
        this.description = description;
        this.httpStatus = status;
    }
}
