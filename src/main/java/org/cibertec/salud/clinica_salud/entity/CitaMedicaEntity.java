package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


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
    private Date fecha;

    @Column(name = "hora")
    private Date hora;

    @Column(name = "estado")
    private String estado;
}
