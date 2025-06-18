package org.cibertec.salud.clinica_salud.repository;
import org.cibertec.salud.clinica_salud.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Integer> {
}
