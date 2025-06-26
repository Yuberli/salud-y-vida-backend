package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.EspecialidadEntity;
import org.cibertec.salud.clinica_salud.service.EspecialidadService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadServiceImpl extends GenericServiceImpl<EspecialidadEntity,Integer> implements EspecialidadService {

    public EspecialidadServiceImpl(JpaRepository<EspecialidadEntity,Integer> repository) {
        this.repository = repository;
    }
}
