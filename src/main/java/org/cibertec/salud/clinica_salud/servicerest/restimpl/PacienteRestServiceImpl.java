package org.cibertec.salud.clinica_salud.servicerest.restimpl;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.PacienteDto;
import org.cibertec.salud.clinica_salud.entity.PacienteEntity;
import org.cibertec.salud.clinica_salud.repository.PacienteRepository;

import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.cibertec.salud.clinica_salud.utilrest.PacienteMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PacienteRestServiceImpl implements GenericRestService<PacienteEntity,PacienteDto,Integer> {

    private final PacienteRepository pacienteRepository;

    @Override
    public PacienteDto toDto(PacienteEntity entity) {
        return PacienteMapper.toDto(entity);
    }

    @Override
    public PacienteEntity toEntity(PacienteDto dto) {
        return PacienteMapper.toEntity(dto);
    }

    @Override
    public List<PacienteDto> findAll() {
        return PacienteMapper.toDtoList(pacienteRepository.findAll());
    }

    @Override
    public Optional<PacienteDto> findById(Integer id) {
        return pacienteRepository.findById(id)
                .map(PacienteMapper::toDto);
    }

    @Override
    public PacienteDto save(PacienteDto dto) {
        PacienteEntity entity = PacienteMapper.toEntity(dto);
        PacienteEntity saved = pacienteRepository.save(entity);
        return PacienteMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        pacienteRepository.deleteById(id);
    }




}
