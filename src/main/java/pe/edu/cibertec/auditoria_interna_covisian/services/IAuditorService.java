package pe.edu.cibertec.auditoria_interna_covisian.services;

import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Auditor;

public interface IAuditorService {
	Auditor findByDni(int dni);
}
