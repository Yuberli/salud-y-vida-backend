package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.UsuarioEntity;
import org.cibertec.salud.clinica_salud.service.UsuarioService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service

public class UsuarioServiceImpl  extends GenericServiceImpl<UsuarioEntity,Integer> implements UsuarioService{

    public UsuarioServiceImpl(JpaRepository<UsuarioEntity,Integer> repository) {
        this.repository = repository;
    }
}
