package pe.edu.cibertec.auditoria_interna_covisian.controllers.backoffice;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Memorandum;
import pe.edu.cibertec.auditoria_interna_covisian.services.IFeedbackService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final IFeedbackService feedbackService;

    @GetMapping("/{dni}")
    public String feedbacksPorDni(@PathVariable("dni") Integer dni, Model model){
        List<Memorandum> memorandums = feedbackService.findByEmpleado(dni);
        model.addAttribute("memorandums", memorandums);
        return "backoffice/feedback/feedbacks";
    }
    @GetMapping("/a/{area}")
    public String feedbacksPorArea(@PathVariable("area") String area, Model model){
        List<Memorandum> memorandums = feedbackService.findByArea(area);
        model.addAttribute("memorandums", memorandums);
        return "backoffice/feedback/feedbacks";
    }

    @GetMapping("/report/{idMemorandum}")
    public ResponseEntity<ByteArrayResource> reportFeedback(@PathVariable("idMemorandum") Integer idMemorandum) throws IOException, JRException {
        return feedbackService.reportFeedback(idMemorandum);
    }
}
