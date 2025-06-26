package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "proveedor")

public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProveedor")
    private Integer idProveedor;

    @Column(name = "razonSocial")
    private String razonSocial ;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "telefono")
    private String telefono ;

    @Column(name = "correo")
    private String correo;

}
