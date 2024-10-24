package pe.edu.cibertec.auditoria_interna_covisian.controllers.backoffice;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Evaluacion;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Llamada;
import pe.edu.cibertec.auditoria_interna_covisian.services.IEvaluacionService;
import pe.edu.cibertec.auditoria_interna_covisian.services.ILlamadasService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
@RequestMapping("/llamada")
public class LlamadaController {


    UserDetailsService userDetailsService;
    private ILlamadasService iLlamadasService;
    private IEvaluacionService iEvaluacionService;

    @GetMapping("/listar")
    public String auditarGet(Model model, Principal principal) {
        return "backoffice/llamada/llamadas";
    }

    @PostMapping("/listar")
    public String auditarPost(Model model, @RequestParam(name = "dniEmpleado", required = false) Integer dniEmpleado) {
        if (dniEmpleado != null) {
            List<Llamada> llamadas = iLlamadasService.listarLlamadasPorDni(dniEmpleado);
            model.addAttribute("llamadas", llamadas);
        }
        return "backoffice/llamada/llamadas";
    }

    @GetMapping("/detail/{id}")
    public String detailLibro(@PathVariable Integer id, Model model) {
        Llamada llamada = iLlamadasService.llamadaPorOrden(id);
        model.addAttribute("llamada", llamada);
        Evaluacion evaluacion = iEvaluacionService.evalaucionPorOrden(llamada.getNumeroOrden());
        model.addAttribute("evaluacion", evaluacion);
        return "backoffice/llamada/llamada";
    }

    @GetMapping("/report/{nroOrden}")
    public ResponseEntity<ByteArrayResource> reportLLamada(@PathVariable("nroOrden") Integer nroOrden, String token) throws IOException, JRException {
        return iLlamadasService.reporteLlamada(nroOrden);
    }

    @PostMapping("/edit")
    public String editEvaluacion(@RequestParam("numeroOrden") Integer numeroOrden,
                                 @RequestParam("observacionesEvaluacion") String observaciones,
                                 @RequestParam("nota") int nota) {
        Evaluacion evaluacionPorOrden = iEvaluacionService.evalaucionPorOrden(numeroOrden);
        evaluacionPorOrden.setObservacionesEvaluacion(observaciones);
        evaluacionPorOrden.setNota(nota);
        iEvaluacionService.save(evaluacionPorOrden);

        return "redirect:/llamada/detail/" + numeroOrden;
    }
}
