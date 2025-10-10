package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idrol")
    private Integer idrol;

    @Column(name = "nomrol")
    private String nomrol;

}
