package pe.edu.cibertec.auditoria_interna_covisian.restclient.placeholder.iclientservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "empleadoClient", url = "http://localhost:8082/api/v1/empleado")
public interface EmpleadoClient {

    @GetMapping("/export")
    ResponseEntity<ByteArrayResource> exportEmpleados();
}
