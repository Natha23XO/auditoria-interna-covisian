package pe.edu.cibertec.auditoria_interna_covisian.models.bd;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "lider")
@NoArgsConstructor
public class Lider {
    @Id
    @Column(name = "dni_lider")
    private int dniLider;
    @Column(name = "nombre_lider")
    private String nombreLider;
    @Column(name = "apellido_lider")
    private String apellidoLider;
    @Column(name = "area")
    private String area;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;
}
