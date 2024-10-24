package pe.edu.cibertec.auditoria_interna_covisian.services;

import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Asistencia;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Capacitacion;

import java.util.List;

public interface ICapacitacionService {
    void saveTrainingAndAssistance(Capacitacion capacitacion, Asistencia asistencia);
    List<Asistencia> findByEmpleado(Integer dni);
    List<Asistencia> findByArea(String area);
}
