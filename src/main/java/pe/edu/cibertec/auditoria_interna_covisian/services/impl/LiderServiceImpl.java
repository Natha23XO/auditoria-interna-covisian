package pe.edu.cibertec.auditoria_interna_covisian.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Lider;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.LiderRepository;
import pe.edu.cibertec.auditoria_interna_covisian.services.ILiderService;

@AllArgsConstructor
@Service
public class LiderServiceImpl implements ILiderService {

    private LiderRepository liderRepository;
    @Override
    public Lider findByDni(int dni) {
        return liderRepository.findByDniLider(dni);
    }
}
