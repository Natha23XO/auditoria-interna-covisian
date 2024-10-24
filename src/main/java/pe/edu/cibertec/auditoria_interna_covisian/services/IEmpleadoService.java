package pe.edu.cibertec.auditoria_interna_covisian.services;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Empleado;

import java.util.List;

public interface IEmpleadoService {

    List<Empleado> listaEmpleados();
    Empleado findByUsername(String username);
    List<Empleado> findByArea(String area);
    Empleado findByDni(int dni);
    int EmpleadosArea(String area);
    ResponseEntity<ByteArrayResource> export();
}
