package org.cibertec.salud.clinica_salud.service.impl;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.entity.RolEntity;
import org.cibertec.salud.clinica_salud.repository.RolRepository;
import org.cibertec.salud.clinica_salud.service.RolService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Override
    public List<RolEntity> obtenerRoles() {
        return rolRepository.findAll();
    }
}
