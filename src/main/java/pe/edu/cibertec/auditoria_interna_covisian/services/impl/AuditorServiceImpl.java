package pe.edu.cibertec.auditoria_interna_covisian.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Auditor;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.AuditorRepository;
import pe.edu.cibertec.auditoria_interna_covisian.services.IAuditorService;

@Service
@AllArgsConstructor
public class AuditorServiceImpl implements IAuditorService {

    private final AuditorRepository auditorRepository;

    @Override
    public Auditor findByDni(int dni) {
        return auditorRepository.findByDniAuditor(dni);
    }
}
