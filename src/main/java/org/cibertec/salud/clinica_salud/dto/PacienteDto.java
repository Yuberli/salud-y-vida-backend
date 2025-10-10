package org.cibertec.salud.clinica_salud.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PacienteDto {

    private Integer id;
    private String nombrePaciente;
    private String apellidoPaciente;
    private String dniPaciente;
    private Integer edadPaciente;
    private String sexoPaciente;
    private String telefonoPaciente;
    private String direccionPaciente;
}
