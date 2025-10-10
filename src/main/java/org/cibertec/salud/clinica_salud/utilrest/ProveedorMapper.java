package org.cibertec.salud.clinica_salud.utilrest;


import org.cibertec.salud.clinica_salud.dto.ProveedorDto;
import org.cibertec.salud.clinica_salud.entity.ProveedorEntity;

import java.util.List;
import java.util.Optional;

public class ProveedorMapper {

    public static ProveedorDto toDto (ProveedorEntity entity) {

        return ProveedorDto.builder()
                .id(entity.getIdProveedor())
                .razon(entity.getRazonSocial())
                .rucProveedor(entity.getRuc())
                .telefonoProveedor(entity.getTelefono())
                .correoProveedor(entity.getCorreo())
                .build();
    }

    public static ProveedorEntity toEntity(ProveedorDto dto) {

        return ProveedorEntity.builder()
                .idProveedor(dto.getId())
                .razonSocial(dto.getRazon())
                .ruc(dto.getRucProveedor())
                .telefono(dto.getTelefonoProveedor())
                .correo(dto.getCorreoProveedor())
                .build();
    }

    public static List<ProveedorDto> toDtoList (List<ProveedorEntity> entities) {
        return entities.stream().map(ProveedorMapper::toDto).toList();
    }

    public static Optional<ProveedorDto> toDtoOptional (ProveedorEntity entity) {
        return Optional.ofNullable(entity).map(ProveedorMapper::toDto);
    }
}
