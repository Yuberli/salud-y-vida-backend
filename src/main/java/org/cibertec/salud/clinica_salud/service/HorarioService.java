package org.cibertec.salud.clinica_salud.service;
import org.cibertec.salud.clinica_salud.entity.HorarioEntity;

import java.util.List;

public interface HorarioService extends GenericService<HorarioEntity,Integer> {

    List<HorarioEntity> findByMedico_IdMedicoAndEstado(Integer idMedico, boolean estado);
}
