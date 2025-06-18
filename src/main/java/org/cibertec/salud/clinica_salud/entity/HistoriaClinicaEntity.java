package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "HistoriaClinica")
public class HistoriaClinicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHistoria")
    private Integer idHistoria;

    @Column(name = "IdPaciente")
    private Integer idPaciente;

    @Column(name = "Alergias")
    private String alergias;

    @Column(name = "Descripcion")
    private String descripcion ;
}
