package org.cibertec.salud.clinica_salud.restcontroller;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.PacienteDto;
import org.cibertec.salud.clinica_salud.entity.PacienteEntity;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class PacienteRestController {

    private final GenericRestService<PacienteEntity, PacienteDto, Integer> pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteDto>> listar() {
        List<PacienteDto> pacientes = pacienteService.findAll();
        return pacientes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> obtener(@PathVariable Integer id) {
        return pacienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PacienteDto> crear(@RequestBody PacienteDto dto) {
        PacienteDto creado = pacienteService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> actualizar(@PathVariable Integer id, @RequestBody PacienteDto dto) {
        if (pacienteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        dto.setId(id);
        PacienteDto actualizado = pacienteService.save(dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDto> eliminar(@PathVariable Integer id) {
        if (pacienteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pacienteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
