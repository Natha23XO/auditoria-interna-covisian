package pe.edu.cibertec.auditoria_interna_covisian.services;


import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Sala;

import java.util.List;
import java.util.Optional;

public interface ISalaService {
    Optional<Sala> findById(int idSala);
    List<Sala> findAll();
}
