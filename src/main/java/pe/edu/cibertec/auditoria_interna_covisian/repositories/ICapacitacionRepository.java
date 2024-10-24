package pe.edu.cibertec.auditoria_interna_covisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Capacitacion;

public interface ICapacitacionRepository extends JpaRepository<Capacitacion, Integer> {
}
