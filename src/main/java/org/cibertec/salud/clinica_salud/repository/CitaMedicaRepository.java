package org.cibertec.salud.clinica_salud.repository;
import org.cibertec.salud.clinica_salud.entity.CitaMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface CitaMedicaRepository extends JpaRepository<CitaMedicaEntity,Integer>{

    List<CitaMedicaEntity> findByMedico_IdMedicoAndFecha(Integer idMedico, LocalDate fecha);

    @Query("SELECT c FROM CitaMedicaEntity c WHERE c.medico.idMedico = :idMedico AND c.fecha = :fecha AND c.hora = :hora")
    List<CitaMedicaEntity> findByMedicoFechaHora(@Param("idMedico") Integer idMedico,
                                                 @Param("fecha") LocalDate fecha,
                                                 @Param("hora") java.time.LocalTime hora);
}
