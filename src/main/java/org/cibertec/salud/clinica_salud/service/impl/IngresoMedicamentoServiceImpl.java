package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.IngresoMedicamentoEntity;
import org.cibertec.salud.clinica_salud.service.IngresoMedicamentoService;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class IngresoMedicamentoServiceImpl extends GenericServiceImpl<IngresoMedicamentoEntity,Integer> implements IngresoMedicamentoService {

    public IngresoMedicamentoServiceImpl(JpaRepository<IngresoMedicamentoEntity,Integer> repository) {
        this.repository = repository;
    }
}
