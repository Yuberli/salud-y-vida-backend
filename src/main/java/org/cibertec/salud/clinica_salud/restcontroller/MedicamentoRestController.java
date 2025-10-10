package org.cibertec.salud.clinica_salud.restcontroller;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.MedicamentoDto;
import org.cibertec.salud.clinica_salud.entity.MedicamentoEntity;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicamentos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoRestController {

    private final GenericRestService<MedicamentoEntity, MedicamentoDto,Integer> medicamentoService;

    @GetMapping
    public ResponseEntity<List<MedicamentoDto>> listar(){
        List<MedicamentoDto> medicamentos = medicamentoService.findAll();
        return medicamentos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(medicamentos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDto> obtener(@PathVariable Integer id){
        return medicamentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicamentoDto> crear(@RequestBody MedicamentoDto dto){
        MedicamentoDto creado = medicamentoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoDto> actualizar(@PathVariable Integer id, @RequestBody MedicamentoDto dto){
        if (medicamentoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        dto.setId(id);
        MedicamentoDto actualizado = medicamentoService.save(dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicamentoDto> eliminar(@PathVariable Integer id){
        if (medicamentoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        medicamentoService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
