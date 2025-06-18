package org.cibertec.salud.clinica_salud.entity;

import jakarta.persistence.*;
import lombok.Data;


    @Data
    @Entity
    @Table(name = "Paciente")
    public class PacienteEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idPaciente")
        private Integer idPaciente;

        @Column(name = "Nombres")
        private String nombres;

        @Column(name = "Apellidos")
        private String apellidos;

        @Column(name = "DNI")
        private String dni;

        @Column(name = "Edad")
        private String edad;

        @Column(name = "Sexo")
        private String sexo;

        @Column(name = "Telefono")
        private String telefono;

        @Column(name = "Direccion")
        private String direccion;
    }

