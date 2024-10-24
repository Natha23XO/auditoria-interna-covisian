package pe.edu.cibertec.auditoria_interna_covisian.restclient.placeholder.iclientservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "llamadaClient", url = "http://localhost:8082/api/v1/llamada")
public interface LlamadaClient {

    @GetMapping("/report/{nroOrden}")
    ResponseEntity<ByteArrayResource> reportLLamada(@PathVariable("nroOrden") Integer nroOrden);
}
