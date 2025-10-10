package org.cibertec.salud.clinica_salud.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class IngresoMedicamentoDto {

    private Integer id;
    private Integer proveedorId;
    private Integer medicamentoId;
    private Integer cantidadMedi;
    private Date    fechaEntrada;
    private String observaciones;
}
