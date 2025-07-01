package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;



@Data
@Entity
@Table(name = "citaMedica")
public class CitaMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCita")
    private Integer idCita;

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "idMedico")
    private MedicoEntity medico;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "estado")
    private String estado;
}
