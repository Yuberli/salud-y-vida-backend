package org.cibertec.salud.clinica_salud.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
@Builder
@Data
public class HorarioDto {

    private Integer id;
    private Integer medicoId;
    private LocalTime horario;
    private Boolean estadoHora;
}
