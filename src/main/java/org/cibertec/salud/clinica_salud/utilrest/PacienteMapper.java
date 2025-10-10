package org.cibertec.salud.clinica_salud.utilrest;

import org.cibertec.salud.clinica_salud.dto.PacienteDto;
import org.cibertec.salud.clinica_salud.entity.PacienteEntity;

import java.util.List;
import java.util.Optional;

public class PacienteMapper {

    public static PacienteDto toDto(PacienteEntity entity) {
        return PacienteDto.builder()
                .id(entity.getIdPaciente())
                .nombrePaciente(entity.getNombre())
                .apellidoPaciente(entity.getApellido())
                .dniPaciente(entity.getDni())
                .edadPaciente(entity.getEdad())
                .sexoPaciente(entity.getSexo())
                .telefonoPaciente(entity.getTelefono())
                .direccionPaciente(entity.getDireccion())
                .build();
    }

    public static PacienteEntity toEntity(PacienteDto dto) {
        return PacienteEntity.builder()
                .idPaciente(dto.getId())
                .nombre(dto.getNombrePaciente())
                .apellido(dto.getApellidoPaciente())
                .dni(dto.getDniPaciente())
                .edad(dto.getEdadPaciente())
                .sexo(dto.getSexoPaciente())
                .telefono(dto.getTelefonoPaciente())
                .direccion(dto.getDireccionPaciente())
                .build();
    }

    public static List<PacienteDto> toDtoList(List<PacienteEntity> entities) {
        return entities.stream().map(PacienteMapper::toDto).toList();
    }

    public static Optional<PacienteDto> toDtoOptional(PacienteEntity entity) {
        return Optional.ofNullable(entity).map(PacienteMapper::toDto);
    }

}
