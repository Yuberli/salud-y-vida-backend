package org.cibertec.salud.clinica_salud.servicerest.restimpl;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.IngresoMedicamentoDto;
import org.cibertec.salud.clinica_salud.entity.IngresoMedicamentoEntity;
import org.cibertec.salud.clinica_salud.repository.IngresoMedicamentoRepository;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.cibertec.salud.clinica_salud.utilrest.IngresoMedicamentoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngresoMedicamentoRestServiceImpl implements GenericRestService<IngresoMedicamentoEntity, IngresoMedicamentoDto, Integer> {

    private final IngresoMedicamentoRepository ingresoMedicamentoRepository;

    @Override
    public IngresoMedicamentoDto toDto(IngresoMedicamentoEntity entity) {
        return IngresoMedicamentoMapper.toDto(entity);
    }

    @Override
    public IngresoMedicamentoEntity toEntity(IngresoMedicamentoDto dto) {
        return IngresoMedicamentoMapper.toEntity(dto);
    }

    @Override
    public List<IngresoMedicamentoDto> findAll(){
        return IngresoMedicamentoMapper.toDtoList(ingresoMedicamentoRepository.findAll());
    }

    @Override
    public Optional<IngresoMedicamentoDto> findById(Integer id){
        return ingresoMedicamentoRepository.findById(id)
                .map(IngresoMedicamentoMapper::toDto);
    }

    @Override
    public IngresoMedicamentoDto save(IngresoMedicamentoDto dto) {
        IngresoMedicamentoEntity entity = IngresoMedicamentoMapper.toEntity(dto);
        IngresoMedicamentoEntity saved =  ingresoMedicamentoRepository.save(entity);
        return IngresoMedicamentoMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        ingresoMedicamentoRepository.deleteById(id);
    }
}
