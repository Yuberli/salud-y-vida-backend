package org.cibertec.salud.clinica_salud.service;
import org.cibertec.salud.clinica_salud.entity.HistoriaClinicaEntity;

import java.util.List;
import java.util.Optional;

public interface HistoriaClinicaService extends GenericService<HistoriaClinicaEntity,Integer> {

    List<HistoriaClinicaEntity> buscarPorPacienteNombre(String nombre);
    List<HistoriaClinicaEntity> findByPacienteId(Integer idPaciente);
    Optional<HistoriaClinicaEntity> obtenerUltimoHistorialPorPaciente(Integer idPaciente);

}
