package org.cibertec.salud.clinica_salud.servicerest.restimpl;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.ProveedorDto;
import org.cibertec.salud.clinica_salud.entity.ProveedorEntity;
import org.cibertec.salud.clinica_salud.repository.ProveedorRepository;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.cibertec.salud.clinica_salud.utilrest.ProveedorMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProveedorRestServiceImpl implements GenericRestService<ProveedorEntity, ProveedorDto,Integer> {

    private final ProveedorRepository proveedorRepository;

    @Override
    public ProveedorDto toDto (ProveedorEntity entity) {
        return ProveedorMapper.toDto(entity);
    }

    @Override
    public ProveedorEntity toEntity (ProveedorDto dto) {
        return ProveedorMapper.toEntity(dto);
    }

    @Override
    public List<ProveedorDto> findAll() {
        return ProveedorMapper.toDtoList(proveedorRepository.findAll());
    }

    @Override
    public Optional<ProveedorDto> findById(Integer id) {
        return proveedorRepository.findById(id)
                .map(ProveedorMapper::toDto);
    }

    @Override
    public ProveedorDto save(ProveedorDto dto) {
        ProveedorEntity entity = ProveedorMapper.toEntity(dto);
        ProveedorEntity saved =  proveedorRepository.save(entity);
        return ProveedorMapper.toDto(saved);
    }

    @Override
    public void deleteById(Integer id) {
        proveedorRepository.deleteById(id);
    }
}
