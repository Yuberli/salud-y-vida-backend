package org.cibertec.salud.clinica_salud.servicerest.restimpl;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.CitaMeditaDto;
import org.cibertec.salud.clinica_salud.entity.CitaMedicaEntity;
import org.cibertec.salud.clinica_salud.repository.CitaMedicaRepository;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.cibertec.salud.clinica_salud.utilrest.CitaMedicaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitaMedicaRestServiceImpl implements GenericRestService<CitaMedicaEntity, CitaMeditaDto, Integer> {

    private final CitaMedicaRepository citaMedicaRepository;

    @Override
    public CitaMeditaDto toDto (CitaMedicaEntity entity) {
        return CitaMedicaMapper.toDto(entity);
    }

    @Override
    public CitaMedicaEntity toEntity (CitaMeditaDto dto) {
        return  CitaMedicaMapper.toEntity(dto);
    }

    @Override
    public List<CitaMeditaDto> findAll() {
        return CitaMedicaMapper.toDtoList(citaMedicaRepository.findAll());
    }

    @Override
    public Optional<CitaMeditaDto> findById(Integer id) {
        return citaMedicaRepository.findById(id)
                .map(CitaMedicaMapper::toDto);
    }
    @Override
    public CitaMeditaDto save (CitaMeditaDto dto) {
        CitaMedicaEntity entity = CitaMedicaMapper.toEntity(dto);
        CitaMedicaEntity saved =  citaMedicaRepository.save(entity);
        return CitaMedicaMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        citaMedicaRepository.deleteById(id);
    }
}
