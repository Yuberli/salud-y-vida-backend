package org.cibertec.salud.clinica_salud.exception;


import org.cibertec.salud.clinica_salud.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDto resourceNotFoundException(
            ResourceNotFoundException exception) {
        return ErrorMessageDto.builder()
                .message(exception.getMessage())
                .date(LocalDate.now())
                .statudCode(HttpStatus.NOT_FOUND.value()).build();
    }
}