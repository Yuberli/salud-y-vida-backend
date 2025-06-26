package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.MedicoEntity;
import org.cibertec.salud.clinica_salud.service.MedicoService;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public class MedicoServiceImpl extends GenericServiceImpl<MedicoEntity,Integer> implements MedicoService {

    public MedicoServiceImpl(JpaRepository<MedicoEntity,Integer> repository) {
        this.repository = repository;
    }
}
