package org.cibertec.salud.clinica_salud.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Builder
@Data
public class CitaMeditaDto {

    private Integer id;
    private Integer pacienteid;
    private Integer medicoid;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String estadoCita;

}
