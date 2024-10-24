package pe.edu.cibertec.auditoria_interna_covisian.models.bd;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "salas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private Integer idSala;
    @Column(name = "nro_sala")
    private String nroSala;
}
