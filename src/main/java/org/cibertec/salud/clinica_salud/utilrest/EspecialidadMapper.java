package org.cibertec.salud.clinica_salud.utilrest;

import org.cibertec.salud.clinica_salud.dto.EspecialidadDto;
import org.cibertec.salud.clinica_salud.entity.EspecialidadEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EspecialidadMapper {

    public static EspecialidadDto toDto (EspecialidadEntity entity) {

        return EspecialidadDto.builder()
                .id(entity.getIdEspecialidad())
                .nombre(entity.getNombre())
                .build();
    }

    public static EspecialidadEntity toEntity (EspecialidadDto dto) {

        return EspecialidadEntity.builder()
                .idEspecialidad(dto.getId())
                .nombre(dto.getNombre())
                .build();
    }

    public static List<EspecialidadDto> toDtoList (List<EspecialidadEntity> entities) {
        return entities.stream().map(EspecialidadMapper::toDto).toList();
    }

    public static Optional<EspecialidadDto> toDtoOptional (EspecialidadEntity entity) {
        return Optional.ofNullable(entity).map(EspecialidadMapper::toDto);
    }
}
