package pe.edu.cibertec.auditoria_interna_covisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Feedback;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Memorandum;

import java.util.List;

public interface IFeedbackRepository extends JpaRepository<Feedback, Integer> {
    @Query("SELECT m FROM Memorandum m JOIN m.feedback f JOIN f.empleado emp WHERE emp.dniEmpleado = :dni")
    List<Memorandum> findByEmpleado(@Param("dni") Integer dni);
    @Query("SELECT m FROM Memorandum m JOIN m.feedback f JOIN f.empleado emp WHERE emp.area = :area")
    List<Memorandum> findByArea(@Param("area") String area);
}
