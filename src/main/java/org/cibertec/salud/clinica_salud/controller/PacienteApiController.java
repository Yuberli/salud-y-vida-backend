package org.cibertec.salud.clinica_salud.controller;

import org.cibertec.salud.clinica_salud.dto.PacienteDto;
import org.cibertec.salud.clinica_salud.servicerest.restimpl.PacienteRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteApiController {

    @Autowired
    private PacienteRestServiceImpl pacienteRestService;

    @GetMapping
    public ResponseEntity<List<PacienteDto>> getAllPacientes() {
        return ResponseEntity.ok(pacienteRestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PacienteDto>> getPaciente(@PathVariable Integer id) {
        return ResponseEntity.ok(pacienteRestService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PacienteDto> createPaciente(@RequestBody PacienteDto pacienteDto) {
        PacienteDto saved = pacienteRestService.save(pacienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved); // ✅ 201 CREATED en lugar de redirección
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> updatePaciente(@PathVariable Integer id, @RequestBody PacienteDto pacienteDto) {
        PacienteDto updated = pacienteRestService.save(pacienteDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Integer id) {
        pacienteRestService.deleteById(id);
        return ResponseEntity.noContent().build(); // ✅ 204 NO CONTENT
    }
}