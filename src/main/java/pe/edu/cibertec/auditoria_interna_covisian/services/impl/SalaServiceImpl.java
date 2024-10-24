package pe.edu.cibertec.auditoria_interna_covisian.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Sala;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.ISalaRepository;
import pe.edu.cibertec.auditoria_interna_covisian.services.ISalaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalaServiceImpl implements ISalaService {
    private final ISalaRepository salaRepository;

    @Override
    public Optional<Sala> findById(int idSala) {
        return salaRepository.findById(idSala);
    }

    @Override
    public List<Sala> findAll() {
        return salaRepository.findAll();
    }
}
