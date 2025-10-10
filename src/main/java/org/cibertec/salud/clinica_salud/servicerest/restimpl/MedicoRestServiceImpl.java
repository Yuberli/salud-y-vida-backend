package org.cibertec.salud.clinica_salud.servicerest.restimpl;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.MedicoDto;
import org.cibertec.salud.clinica_salud.entity.MedicoEntity;
import org.cibertec.salud.clinica_salud.repository.MedicoRepository;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.cibertec.salud.clinica_salud.utilrest.MedicoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoRestServiceImpl implements GenericRestService<MedicoEntity, MedicoDto,Integer> {

    private final MedicoRepository medicoRepository;

    @Override
    public MedicoDto toDto (MedicoEntity entity) {
        return MedicoMapper.toDto(entity);
    }

    @Override
    public MedicoEntity toEntity (MedicoDto dto) {
        return MedicoMapper.toEntity(dto);
    }

    @Override
    public List<MedicoDto> findAll() {
        return MedicoMapper.toDtoList(medicoRepository.findAll());
    }

    @Override
    public Optional<MedicoDto> findById(Integer id) {
        return medicoRepository.findById(id)
                .map(MedicoMapper::toDto);
    }

    @Override
    public MedicoDto save (MedicoDto dto) {
        MedicoEntity entity = MedicoMapper.toEntity(dto);
        MedicoEntity saved = medicoRepository.save(entity);
        return MedicoMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        medicoRepository.deleteById(id);
    }

}
