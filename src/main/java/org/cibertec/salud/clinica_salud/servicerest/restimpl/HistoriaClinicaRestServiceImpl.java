package org.cibertec.salud.clinica_salud.servicerest.restimpl;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.HistoriaClinicaDto;
import org.cibertec.salud.clinica_salud.entity.HistoriaClinicaEntity;
import org.cibertec.salud.clinica_salud.repository.HistoriaClinicaRepository;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.cibertec.salud.clinica_salud.utilrest.HistoriaClinicaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaRestServiceImpl implements GenericRestService<HistoriaClinicaEntity, HistoriaClinicaDto, Integer> {

    private final HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public HistoriaClinicaDto toDto(HistoriaClinicaEntity entity) {
        return HistoriaClinicaMapper.toDto(entity);
    }

    @Override
    public HistoriaClinicaEntity toEntity(HistoriaClinicaDto dto) {
        return HistoriaClinicaMapper.toEntity(dto);
    }

    @Override
    public List<HistoriaClinicaDto> findAll(){
        return HistoriaClinicaMapper.toDtoList(historiaClinicaRepository.findAll());
    }

    @Override
    public Optional<HistoriaClinicaDto> findById(Integer id) {
        return historiaClinicaRepository.findById(id)
                .map(HistoriaClinicaMapper::toDto);
    }

    @Override
    public HistoriaClinicaDto save(HistoriaClinicaDto dto) {
        HistoriaClinicaEntity entity = HistoriaClinicaMapper.toEntity(dto);
        HistoriaClinicaEntity saved =  historiaClinicaRepository.save(entity);
        return HistoriaClinicaMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        historiaClinicaRepository.deleteById(id);
    }
}
