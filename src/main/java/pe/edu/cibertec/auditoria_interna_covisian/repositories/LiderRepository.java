package pe.edu.cibertec.auditoria_interna_covisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Lider;

public interface LiderRepository extends JpaRepository<Lider,Integer> {
    @Query(value = "SELECT * FROM Lider WHERE id = :iduser", nativeQuery = true)
    Lider findByIdUser(@Param("iduser") Long iduser);
    Lider findByDniLider(int dni);
}
