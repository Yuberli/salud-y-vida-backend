package org.cibertec.salud.clinica_salud.servicerest.restimpl;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.EspecialidadDto;
import org.cibertec.salud.clinica_salud.entity.EspecialidadEntity;
import org.cibertec.salud.clinica_salud.repository.EspecialidadRepository;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.cibertec.salud.clinica_salud.utilrest.EspecialidadMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecialidadRestServiceImpl implements GenericRestService<EspecialidadEntity, EspecialidadDto, Integer> {

    private final EspecialidadRepository especialidadRepository;

    @Override
    public EspecialidadDto toDto (EspecialidadEntity entity) {
        return EspecialidadMapper.toDto(entity);
    }

    @Override
    public EspecialidadEntity toEntity (EspecialidadDto dto) {
        return EspecialidadMapper.toEntity(dto);
    }

    @Override
    public List<EspecialidadDto> findAll() {
        return EspecialidadMapper.toDtoList(especialidadRepository.findAll());
    }

    @Override
    public Optional<EspecialidadDto> findById(Integer id) {
        return especialidadRepository.findById(id)
                .map(EspecialidadMapper::toDto);
    }

    @Override
    public EspecialidadDto save(EspecialidadDto dto) {
        EspecialidadEntity entity = EspecialidadMapper.toEntity(dto);
        EspecialidadEntity saved =  especialidadRepository.save(entity);
        return EspecialidadMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        especialidadRepository.deleteById(id);
    }
}
