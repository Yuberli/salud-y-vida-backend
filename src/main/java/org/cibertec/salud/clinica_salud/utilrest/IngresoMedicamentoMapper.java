package org.cibertec.salud.clinica_salud.utilrest;

import org.cibertec.salud.clinica_salud.dto.IngresoMedicamentoDto;
import org.cibertec.salud.clinica_salud.entity.IngresoMedicamentoEntity;
import org.cibertec.salud.clinica_salud.entity.MedicamentoEntity;
import org.cibertec.salud.clinica_salud.entity.ProveedorEntity;

import java.util.List;
import java.util.Optional;

public class IngresoMedicamentoMapper {

    public static IngresoMedicamentoDto toDto (IngresoMedicamentoEntity entity) {

        return IngresoMedicamentoDto.builder()
                .id(entity.getIdIngreso())
                .proveedorId(entity.getProveedor().getIdProveedor())
                .medicamentoId(entity.getMedicamento().getIdMedicamento())
                .cantidadMedi(entity.getCantidad())
                .fechaEntrada(entity.getFechaIngreso())
                .observaciones(entity.getObservaciones())
                .build();
    }

    public static IngresoMedicamentoEntity toEntity(IngresoMedicamentoDto dto){

        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setIdProveedor(dto.getProveedorId());
        MedicamentoEntity medicamento = new MedicamentoEntity();
        medicamento.setIdMedicamento(dto.getMedicamentoId());

        return IngresoMedicamentoEntity.builder()
                .idIngreso(dto.getId())
                .proveedor(proveedor)
                .medicamento(medicamento)
                .cantidad(dto.getCantidadMedi())
                .fechaIngreso(dto.getFechaEntrada())
                .observaciones(dto.getObservaciones())
                .build();
    }

    public static List<IngresoMedicamentoDto> toDtoList (List<IngresoMedicamentoEntity> entites) {
        return entites.stream().map(IngresoMedicamentoMapper::toDto).toList();
    }

    public static Optional<IngresoMedicamentoDto> toDtoOptional (IngresoMedicamentoEntity entity) {
        return Optional.ofNullable(entity).map(IngresoMedicamentoMapper::toDto);
    }

}
