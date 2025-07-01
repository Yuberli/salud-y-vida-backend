package org.cibertec.salud.clinica_salud.controller;
import org.cibertec.salud.clinica_salud.entity.ProveedorEntity;
import org.cibertec.salud.clinica_salud.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proveedor")

public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        model.addAttribute("proveedor", new ProveedorEntity());
        model.addAttribute("lista", proveedorService.getAll());
        return "mantenimientoProveedor";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ProveedorEntity proveedor, RedirectAttributes redirect) {
        if (proveedor.getIdProveedor() == null) {
            redirect.addFlashAttribute("success", "Proveedor registrado correctamente.");
        } else {
            redirect.addFlashAttribute("success", "Proveedor actualizado correctamente.");
        }
        proveedorService.create(proveedor);
        return "redirect:/proveedor/inicio";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("idProveedor") Integer idProveedor, Model model) {
        model.addAttribute("proveedor", proveedorService.getById(idProveedor));
        model.addAttribute("lista", proveedorService.getAll());
        return "mantenimientoProveedor";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirect) {
        proveedorService.remove(id);
        redirect.addFlashAttribute("success", "Proveedor eliminado correctamente.");
        return "redirect:/proveedor/inicio";
    }
}
