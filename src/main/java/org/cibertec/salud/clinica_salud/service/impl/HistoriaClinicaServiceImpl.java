package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.HistoriaClinicaEntity;
import org.cibertec.salud.clinica_salud.service.HistoriaClinicaService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service

public class HistoriaClinicaServiceImpl extends GenericServiceImpl<HistoriaClinicaEntity,Integer> implements HistoriaClinicaService {

    public HistoriaClinicaServiceImpl(JpaRepository<HistoriaClinicaEntity,Integer> repository) {
        this.repository = repository;
    }
}
