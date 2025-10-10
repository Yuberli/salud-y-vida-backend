package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Data
@Entity
@Table(name = "usuario")

public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private Integer idusuario;

    @Column(name = "nomusuario")
    private String nomusuario;

    @Column(name="nombres")
    private String nombres;

    @Column(name="apellidos")
    private String apellidos;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToMany(cascade = CascadeType.MERGE,
    fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
    joinColumns = @JoinColumn(name="idusuario"),
    inverseJoinColumns = @JoinColumn(name="idrol"))

    private Set<RolEntity> roles;


}
