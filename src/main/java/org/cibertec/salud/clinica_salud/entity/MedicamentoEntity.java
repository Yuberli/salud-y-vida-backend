package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "medicamento")

public class MedicamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMedicamento")
    private Integer idMedicamento;

    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private ProveedorEntity proveedor;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "presentacion")
    private String presentacion;

    @Column(name = "concentracion")
    private String concentracion;

    @Column(name = "unidad")
    private String unidad;

    @Column(name = "stockActual")
    private Integer stockActual;

    @Column(name = "stockMinimo")
    private Integer stockMinimo;

    @Column(name = "Estado")
    private boolean estado;

}
