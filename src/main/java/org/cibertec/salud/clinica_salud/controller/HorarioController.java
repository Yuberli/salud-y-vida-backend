package org.cibertec.salud.clinica_salud.controller;

import org.cibertec.salud.clinica_salud.entity.CitaMedicaEntity;
import org.cibertec.salud.clinica_salud.entity.HorarioEntity;
import org.cibertec.salud.clinica_salud.entity.MedicoEntity;
import org.cibertec.salud.clinica_salud.repository.CitaMedicaRepository;
import org.cibertec.salud.clinica_salud.service.HorarioService;
import org.cibertec.salud.clinica_salud.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    @GetMapping("/disponibles")
    public List<HorarioEntity> getHorariosDisponibles(

            @RequestParam("idMedico") Integer idMedico,
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {

        List<HorarioEntity> horarios = horarioService.findByMedico_IdMedicoAndEstado(idMedico, true);

        List<CitaMedicaEntity> citasOcupadas = citaMedicaRepository.findByMedico_IdMedicoAndFecha(idMedico, fecha);

        List<LocalTime> horasOcupadas = citasOcupadas.stream()
                .map(CitaMedicaEntity::getHora)
                .collect(Collectors.toList());

        return horarios.stream()
                .filter(horario -> !horasOcupadas.contains(horario.getHora()))
                .collect(Collectors.toList());
    }
}

@Controller
@RequestMapping("/horario/registro")
class HorarioRegistroController {

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        model.addAttribute("medicos", medicoService.getAll());

        List<HorarioEntity> horarios = horarioService.getAll();

        Map<MedicoEntity, List<HorarioRango>> agrupados = new LinkedHashMap<>();

        horarios.stream()
                .collect(Collectors.groupingBy(HorarioEntity::getMedico))
                .forEach((medico, bloques) -> {
                    List<LocalTime> horas = bloques.stream()
                            .map(HorarioEntity::getHora)
                            .sorted()
                            .collect(Collectors.toList());

                    List<HorarioRango> rangos = new ArrayList<>();
                    LocalTime inicio = null, anterior = null;

                    for (LocalTime hora : horas) {
                        if (inicio == null) {
                            inicio = hora;
                            anterior = hora;
                        } else if (hora.equals(anterior.plusMinutes(30))) {
                            anterior = hora;
                        } else {
                            rangos.add(new HorarioRango(inicio, anterior));
                            inicio = hora;
                            anterior = hora;
                        }
                    }
                    if (inicio != null) {
                        rangos.add(new HorarioRango(inicio, anterior));
                    }

                    agrupados.put(medico, rangos);
                });

        model.addAttribute("horariosAgrupados", agrupados);

        List<LocalTime> horasDisponibles = new ArrayList<>();
        for (int i = 8; i <= 20; i++) {
            horasDisponibles.add(LocalTime.of(i, 0));
            horasDisponibles.add(LocalTime.of(i, 30));
        }
        model.addAttribute("horasDisponibles", horasDisponibles);

        return "mantenimientoHorario";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam("idMedico") Integer idMedico,
                          @RequestParam("desde") String desde,
                          @RequestParam("hasta") String hasta,
                          RedirectAttributes redirect) {

        MedicoEntity medico = medicoService.getById(idMedico);
        LocalTime horaInicio = LocalTime.parse(desde);
        LocalTime horaFin = LocalTime.parse(hasta);

        if (horaInicio.isAfter(horaFin)) {
            redirect.addFlashAttribute("error", "Hora 'Desde' no puede ser mayor que 'Hasta'.");
            return "redirect:/horario/registro/inicio";
        }

        LocalTime actual = horaInicio;
        while (!actual.isAfter(horaFin)) {
            HorarioEntity nuevo = new HorarioEntity();
            nuevo.setMedico(medico);
            nuevo.setHora(actual);
            nuevo.setEstado(true);
            horarioService.create(nuevo);
            actual = actual.plusMinutes(30);
        }

        redirect.addFlashAttribute("success", "Horario(s) registrado(s) correctamente.");
        return "redirect:/horario/registro/inicio";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirect) {
        horarioService.remove(id);
        redirect.addFlashAttribute("success", "Horario eliminado correctamente.");
        return "redirect:/horario/registro/inicio";
    }

    // ✅ Para evitar el error de compilación
    public record HorarioRango(LocalTime inicio, LocalTime fin) {}
}