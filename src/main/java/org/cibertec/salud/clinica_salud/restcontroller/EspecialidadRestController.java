package org.cibertec.salud.clinica_salud.restcontroller;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.EspecialidadDto;
import org.cibertec.salud.clinica_salud.entity.EspecialidadEntity;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/especialidad")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class EspecialidadRestController {

    private final GenericRestService<EspecialidadEntity, EspecialidadDto, Integer> especialidadService;

    @GetMapping
    public ResponseEntity<List<EspecialidadDto>> listar () {
        List<EspecialidadDto> especialidad = especialidadService.findAll();
        return especialidad.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(especialidad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDto> obtener (@PathVariable Integer id) {
        return especialidadService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EspecialidadDto> crear(@RequestBody EspecialidadDto dto) {
        EspecialidadDto creado = especialidadService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadDto> actualizar(@PathVariable Integer id, @RequestBody EspecialidadDto dto) {
        if(especialidadService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        dto.setId(id);
        EspecialidadDto actualizado = especialidadService.save(dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EspecialidadDto> eliminar(@PathVariable Integer id) {
        if(especialidadService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        especialidadService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
