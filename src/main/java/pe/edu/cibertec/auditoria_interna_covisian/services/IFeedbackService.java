package pe.edu.cibertec.auditoria_interna_covisian.services;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Feedback;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Memorandum;

import java.util.List;

public interface IFeedbackService {
    void saveFeedbackAndMemorandum(Feedback feedback, Memorandum memorandum);
    List<Memorandum> findByEmpleado(Integer dni);
    List<Memorandum> findByArea(String area);
    Memorandum findMemorandumById(Integer id);
    ResponseEntity<ByteArrayResource> reportFeedback(Integer idMemorandum);
}
