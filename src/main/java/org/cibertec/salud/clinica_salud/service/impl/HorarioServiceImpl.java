package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.HorarioEntity;
import org.cibertec.salud.clinica_salud.service.HorarioService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class HorarioServiceImpl extends GenericServiceImpl<HorarioEntity,Integer> implements HorarioService {
}
