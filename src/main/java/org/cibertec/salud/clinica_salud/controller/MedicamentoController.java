package org.cibertec.salud.clinica_salud.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.cibertec.salud.clinica_salud.entity.IngresoMedicamentoEntity;
import org.cibertec.salud.clinica_salud.entity.MedicamentoEntity;
import org.cibertec.salud.clinica_salud.service.IngresoMedicamentoService;
import org.cibertec.salud.clinica_salud.service.MedicamentoService;
import org.cibertec.salud.clinica_salud.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

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


    @Controller
    @RequestMapping("/reporte")
    public class ReporteMedicamentoController {

        @Autowired
        private MedicamentoService medicamentoService;

        @GetMapping("/medicamentos")
        @ResponseBody
        public ResponseEntity<byte[]> exportarReporteMedicamentos() throws Exception {

            //Traer datos
            List<MedicamentoEntity> lista = medicamentoService.getAll();

            //Crear datasource
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

            //Cargar el .jasper compilado
            InputStream jasperStream = new ClassPathResource("reportes/reporte_medicamentos.jasper").getInputStream();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, new HashMap<>(), dataSource);

            //Exportar PDF
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=medicamentos.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        }
    }
}
