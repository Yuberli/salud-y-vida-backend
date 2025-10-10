package org.cibertec.salud.clinica_salud.utilrest;

import org.cibertec.salud.clinica_salud.dto.CitaMeditaDto;
import org.cibertec.salud.clinica_salud.entity.CitaMedicaEntity;
import org.cibertec.salud.clinica_salud.entity.MedicoEntity;
import org.cibertec.salud.clinica_salud.entity.PacienteEntity;

import java.util.List;
import java.util.Optional;

public class CitaMedicaMapper {

    public static CitaMeditaDto toDto (CitaMedicaEntity entity) {

        return CitaMeditaDto.builder()
                .id(entity.getIdCita())
                .pacienteid(entity.getPaciente().getIdPaciente())
                .medicoid(entity.getMedico().getIdMedico())
                .fechaCita(entity.getFecha())
                .horaCita(entity.getHora())
                .estadoCita(entity.getEstado())
                .build();
    }

    public static CitaMedicaEntity toEntity (CitaMeditaDto dto) {

        PacienteEntity paciente = new PacienteEntity();
        paciente.setIdPaciente(dto.getPacienteid());
        MedicoEntity medico = new MedicoEntity();
        medico.setIdMedico(dto.getMedicoid());

        return CitaMedicaEntity.builder()
                .idCita(dto.getId())
                .paciente(paciente)
                .medico(medico)
                .fecha(dto.getFechaCita())
                .hora(dto.getHoraCita())
                .estado(dto.getEstadoCita())
                .build();
    }

    public static List<CitaMeditaDto> toDtoList (List<CitaMedicaEntity> entites)
    {
        return entites.stream().map(CitaMedicaMapper::toDto).toList();
    }

    public static Optional<CitaMeditaDto> toDtoOptional (CitaMedicaEntity entity)
    {
        return Optional.ofNullable(entity).map(CitaMedicaMapper::toDto);
    }
}
