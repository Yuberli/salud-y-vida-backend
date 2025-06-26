package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;



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
}
