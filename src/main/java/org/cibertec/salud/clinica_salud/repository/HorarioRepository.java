package org.cibertec.salud.clinica_salud.repository;
import org.cibertec.salud.clinica_salud.entity.HorarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface HorarioRepository extends JpaRepository<HorarioEntity,Integer> {

    List<HorarioEntity> findByMedico_IdMedicoAndEstado(Integer idMedico, boolean estado);
}
