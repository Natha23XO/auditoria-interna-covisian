package pe.edu.cibertec.auditoria_interna_covisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	@Query(value = "SELECT MAX(id) FROM usuarios", nativeQuery = true) //Solo pq no hay inserciones concurrentes
	int ultimoCodigo();
}
