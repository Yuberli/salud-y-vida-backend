package org.cibertec.salud.clinica_salud.dto;

import lombok.Data;
import java.util.Set;

@Data
public class RegistroUsuarioDto {

    private Integer idusuario;
    private String nomusuario;
    private String nombres;
    private String apellidos;
    private String password;
    private String email;
    private Boolean activo;
    private Set<Integer> rolesSeleccionados;
}
