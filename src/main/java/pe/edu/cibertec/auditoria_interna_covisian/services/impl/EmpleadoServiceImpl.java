package pe.edu.cibertec.auditoria_interna_covisian.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Empleado;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.EmpleadoRepository;
import pe.edu.cibertec.auditoria_interna_covisian.restclient.placeholder.iclientservice.EmpleadoClient;
import pe.edu.cibertec.auditoria_interna_covisian.services.IEmpleadoService;

import java.util.List;

@Service
@AllArgsConstructor
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoClient empleadoClient;

    @Override
    public List<Empleado> listaEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado findByUsername(String username) {
        return empleadoRepository.findByUsername(username);
    }

    @Override
    public List<Empleado> findByArea(String area) {
        return empleadoRepository.findByArea(area);
    }

    @Override
    public Empleado findByDni(int dni) {
        return empleadoRepository.findByDniEmpleado(dni);
    }

    @Override
    public int EmpleadosArea(String area) {
        return  empleadoRepository.cantidadEmpleadosArea(area);
    }

    @Override
    public ResponseEntity<ByteArrayResource> export() {
        return empleadoClient.exportEmpleados();
    }
}
