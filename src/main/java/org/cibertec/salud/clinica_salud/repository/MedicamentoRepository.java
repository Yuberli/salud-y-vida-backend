package org.cibertec.salud.clinica_salud.repository;
import org.cibertec.salud.clinica_salud.entity.MedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity,Integer> {
}
