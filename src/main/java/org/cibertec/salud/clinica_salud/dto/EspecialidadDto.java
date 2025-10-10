package org.cibertec.salud.clinica_salud.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EspecialidadDto {

    private Integer id;
    private String nombre;
}
