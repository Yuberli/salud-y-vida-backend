package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.HorarioEntity;
import org.cibertec.salud.clinica_salud.repository.HorarioRepository;
import org.cibertec.salud.clinica_salud.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class HorarioServiceImpl extends GenericServiceImpl<HorarioEntity, Integer> implements HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public HorarioServiceImpl(JpaRepository<HorarioEntity, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public List<HorarioEntity> findByMedico_IdMedicoAndEstado(Integer idMedico, boolean estado) {
        return horarioRepository.findByMedico_IdMedicoAndEstado(idMedico, estado);
    }
}