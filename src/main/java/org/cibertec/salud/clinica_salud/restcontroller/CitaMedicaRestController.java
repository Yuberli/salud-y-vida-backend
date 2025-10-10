package org.cibertec.salud.clinica_salud.restcontroller;

import lombok.RequiredArgsConstructor;
import org.cibertec.salud.clinica_salud.dto.CitaMeditaDto;
import org.cibertec.salud.clinica_salud.entity.CitaMedicaEntity;
import org.cibertec.salud.clinica_salud.servicerest.GenericRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cita")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CitaMedicaRestController {

    private final GenericRestService<CitaMedicaEntity, CitaMeditaDto, Integer> citaMedicaService;

    @GetMapping
    public ResponseEntity<List<CitaMeditaDto>> listar (){
        List<CitaMeditaDto> cita = citaMedicaService.findAll();
        return cita.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(cita);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaMeditaDto> obtener(@PathVariable Integer id){
        return citaMedicaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CitaMeditaDto> crear (@RequestBody CitaMeditaDto dto){
        CitaMeditaDto creado =  citaMedicaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaMeditaDto> actualizar(@PathVariable Integer id, @RequestBody CitaMeditaDto dto){
        if(citaMedicaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        dto.setId(id);
        CitaMeditaDto actualizado = citaMedicaService.save(dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CitaMeditaDto> eliminar(@PathVariable Integer id){
        if(citaMedicaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        citaMedicaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
