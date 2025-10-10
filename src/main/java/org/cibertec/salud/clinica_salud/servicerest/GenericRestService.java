package org.cibertec.salud.clinica_salud.servicerest;

import java.util.List;
import java.util.Optional;

public interface GenericRestService<T, DTO, ID> {

    DTO toDto(T entity);
    T toEntity(DTO dto);

    Optional<DTO> findById(ID id);
    List<DTO> findAll();
    DTO save(DTO dto);
    void deleteById(ID id);

}
