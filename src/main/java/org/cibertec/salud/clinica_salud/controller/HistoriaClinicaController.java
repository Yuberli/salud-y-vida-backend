package org.cibertec.salud.clinica_salud.controller;
import org.cibertec.salud.clinica_salud.entity.HistoriaClinicaEntity;
import org.cibertec.salud.clinica_salud.entity.PacienteEntity;
import org.cibertec.salud.clinica_salud.service.HistoriaClinicaService;
import org.cibertec.salud.clinica_salud.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/historial")
public class HistoriaClinicaController {

    @Autowired
    private HistoriaClinicaService historiaClinicaService;
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/inicio")
    String inicio(@RequestParam(name = "nombre", required = false) String nombre, Model model) {

        List<HistoriaClinicaEntity> lista;

        if(nombre != null && !nombre.trim().isEmpty()) {

            lista = historiaClinicaService.buscarPorPacienteNombre(nombre);
            model.addAttribute("nombreBuscado",nombre);
        } else {
            List<PacienteEntity> pacientes = pacienteService.getAll();
            lista = pacientes.stream()
                    .map(p -> historiaClinicaService
                            .obtenerUltimoHistorialPorPaciente(p.getIdPaciente())
                            .orElse(null))
                    .filter(h -> h != null)
                    .toList();
        }
        model.addAttribute("lista",lista);

        return "mantenimientoHistorial";
    }

    @GetMapping ("/detalle/{idPaciente}")
    String detallePaciente(@PathVariable Integer idPaciente, Model model) {

        HistoriaClinicaEntity nuevaHistoria = new HistoriaClinicaEntity();
        PacienteEntity paciente = pacienteService.getById(idPaciente);
        nuevaHistoria.setPaciente(paciente);

        model.addAttribute("paciente", paciente);
        model.addAttribute("historiales",historiaClinicaService.buscarPorPacienteNombre(paciente.getNombre()));
        model.addAttribute("historiaClinica",nuevaHistoria);

        nuevaHistoria.setFechaRegistro(LocalDate.now());


        return "detalleHistorial";
    }

    @PostMapping("/guardar")
    String guardar(@ModelAttribute HistoriaClinicaEntity historiaClinica){

        if(historiaClinica.getPaciente() == null || historiaClinica.getPaciente().getIdPaciente() == null){

            throw new IllegalArgumentException("Paciente no puede ser NULL");
        }

        PacienteEntity paciente = pacienteService.getById(historiaClinica.getPaciente().getIdPaciente());
        historiaClinica.setPaciente(paciente);

        historiaClinica.setFechaRegistro(LocalDate.now());
        historiaClinicaService.create(historiaClinica);

        return "redirect:/historial/detalle/" + paciente.getIdPaciente();
    }
}
