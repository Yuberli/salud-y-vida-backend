package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.RolEntity;
import org.cibertec.salud.clinica_salud.service.RolService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service

public class RolServiceImpl extends GenericServiceImpl<RolEntity,Integer> implements RolService {

    public RolServiceImpl(JpaRepository<RolEntity,Integer> repository) {
        this.repository = repository;
    }
}
