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
    @Table(name = "paciente")
    public class PacienteEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idPaciente")
        private Integer idPaciente;

        @Column(name = "nombre")
        private String nombre;

        @Column(name = "apellido")
        private String apellido;

        @Column(name = "dni")
        private String dni;

        @Column(name = "edad")
        private Integer edad;

        @Column(name = "sexo")
        private String sexo;

        @Column(name = "telefono")
        private String telefono;

        @Column(name = "direccion")
        private String direccion;
    }

