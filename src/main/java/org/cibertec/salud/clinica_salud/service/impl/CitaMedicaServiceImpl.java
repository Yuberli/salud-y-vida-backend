package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.CitaMedicaEntity;
import org.cibertec.salud.clinica_salud.service.CitaMedicaService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CitaMedicaServiceImpl extends GenericServiceImpl<CitaMedicaEntity,Integer> implements CitaMedicaService {

    public CitaMedicaServiceImpl(JpaRepository<CitaMedicaEntity,Integer> repository) {
        this.repository = repository;
    }
}
