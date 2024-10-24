package pe.edu.cibertec.auditoria_interna_covisian.services;

import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Lider;

public interface ILiderService {
    Lider findByDni(int dni);
}
