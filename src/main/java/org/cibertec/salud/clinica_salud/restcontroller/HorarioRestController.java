package org.cibertec.salud.clinica_salud.restcontroller;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.HorarioDto;
import org.cibertec.salud.clinica_salud.entity.HorarioEntity;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/horario")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class HorarioRestController {

    private final GenericRestService<HorarioEntity, HorarioDto,Integer> horarioService;

    @GetMapping
    public ResponseEntity<List<HorarioDto>> listar() {
        List<HorarioDto> horario = horarioService.findAll();
        return horario.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(horario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDto> obtener(@PathVariable Integer id) {
        return horarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HorarioDto> create(@RequestBody HorarioDto dto) {
        HorarioDto creado = horarioService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioDto> actualizar(@PathVariable Integer id, @RequestBody HorarioDto dto) {
        if(horarioService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        dto.setId(id);
        HorarioDto actualizado = horarioService.save(dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HorarioDto> eliminar(@PathVariable Integer id) {
        if(horarioService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        horarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
