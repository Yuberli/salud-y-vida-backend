package org.cibertec.salud.clinica_salud.controller;
import org.cibertec.salud.clinica_salud.entity.IngresoMedicamentoEntity;
import org.cibertec.salud.clinica_salud.service.IngresoMedicamentoService;
import org.cibertec.salud.clinica_salud.service.MedicamentoService;
import org.cibertec.salud.clinica_salud.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/ingreso")
public class IngresoMedicamentoController {

    @Autowired
    private IngresoMedicamentoService ingresoMedicamentoService;

    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        IngresoMedicamentoEntity ingreso = new IngresoMedicamentoEntity();
        model.addAttribute("ingresoMedicamento", ingreso);
        model.addAttribute("ingresos", ingresoMedicamentoService.getAll());
        model.addAttribute("medicamentos", medicamentoService.getAll());
        model.addAttribute("proveedores", proveedorService.getAll());
        return "mantenimientoIngresoMedicamento";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute IngresoMedicamentoEntity ingresoMedicamento) {

        ingresoMedicamento.setFechaIngreso(new Date());

        // Puedes sumar la cantidad al stock del medicamento:
        var medicamento = medicamentoService.getById(ingresoMedicamento.getMedicamento().getIdMedicamento());

        medicamento.setStockActual(medicamento.getStockActual() + ingresoMedicamento.getCantidad());

        medicamentoService.modify(medicamento);
        ingresoMedicamento.setMedicamento(medicamento);
        ingresoMedicamentoService.create(ingresoMedicamento);

        return "redirect:/ingreso/inicio";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        ingresoMedicamentoService.remove(id);
        return "redirect:/ingreso/inicio";
    }
}
