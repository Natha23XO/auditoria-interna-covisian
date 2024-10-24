package pe.edu.cibertec.auditoria_interna_covisian.services.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Llamada;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.LlamadasRepository;
import pe.edu.cibertec.auditoria_interna_covisian.restclient.placeholder.iclientservice.LlamadaClient;
import pe.edu.cibertec.auditoria_interna_covisian.services.ILlamadasService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LlamadasServiceImpl implements ILlamadasService {

	private final LlamadasRepository llamadasRepository;
	private final LlamadaClient llamadaClient;

	@Override
	public List<Llamada> listarLlamadasPorDni(int dniEmpleado) {
		return llamadasRepository.findByDniEmpleado(dniEmpleado);
	}

	@Override
	public Llamada llamadaPorOrden(int orden) {
		return llamadasRepository.findByOrden(orden);
	}

	@Override
	public void save(Llamada llamada) {
		 llamadasRepository.save(llamada);
	}

	@Override
	public ResponseEntity<ByteArrayResource> reporteLlamada(int nroOrden) {
		return llamadaClient.reportLLamada(nroOrden);
	}
}
