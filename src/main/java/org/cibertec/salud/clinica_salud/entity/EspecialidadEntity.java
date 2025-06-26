package org.cibertec.salud.clinica_salud.entity;

import jakarta.persistence.*;
import lombok.Data;

    @Data
    @Entity
    @Table(name = "especialidad")

    public class EspecialidadEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idEspecialidad")
        private Integer idEspecialidad;

        @Column(name = "nombre")
        private String nombre;


    }


