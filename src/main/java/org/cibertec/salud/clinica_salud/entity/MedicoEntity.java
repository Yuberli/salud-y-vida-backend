package org.cibertec.salud.clinica_salud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
    @Data
    @Entity
    @Table(name = "medico")
    public class MedicoEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idMedico")
        private Integer idMedico;

        @Column(name = "nombre")
        private String nombre;

        @Column(name = "apellido")
        private String apellido;

        @ManyToOne
        @JoinColumn(name = "idEspecialidad")
        private EspecialidadEntity especialidad;

        @Column(name = "telefono")
        private String telefono;

        @Column(name = "Estado")
        private Boolean estado;
    }

