package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.MedicamentoEntity;
import org.cibertec.salud.clinica_salud.service.MedicamentoService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service

public class MedicamentoServiceImpl extends GenericServiceImpl<MedicamentoEntity,Integer> implements MedicamentoService {
}
