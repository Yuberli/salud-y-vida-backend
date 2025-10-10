package org.cibertec.salud.clinica_salud.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Builder
@Data
public class HistoriaClinicaDto {

    private Integer id;
    private Integer pacienteId;
    private String descripcionHistorial;
    private LocalDate fecha;
}
