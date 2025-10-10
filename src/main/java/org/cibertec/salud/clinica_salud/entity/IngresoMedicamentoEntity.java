package org.cibertec.salud.clinica_salud.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ingreso_medicamento")

public class IngresoMedicamentoEntity {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "idIngreso")
    private Integer idIngreso;

    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private ProveedorEntity proveedor;

    @ManyToOne
    @JoinColumn(name = "idMedicamento")
    private MedicamentoEntity medicamento;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "fechaIngreso")
    private Date fechaIngreso;

    @Column(name = "observaciones")
    private String observaciones;


}
