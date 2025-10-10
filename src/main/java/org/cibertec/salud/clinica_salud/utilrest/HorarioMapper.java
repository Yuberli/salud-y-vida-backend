package org.cibertec.salud.clinica_salud.utilrest;

import org.cibertec.salud.clinica_salud.dto.HorarioDto;
import org.cibertec.salud.clinica_salud.entity.HorarioEntity;
import org.cibertec.salud.clinica_salud.entity.MedicoEntity;

import java.util.List;
import java.util.Optional;

public class HorarioMapper {

    public static HorarioDto toDto (HorarioEntity entity) {

        return HorarioDto.builder()
                .id(entity.getIdHorario())
                .medicoId(entity.getMedico().getIdMedico())
                .horario(entity.getHora())
                .estadoHora(entity.getEstado())
                .build();
    }

    public static HorarioEntity toEntity(HorarioDto dto) {

        MedicoEntity medico = new MedicoEntity();
        medico.setIdMedico(dto.getMedicoId());

        return HorarioEntity.builder()
                .idHorario(dto.getId())
                .medico(medico)
                .hora(dto.getHorario())
                .estado(dto.getEstadoHora())
                .build();
    }

    public static List<HorarioDto> toDtoList (List<HorarioEntity> entities)
    {
        return entities.stream().map(HorarioMapper::toDto).toList();
    }

    public static Optional<HorarioDto> toDtoOptional (HorarioEntity entity){
        return Optional.ofNullable(entity).map(HorarioMapper::toDto);
    }
}
