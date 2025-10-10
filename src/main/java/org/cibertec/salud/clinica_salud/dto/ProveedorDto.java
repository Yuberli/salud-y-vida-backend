package org.cibertec.salud.clinica_salud.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProveedorDto {

    private Integer id;
    private String razon;
    private String rucProveedor;
    private String telefonoProveedor;
    private String correoProveedor;
}
