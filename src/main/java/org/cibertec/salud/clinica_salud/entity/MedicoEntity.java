package org.cibertec.salud.clinica_salud.entity;

import jakarta.persistence.*;
import lombok.Data;


    @Data
    @Entity
    @Table(name = "Medico")
    public class MedicoEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idMedico")
        private Integer idMedico;

        @Column(name = "Nombres")
        private String nombres;

        @Column(name = "Apellidos")
        private String apellidos;

        @Column(name = "Especialidad")
        private String especialidad;

        @Column(name = "Estado")
        private boolean estado;
    }

