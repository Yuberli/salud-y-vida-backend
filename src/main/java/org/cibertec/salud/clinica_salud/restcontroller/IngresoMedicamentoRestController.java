package org.cibertec.salud.clinica_salud.restcontroller;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.IngresoMedicamentoDto;
import org.cibertec.salud.clinica_salud.entity.IngresoMedicamentoEntity;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingreso")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class IngresoMedicamentoRestController {

    private final GenericRestService<IngresoMedicamentoEntity, IngresoMedicamentoDto, Integer> ingresoMedicamentoService;

    @GetMapping
    public ResponseEntity<List<IngresoMedicamentoDto>> listar() {
        List<IngresoMedicamentoDto> ingreso =  ingresoMedicamentoService.findAll();
        return ingreso.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(ingreso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngresoMedicamentoDto> obtener(@PathVariable Integer id) {
        return ingresoMedicamentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IngresoMedicamentoDto> crear(@RequestBody IngresoMedicamentoDto dto) {
        IngresoMedicamentoDto creado =  ingresoMedicamentoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngresoMedicamentoDto> actualizar(@PathVariable Integer id, @RequestBody IngresoMedicamentoDto dto) {
        if(ingresoMedicamentoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        dto.setId(id);
        IngresoMedicamentoDto actualizado =  ingresoMedicamentoService.save(dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IngresoMedicamentoDto> eliminar(@PathVariable Integer id) {
        if(ingresoMedicamentoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ingresoMedicamentoService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
