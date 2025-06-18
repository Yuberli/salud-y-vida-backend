package org.cibertec.salud.clinica_salud.repository;
import org.cibertec.salud.clinica_salud.entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Integer> {
}
