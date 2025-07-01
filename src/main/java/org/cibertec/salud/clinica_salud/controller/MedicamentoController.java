package org.cibertec.salud.clinica_salud.controller;
import org.cibertec.salud.clinica_salud.entity.MedicamentoEntity;
import org.cibertec.salud.clinica_salud.service.MedicamentoService;
import org.cibertec.salud.clinica_salud.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/medicamento")
public class MedicamentoController {
    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/inicio")
    String inicio(Model model) {
        MedicamentoEntity medicamento = new MedicamentoEntity();
        model.addAttribute("medicamento", medicamento);
        model.addAttribute("lista", medicamentoService.getAll());
        model.addAttribute("proveedores", proveedorService.getAll());
        return "mantenimientoMedicamento";
    }

    @PostMapping("/guardar")
    String guardar(@ModelAttribute MedicamentoEntity medicamento,
                          RedirectAttributes redirectAttributes) {
        try {
            if (medicamento.getIdMedicamento() == null) {
                medicamentoService.create(medicamento);
                redirectAttributes.addFlashAttribute("success", "Medicamento registrado.");
            } else {
                medicamentoService.modify(medicamento);
                redirectAttributes.addFlashAttribute("success", "Medicamento actualizado.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/medicamento/inicio";
    }

    @GetMapping("/editar")
    String editar(@RequestParam("idMedicamento") Integer idMedicamento, Model model) {
        MedicamentoEntity medicamento = medicamentoService.getById(idMedicamento);
        model.addAttribute("medicamento", medicamento);
        model.addAttribute("lista", medicamentoService.getAll());
        model.addAttribute("proveedores", proveedorService.getAll());
        return "mantenimientoMedicamento";
    }

    @GetMapping("/eliminar/{id}")
    String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            medicamentoService.remove(id);
            redirectAttributes.addFlashAttribute("success", "Medicamento eliminado.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar.");
        }
        return "redirect:/medicamento/inicio";
    }
}
