package org.cibertec.salud.clinica_salud.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MedicamentoDto {

    private Integer id;
    private Integer proveedorId;
    private String nombreMedicamento;
    private String presenMedicamento;
    private String concenMedicamento;
    private String unidadMedicamento;
    private Integer stockAMedicamento;
    private Integer stockMMedicamento;
    private Boolean estadoMedicamento;

}
