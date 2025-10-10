package org.cibertec.salud.clinica_salud.utilrest;

import org.cibertec.salud.clinica_salud.dto.HistoriaClinicaDto;
import org.cibertec.salud.clinica_salud.entity.HistoriaClinicaEntity;
import org.cibertec.salud.clinica_salud.entity.HorarioEntity;
import org.cibertec.salud.clinica_salud.entity.PacienteEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HistoriaClinicaMapper {

    public static HistoriaClinicaDto toDto (HistoriaClinicaEntity entity) {

        return HistoriaClinicaDto.builder()
                .id(entity.getIdHistoria())
                .pacienteId(entity.getPaciente().getIdPaciente())
                .descripcionHistorial(entity.getDescripcion())
                .fecha(entity.getFechaRegistro())
                .build();

    }

    public static HistoriaClinicaEntity toEntity(HistoriaClinicaDto dto) {

        PacienteEntity paciente = new PacienteEntity();
        paciente.setIdPaciente(dto.getPacienteId());

        return HistoriaClinicaEntity.builder()
                .idHistoria(dto.getId())
                .paciente(paciente)
                .descripcion(dto.getDescripcionHistorial())
                .fechaRegistro(dto.getFecha())
                .build();
    }

    public static List<HistoriaClinicaDto> toDtoList (List<HistoriaClinicaEntity> entities)
    {
        return entities.stream().map(HistoriaClinicaMapper::toDto).toList();
    }

    public Optional<HistoriaClinicaDto> toDtoOptional (HistoriaClinicaEntity entity) {
        return Optional.ofNullable(entity).map(HistoriaClinicaMapper::toDto);
    }
}
