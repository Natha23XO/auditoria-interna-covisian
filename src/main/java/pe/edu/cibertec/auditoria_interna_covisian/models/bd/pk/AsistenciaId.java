package pe.edu.cibertec.auditoria_interna_covisian.models.bd.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaId implements Serializable {
    @Column(name = "id_capacitacion")
    private Integer idCapacitacion;
    @Column(name = "dni_empleado")
    private Integer dniEmpleado;
}
