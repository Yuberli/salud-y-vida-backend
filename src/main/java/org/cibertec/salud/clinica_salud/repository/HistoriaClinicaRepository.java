package org.cibertec.salud.clinica_salud.repository;
import org.cibertec.salud.clinica_salud.entity.HistoriaClinicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinicaEntity,Integer> {

    @Query("SELECT h FROM HistoriaClinicaEntity h WHERE LOWER(h.paciente.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<HistoriaClinicaEntity> buscarPorPacienteNombre(@Param("nombre") String nombre);

    List<HistoriaClinicaEntity> findByPaciente_IdPaciente(Integer idPaciente);
    List<HistoriaClinicaEntity> findByPaciente_NombreContainingIgnoreCase(String nombre);

    Optional<HistoriaClinicaEntity> findFirstByPaciente_IdPacienteOrderByFechaRegistroDesc(Integer idPaciente);
}
