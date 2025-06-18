package org.cibertec.salud.clinica_salud.entity;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "Medicamento")

public class MedicamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMedicamento")
    private Integer idMedicamento;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Presentacion")
    private String presentacion;

    @Column(name = "Concentracion")
    private String concentracion;

    @Column(name = "Unidad")
    private String unidad;

    @Column(name = "StockActual")
    private Integer stockActual;

    @Column(name = "Estado")
    private boolean estado;

}
