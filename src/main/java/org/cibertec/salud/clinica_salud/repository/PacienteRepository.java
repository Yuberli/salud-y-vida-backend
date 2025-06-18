package org.cibertec.salud.clinica_salud.repository;

import org.cibertec.salud.clinica_salud.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Integer> {
}
