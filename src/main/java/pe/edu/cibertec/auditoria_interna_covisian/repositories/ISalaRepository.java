package pe.edu.cibertec.auditoria_interna_covisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Sala;

public interface ISalaRepository extends JpaRepository<Sala, Integer> {
}
