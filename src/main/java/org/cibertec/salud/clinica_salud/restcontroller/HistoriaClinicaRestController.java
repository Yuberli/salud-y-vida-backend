package org.cibertec.salud.clinica_salud.restcontroller;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.HistoriaClinicaDto;
import org.cibertec.salud.clinica_salud.entity.HistoriaClinicaEntity;
import org.cibertec.salud.clinica_salud.service.HistoriaClinicaService;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/historia")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class HistoriaClinicaRestController {

    private final GenericRestService<HistoriaClinicaEntity, HistoriaClinicaDto, Integer> historiaClinicaService;

    @GetMapping
    public ResponseEntity<List<HistoriaClinicaDto>> listar() {
        List<HistoriaClinicaDto> historia = historiaClinicaService.findAll();
        return historia.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(historia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinicaDto> obtener(@PathVariable Integer id) {
        return historiaClinicaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HistoriaClinicaDto> crear(@RequestBody HistoriaClinicaDto dto) {
        HistoriaClinicaDto creado =  historiaClinicaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaClinicaDto> actualizar(@PathVariable Integer id, @RequestBody HistoriaClinicaDto dto) {
        if(historiaClinicaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        dto.setId(id);
        HistoriaClinicaDto actualizado =  historiaClinicaService.save(dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HistoriaClinicaDto> eliminar(@PathVariable Integer id) {
        if(historiaClinicaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        historiaClinicaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
