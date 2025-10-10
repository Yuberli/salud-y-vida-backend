package org.cibertec.salud.clinica_salud.restcontroller;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.MedicoDto;
import org.cibertec.salud.clinica_salud.entity.MedicoEntity;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class MedicoRestController {

    private final GenericRestService<MedicoEntity, MedicoDto,Integer> medicoService;


    @GetMapping
    public ResponseEntity<List<MedicoDto>> listar() {
        List<MedicoDto> medicos = medicoService.findAll();
        return medicos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDto> obtener(@PathVariable Integer id) {
        return medicoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicoDto> crear(@RequestBody MedicoDto dto) {
        MedicoDto creado = medicoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDto> actualizar(@PathVariable Integer id, @RequestBody MedicoDto dto) {

        if(medicoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        dto.setId(id);
        MedicoDto actualizado = medicoService.save(dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicoDto> eliminar(@PathVariable Integer id) {
        if (medicoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        medicoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
