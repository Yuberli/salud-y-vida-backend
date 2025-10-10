package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private Boolean estado;
}
