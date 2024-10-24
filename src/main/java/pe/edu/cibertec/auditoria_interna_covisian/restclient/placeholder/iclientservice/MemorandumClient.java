package pe.edu.cibertec.auditoria_interna_covisian.restclient.placeholder.iclientservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "memorandumClient", url = "http://localhost:8082/api/v1/memorandum")
public interface MemorandumClient {

    @GetMapping("/report/{idMemorandum}")
    ResponseEntity<ByteArrayResource> reportFeedback(@PathVariable("idMemorandum") Integer idMemorandum);
}
