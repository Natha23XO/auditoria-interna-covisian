package pe.edu.cibertec.auditoria_interna_covisian.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Feedback;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Memorandum;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.IFeedbackRepository;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.IMemorandumRepository;
import pe.edu.cibertec.auditoria_interna_covisian.restclient.placeholder.iclientservice.MemorandumClient;
import pe.edu.cibertec.auditoria_interna_covisian.services.IFeedbackService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements IFeedbackService {
    private final IFeedbackRepository feedbackRepository;
    private final IMemorandumRepository memorandumRepository;
    private final MemorandumClient memorandumClient;

    @Override
    @Transactional
    public void saveFeedbackAndMemorandum(Feedback feedback, Memorandum memorandum) {
        Feedback savedFeedback = feedbackRepository.save(feedback);
        memorandum.setFeedback(savedFeedback);
        memorandumRepository.save(memorandum);
    }

    @Override
    public List<Memorandum> findByEmpleado(Integer dni) {
        return feedbackRepository.findByEmpleado(dni);
    }

    @Override
    public List<Memorandum> findByArea(String area) {
        return feedbackRepository.findByArea(area);
    }

    @Override
    public Memorandum findMemorandumById(Integer id) {
        Memorandum memorandum = null;
        Optional<Memorandum> optional = memorandumRepository.findById(id);
        if(optional.isPresent())
            memorandum = optional.get();
        return memorandum;
    }

    @Override
    public ResponseEntity<ByteArrayResource> reportFeedback(Integer idMemorandum) {
        return memorandumClient.reportFeedback(idMemorandum);
    }
}
