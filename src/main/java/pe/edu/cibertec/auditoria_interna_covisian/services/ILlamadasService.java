package pe.edu.cibertec.auditoria_interna_covisian.services;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Llamada;

import java.util.List;

public interface ILlamadasService {
	List<Llamada> listarLlamadasPorDni(int dniEmpleado);
	Llamada llamadaPorOrden(int orden);
	void save(Llamada llamada);
	ResponseEntity<ByteArrayResource> reporteLlamada(int nroOrden);

}
