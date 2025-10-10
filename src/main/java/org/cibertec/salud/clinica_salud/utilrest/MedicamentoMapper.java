package org.cibertec.salud.clinica_salud.utilrest;

import org.cibertec.salud.clinica_salud.dto.MedicamentoDto;
import org.cibertec.salud.clinica_salud.entity.MedicamentoEntity;
import org.cibertec.salud.clinica_salud.entity.ProveedorEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MedicamentoMapper {

    public static MedicamentoDto toDto (MedicamentoEntity entity) {
        return MedicamentoDto.builder()
                .id(entity.getIdMedicamento())
                .proveedorId(entity.getProveedor().getIdProveedor())
                .nombreMedicamento(entity.getNombre())
                .presenMedicamento(entity.getPresentacion())
                .concenMedicamento(entity.getConcentracion())
                .unidadMedicamento(entity.getUnidad())
                .stockAMedicamento(entity.getStockActual())
                .stockMMedicamento(entity.getStockMinimo())
                .estadoMedicamento(entity.getEstado())
                .build();
    }

    public static MedicamentoEntity toEntity(MedicamentoDto dto) {

        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setIdProveedor(dto.getProveedorId());

        return MedicamentoEntity.builder()
                .idMedicamento(dto.getId())
                .proveedor(proveedor)
                .nombre(dto.getNombreMedicamento())
                .presentacion(dto.getPresenMedicamento())
                .concentracion(dto.getConcenMedicamento())
                .unidad(dto.getUnidadMedicamento())
                .stockActual(dto.getStockAMedicamento())
                .stockMinimo(dto.getStockMMedicamento())
                .estado(dto.getEstadoMedicamento())
                .build();
    }

    public static List<MedicamentoDto> toDtoList (List<MedicamentoEntity> entities) {

        return entities.stream().map(MedicamentoMapper::toDto).collect(Collectors.toList());
    }

    public static Optional<MedicamentoDto> toDtoOptional (MedicamentoEntity entity) {

        return Optional.ofNullable(entity).map(MedicamentoMapper::toDto);
    }

}
