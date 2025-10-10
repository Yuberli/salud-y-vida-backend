package org.cibertec.salud.clinica_salud.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ErrorMessageDto {

    private Integer statudCode;
    private String message;
    private LocalDate date;
}
