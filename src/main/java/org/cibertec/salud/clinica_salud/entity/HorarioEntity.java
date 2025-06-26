package org.cibertec.salud.clinica_salud.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "horario")

public class HorarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idHorario")
    private Integer idHorario;

    @ManyToOne
    @JoinColumn(name = "idMedico")
    private MedicoEntity medico;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "hora")
    private Date hora;

    @Column(name = "estado")
    private boolean estado;
}
