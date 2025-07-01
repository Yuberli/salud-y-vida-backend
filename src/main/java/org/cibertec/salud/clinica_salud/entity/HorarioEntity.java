package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;


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

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "estado")
    private boolean estado;
}
