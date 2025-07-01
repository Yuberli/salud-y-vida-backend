package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.entity.HistoriaClinicaEntity;
import org.cibertec.salud.clinica_salud.repository.HistoriaClinicaRepository;
import org.cibertec.salud.clinica_salud.service.HistoriaClinicaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class HistoriaClinicaServiceImpl extends GenericServiceImpl<HistoriaClinicaEntity,Integer> implements HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    public HistoriaClinicaServiceImpl(JpaRepository<HistoriaClinicaEntity,Integer> repository) {
        this.repository = repository;
    }
    @Override
    public List<HistoriaClinicaEntity> buscarPorPacienteNombre(String nombre) {
        return historiaClinicaRepository.findByPaciente_NombreContainingIgnoreCase(nombre);
    }
    @Override
    public List<HistoriaClinicaEntity> findByPacienteId(Integer idPaciente) {
        return historiaClinicaRepository.findByPaciente_IdPaciente(idPaciente);
    }

    @Override
    public Optional<HistoriaClinicaEntity> obtenerUltimoHistorialPorPaciente(Integer idPaciente) {
        return historiaClinicaRepository.findFirstByPaciente_IdPacienteOrderByFechaRegistroDesc(idPaciente);
    }


}
