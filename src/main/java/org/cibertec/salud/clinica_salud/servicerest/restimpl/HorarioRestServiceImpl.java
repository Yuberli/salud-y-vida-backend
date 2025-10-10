package org.cibertec.salud.clinica_salud.servicerest.restimpl;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.HorarioDto;
import org.cibertec.salud.clinica_salud.entity.HorarioEntity;
import org.cibertec.salud.clinica_salud.repository.HorarioRepository;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.cibertec.salud.clinica_salud.utilrest.HorarioMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HorarioRestServiceImpl implements GenericRestService<HorarioEntity, HorarioDto, Integer> {

    private final HorarioRepository horarioRepository;

    @Override
    public HorarioDto toDto (HorarioEntity entity) {
        return HorarioMapper.toDto(entity);
    }

    @Override
    public HorarioEntity toEntity (HorarioDto dto) {
        return HorarioMapper.toEntity(dto);
    }

    @Override
    public List<HorarioDto> findAll() {
        return HorarioMapper.toDtoList(horarioRepository.findAll());
    }

    @Override
    public Optional<HorarioDto> findById(Integer id) {
        return horarioRepository.findById(id)
                .map(HorarioMapper::toDto);
    }

    @Override
    public HorarioDto save(HorarioDto dto) {
        HorarioEntity entity = HorarioMapper.toEntity(dto);
        HorarioEntity saved =  horarioRepository.save(entity);
        return HorarioMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        horarioRepository.deleteById(id);
    }
}
