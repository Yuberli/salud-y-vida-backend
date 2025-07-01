package org.cibertec.salud.clinica_salud.controller;

import org.cibertec.salud.clinica_salud.entity.EspecialidadEntity;
import org.cibertec.salud.clinica_salud.entity.MedicoEntity;
import org.cibertec.salud.clinica_salud.service.EspecialidadService;
import org.cibertec.salud.clinica_salud.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        MedicoEntity medico = new MedicoEntity();
        model.addAttribute("medico", medico);
        model.addAttribute("medicos", medicoService.getAll());
        model.addAttribute("especialidades", especialidadService.getAll());
        return "mantenimientoMedico";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute MedicoEntity medico, RedirectAttributes redirect) {
        medicoService.create(medico);
        redirect.addFlashAttribute("success", "Médico registrado correctamente");
        return "redirect:/medico/inicio";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("idMedico") Integer idMedico, Model model) {
        MedicoEntity medico = medicoService.getById(idMedico);
        model.addAttribute("medico", medico);
        model.addAttribute("medicos", medicoService.getAll());
        model.addAttribute("especialidades", especialidadService.getAll());
        return "mantenimientoMedico";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirect) {
        medicoService.remove(id);
        redirect.addFlashAttribute("success", "Médico eliminado correctamente");
        return "redirect:/medico/inicio";
    }
}
