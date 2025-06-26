package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.PacienteEntity;
import org.cibertec.salud.clinica_salud.service.PacienteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service

public class PacienteServiceImpl extends GenericServiceImpl<PacienteEntity,Integer> implements PacienteService {

    public PacienteServiceImpl(JpaRepository<PacienteEntity,Integer> repository) {

        this.repository = repository;
    }
}
