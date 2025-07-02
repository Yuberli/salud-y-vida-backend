package org.cibertec.salud.clinica_salud.controller;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.cibertec.salud.clinica_salud.entity.CitaMedicaEntity;
import org.cibertec.salud.clinica_salud.service.CitaMedicaService;
import org.cibertec.salud.clinica_salud.service.MedicoService;
import org.cibertec.salud.clinica_salud.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/cita")

public class CitaMedicaController {

    @Autowired
    private CitaMedicaService citaMedicaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;


    @GetMapping("/inicio")

    String inicio(Model model) {

        CitaMedicaEntity citaMedica = new CitaMedicaEntity();
        model.addAttribute("citaMedica", citaMedica);
        model.addAttribute("lista",citaMedicaService.getAll());
        model.addAttribute("pacientes",pacienteService.getAll());
        model.addAttribute("medicos",medicoService.getAll());
        return "mantenimientoCita";

    }


    @PostMapping("/guardar")
    public String guardar(@ModelAttribute CitaMedicaEntity citaMedica, RedirectAttributes redirectAttributes) {
        try {
            // Validar fecha futura
            if (citaMedica.getFecha().isBefore(LocalDate.now())) {
                redirectAttributes.addFlashAttribute("error", "La fecha debe ser futura a la fecha actual");
                return "redirect:/cita/inicio";
            }

            // Verificar creación o edición
            if (citaMedica.getIdCita() == null) {
                citaMedicaService.create(citaMedica);
                redirectAttributes.addFlashAttribute("success", "Cita médica creada exitosamente");
            } else {
                citaMedicaService.modify(citaMedica);
                redirectAttributes.addFlashAttribute("success", "Cita médica actualizada exitosamente");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al procesar la cita: " + e.getMessage());
        }

        return "redirect:/cita/inicio";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("idCita") Integer idCita, Model model) {
        try {
            CitaMedicaEntity cita = citaMedicaService.getById(idCita);
            System.out.println("🟢 Cita: " + cita);
            System.out.println("🟢 Fecha: " + cita.getFecha());
            System.out.println("🟢 Hora: " + cita.getHora());
            model.addAttribute("citaMedica", cita);
            model.addAttribute("lista", citaMedicaService.getAll());
            model.addAttribute("pacientes", pacienteService.getAll());
            model.addAttribute("medicos", medicoService.getAll());
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo cargar la cita para editar");
        }
        return "mantenimientoCita";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            citaMedicaService.remove(id);
            redirectAttributes.addFlashAttribute("success", "Cita eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la cita");
        }
        return "redirect:/cita/inicio";
    }

    @Controller
    @RequestMapping("/reporte")
    public class ReporteCitaMedicaController {

        @Autowired
        private CitaMedicaService citaMedicaService;

        @GetMapping("/citas")
        @ResponseBody
        public ResponseEntity<byte[]> exportarReporteCitas() throws Exception {

            List<CitaMedicaEntity> lista = citaMedicaService.getAll();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

            InputStream jasperStream = new ClassPathResource("reportes/reporte_citas.jasper").getInputStream();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, new HashMap<>(), dataSource);

            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=citas.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        }
    }
}
