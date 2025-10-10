package org.cibertec.salud.clinica_salud.controller;
import org.cibertec.salud.clinica_salud.entity.PacienteEntity;
import org.cibertec.salud.clinica_salud.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paciente")

public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/inicio")

String inicio(Model model){

        PacienteEntity paciente = new PacienteEntity();
        model.addAttribute("paciente",paciente);
        model.addAttribute("lista", pacienteService.getAll());
        return "mantenimientoPaciente";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute  PacienteEntity paciente){
        String paci =
        paciente.getIdPaciente()+
        paciente.getNombre()+
        paciente.getApellido()+
        paciente.getDni()+
        paciente.getEdad()+
        paciente.getSexo()+
        paciente.getTelefono()+
        paciente.getDireccion();
        pacienteService.create(paciente);
        if(paciente.getIdPaciente() == null) {
            pacienteService.create(paciente);
        } else {
            pacienteService.modify(paciente);
        }

        return "redirect:/paciente/inicio";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("idPaciente") Integer idPaciente, Model model){
        PacienteEntity paciente = pacienteService.getById(idPaciente);
        model.addAttribute("paciente",paciente);
        model.addAttribute("lista", pacienteService.getAll());
        return "mantenimientoPaciente";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        pacienteService.remove(id);
        return "redirect:/paciente/inicio";
    }

}
