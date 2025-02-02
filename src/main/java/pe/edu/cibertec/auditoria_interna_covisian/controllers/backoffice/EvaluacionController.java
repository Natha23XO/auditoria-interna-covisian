package pe.edu.cibertec.auditoria_interna_covisian.controllers.backoffice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Empleado;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Evaluacion;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Llamada;
import pe.edu.cibertec.auditoria_interna_covisian.services.IEmpleadoService;
import pe.edu.cibertec.auditoria_interna_covisian.services.IEvaluacionService;
import pe.edu.cibertec.auditoria_interna_covisian.services.ILlamadasService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
@RequestMapping("/evaluacion")
public class EvaluacionController {

    UserDetailsService userDetailsService;
    private ILlamadasService iLlamadasService;
    private IEvaluacionService iEvaluacionService;
    private IEmpleadoService iEmpleadoService;


    @GetMapping("/crear/{numeroOrden}")
    public String llamada(@PathVariable int numeroOrden, Model model, Principal principal){

        Llamada llamada = iLlamadasService.llamadaPorOrden(numeroOrden);
        model.addAttribute("llamada", llamada);

        Evaluacion evaluacion = new Evaluacion();
        model.addAttribute("evaluacion",evaluacion);

        LocalDateTime fh = LocalDateTime.now();
        model.addAttribute("fechaconhora",fh);

        return "backoffice/evaluacion/evaluacion";
    }

    @PostMapping("/guardarEvaluacion")
    public String guardarEvaluacion(@ModelAttribute("evaluacion") Evaluacion evaluacion, @RequestParam("numeroOrden") int numeroOrden, Model model, Principal principal){
        iEvaluacionService.save(evaluacion);
        Llamada llamada = iLlamadasService.llamadaPorOrden(numeroOrden);
        llamada.setEstadoAuditoria(true);
        iLlamadasService.save(llamada);
        return "backoffice/llamada/llamadas";
    }

    @GetMapping("/listar")
    public String listarEvaluaciones(Model model, Principal principal) {
        String username = principal.getName();
        Empleado empleado = iEmpleadoService.findByUsername(username);
        List<Evaluacion> evaluaciones = iEvaluacionService.obtenerEvaluacionesPorEmpleado(empleado);
        model.addAttribute("evaluaciones", evaluaciones);
        return "backoffice/evaluacion/evaluaciones";
    }

    @PostMapping("/firmar")
    public String firmarEvaluacion(@RequestParam("numeroOrden") int numeroOrden) {
        Evaluacion evaluacion = iEvaluacionService.obtenerEvaluacionPorNumeroOrden(numeroOrden);
        evaluacion.setEstadoFirma(true);
        iEvaluacionService.save(evaluacion);
        return "redirect:/evaluacion/listar";
    }
    @GetMapping("/{id}/detalles")
    @ResponseBody
    public Map<String, Object> getEvaluacionDetailsPage(@PathVariable("id") Long idEvaluacion, @RequestParam("numeroOrden") int numeroOrden) {
        Map<String, Object> datos = new HashMap<>();
        Evaluacion evaluacion = iEvaluacionService.findEvaluacionAndLlamadaByIdAndNumeroOrden(idEvaluacion, numeroOrden);
        if (evaluacion != null) {
            //Datos de la Llamada
            datos.put("numeroOrden", evaluacion.getLlamada().getNumeroOrden());
            datos.put("area", evaluacion.getLlamada().getArea());
            datos.put("tipo", evaluacion.getLlamada().getTipo());
            datos.put("subarea", evaluacion.getLlamada().getSubarea());
        }
        return datos;
    }

    @GetMapping("/prueba")
    @ResponseBody
    @JsonIgnore
    public List<Evaluacion> evaluaciones(){
        return iEvaluacionService.listaEvaluacionNotification("Ventas");
    }

}
