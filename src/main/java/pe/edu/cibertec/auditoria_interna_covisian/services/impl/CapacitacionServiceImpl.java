package pe.edu.cibertec.auditoria_interna_covisian.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Asistencia;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Capacitacion;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.pk.AsistenciaId;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.IAsistenciaRepository;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.ICapacitacionRepository;
import pe.edu.cibertec.auditoria_interna_covisian.services.ICapacitacionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CapacitacionServiceImpl implements ICapacitacionService {
    private final ICapacitacionRepository capacitacionRepository;
    private final IAsistenciaRepository asistenciaRepository;

    @Override
    @Transactional
    public void saveTrainingAndAssistance(Capacitacion capacitacion, Asistencia asistencia) {
        Capacitacion savedCapacitacion = capacitacionRepository.save(capacitacion);
        AsistenciaId idAsistencia = new AsistenciaId(savedCapacitacion.getIdCapacitacion(), asistencia.getEmpleado().getDniEmpleado());
        asistencia.setIdAsistencia(idAsistencia);
        asistenciaRepository.save(asistencia);
    }

    @Override
    public List<Asistencia> findByEmpleado(Integer dni) {
        return asistenciaRepository.findByEmpleado(dni);
    }

    @Override
    public List<Asistencia> findByArea(String area) {
        return asistenciaRepository.findByArea(area);
    }
}
