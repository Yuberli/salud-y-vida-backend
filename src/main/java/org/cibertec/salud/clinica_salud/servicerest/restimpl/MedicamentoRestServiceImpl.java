package org.cibertec.salud.clinica_salud.servicerest.restimpl;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.MedicamentoDto;
import org.cibertec.salud.clinica_salud.entity.MedicamentoEntity;
import org.cibertec.salud.clinica_salud.repository.MedicamentoRepository;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.cibertec.salud.clinica_salud.utilrest.MedicamentoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class MedicamentoRestServiceImpl implements GenericRestService<MedicamentoEntity,MedicamentoDto ,Integer> {

    private final MedicamentoRepository medicamentoRepository;

    @Override
    public MedicamentoDto toDto(MedicamentoEntity entity){
        return MedicamentoMapper.toDto(entity);
    }

    @Override
    public MedicamentoEntity toEntity(MedicamentoDto dto){
        return MedicamentoMapper.toEntity(dto);
    }

    @Override
    public List<MedicamentoDto> findAll(){
        return MedicamentoMapper.toDtoList(medicamentoRepository.findAll());
    }

    @Override
    public Optional<MedicamentoDto> findById(Integer id){
        return medicamentoRepository.findById(id)
                .map(MedicamentoMapper::toDto);
    }

    @Override
    public MedicamentoDto save(MedicamentoDto dto){
        MedicamentoEntity entity = MedicamentoMapper.toEntity(dto);
        MedicamentoEntity saved = medicamentoRepository.save(entity);
        return MedicamentoMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id){
        medicamentoRepository.deleteById(id);
    }



}
