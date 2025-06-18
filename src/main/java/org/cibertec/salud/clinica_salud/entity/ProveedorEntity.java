package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "Proveedor")

public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProveedor")
    private Integer idProveedor;

    @Column(name = "RazonSocial")
    private String razonSocial ;

    @Column(name = "RUC")
    private String ruc;

    @Column(name = "Telefono")
    private String telefono ;

}
