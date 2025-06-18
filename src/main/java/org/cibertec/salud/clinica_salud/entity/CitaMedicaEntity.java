package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "CitaMedica")
public class CitaMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCita")
    private Integer idCita;

    @Column(name = "IdPaciente")
    private Integer idPaciente;

    @Column(name = "IdMedico")
    private Integer apellidos;

    @Column(name = "FechaHora")
    private LocalDateTime fechaHora;

    @Column(name = "Estado")
    private String estado;
}
