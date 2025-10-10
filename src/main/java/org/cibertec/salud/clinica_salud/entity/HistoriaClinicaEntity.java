package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Builder
@AllArgsConstructor
@NoArgsConstructor

@Data
@Entity
@Table(name = "historiaclinica")
public class HistoriaClinicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistoria")
    private Integer idHistoria;

    @ManyToOne
    @JoinColumn(name = "IdPaciente")
    private PacienteEntity paciente;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "fechaRegistro")
    private LocalDate fechaRegistro;
}
