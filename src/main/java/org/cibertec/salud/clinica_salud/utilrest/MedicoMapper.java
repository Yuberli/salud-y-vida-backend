package org.cibertec.salud.clinica_salud.utilrest;

import org.cibertec.salud.clinica_salud.dto.MedicoDto;
import org.cibertec.salud.clinica_salud.entity.EspecialidadEntity;
import org.cibertec.salud.clinica_salud.entity.MedicoEntity;

import java.util.List;
import java.util.Optional;

public class MedicoMapper {

    public static MedicoDto toDto (MedicoEntity entity) {

        return MedicoDto.builder()
                .id(entity.getIdMedico())
                .nombreMed(entity.getNombre())
                .apellidoMed(entity.getApellido())
                .especialMed(entity.getEspecialidad().getIdEspecialidad())
                .telefonoMed(entity.getTelefono())
                .estadoMed(entity.getEstado())
                .build();
    }

    public static MedicoEntity toEntity(MedicoDto dto) {

        EspecialidadEntity especialidad = new EspecialidadEntity();
        especialidad.setIdEspecialidad(dto.getEspecialMed());

        return MedicoEntity.builder()
                .idMedico(dto.getId())
                .nombre(dto.getNombreMed())
                .apellido(dto.getApellidoMed())
                .especialidad(especialidad)
                .estado(dto.getEstadoMed())
                .build();
    }

    public static List<MedicoDto> toDtoList (List<MedicoEntity> entities)
    {
        return entities.stream().map(MedicoMapper::toDto).toList();
    }

    public static Optional<MedicoDto> toDtoOptional (MedicoEntity entity)
    {
        return Optional.ofNullable(entity).map(MedicoMapper::toDto);
    }

}

