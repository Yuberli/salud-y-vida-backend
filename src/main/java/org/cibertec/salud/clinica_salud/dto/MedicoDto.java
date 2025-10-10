package org.cibertec.salud.clinica_salud.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MedicoDto {

    private Integer id;
    private String nombreMed;
    private String apellidoMed;
    private Integer especialMed;
    private String telefonoMed;
    private Boolean estadoMed;

}

