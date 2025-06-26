package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.ProveedorEntity;
import org.cibertec.salud.clinica_salud.service.ProveedorService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service

public class ProveedorServiceImpl extends GenericServiceImpl<ProveedorEntity,Integer> implements ProveedorService {

    public ProveedorServiceImpl(JpaRepository<ProveedorEntity,Integer> repository) {
        this.repository = repository;
    }
}
