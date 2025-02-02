package pe.edu.cibertec.auditoria_interna_covisian.controllers.backoffice;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Auditor;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Empleado;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Lider;
import pe.edu.cibertec.auditoria_interna_covisian.models.dto.UserDto;
import pe.edu.cibertec.auditoria_interna_covisian.models.dto.UserEmpleado;
import pe.edu.cibertec.auditoria_interna_covisian.models.dto.UserLider;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.AuditorRepository;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.EmpleadoRepository;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.LiderRepository;
import pe.edu.cibertec.auditoria_interna_covisian.services.IAuditorService;
import pe.edu.cibertec.auditoria_interna_covisian.services.IEmpleadoService;
import pe.edu.cibertec.auditoria_interna_covisian.services.ILiderService;
import pe.edu.cibertec.auditoria_interna_covisian.services.IUserService;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    UserDetailsService userDetailsService;
    private IUserService iUserService;
    private ILiderService iLiderService;
    private IEmpleadoService iEmpleadoService;
    private IAuditorService iAuditorService;
    private AuditorRepository auditorRepository;
    private EmpleadoRepository empleadoRepository;
    private LiderRepository liderRepository;

    @GetMapping("/registroAuditor")
    public String registroAuditorInicio(Model model) {
        model.addAttribute("userAndAuditorDto", new UserDto());
        return "backoffice/user/frmregistroauditor";
    }

    @PostMapping("/registroAuditor")
    public String registroAuditorPost(@ModelAttribute("userAndAuditorDto") UserDto userAndAuditorDto, Model model) {
        iUserService.saveUserAndAuditor(userAndAuditorDto);
        model.addAttribute("message", "Registro Correcto");
        return "backoffice/user/frmregistroauditor";
    }

    @GetMapping("/registroEmpleado")
    public String registroEmpleadoInicio(Model model) {
        model.addAttribute("userAndEmpleadoDto", new UserEmpleado());
        return "backoffice/user/frmregistroempleado";
    }

    @PostMapping("/registroEmpleado")
    public String registroEmpleadoPost(@ModelAttribute("userAndEmpleadoDto") UserEmpleado userAndEmpleadoDto, Model model) {
        iUserService.saveUserAndEmpleado(userAndEmpleadoDto);
        model.addAttribute("message", "Registro Correcto");
        return "backoffice/user/frmregistroempleado";
    }

    @GetMapping("/registroLider")
    public String registroLiderInicio(Model model) {
        model.addAttribute("userAndLiderDto", new UserLider());
        return "backoffice/user/frmregistrolider";
    }

    @PostMapping("/registroLider")
    public String registroLiderPost(@ModelAttribute("userAndLiderDto") UserLider userAndLiderDto, Model model) {
        iUserService.saveUserAndLider(userAndLiderDto);
        model.addAttribute("message", "Registro Correcto");
        return "backoffice/user/frmregistrolider";
    }

    @GetMapping("/detail")
    public String verUsuario(Model model, Principal principal) {
        //Ver si aca si usar UserDetails
        return "backoffice/user/user";
    }

    @PostMapping("/actualizar")
    @ResponseBody
    public String actualizarUsuario(@RequestParam("dni") int dni, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido) {
        Empleado empleado = iEmpleadoService.findByDni(dni);
        Auditor auditor = iAuditorService.findByDni(dni);
        Lider lider = iLiderService.findByDni(dni);

        if (empleado != null) {
            empleado.setNombreEmpleado(nombre);
            empleado.setApellidoEmpleado(apellido);
            empleadoRepository.save(empleado);
        } else if (auditor != null) {
            auditor.setNombreAuditor(nombre);
            auditor.setApellidoAuditor(apellido);
            auditorRepository.save(auditor);
        } else if (lider != null) {
            lider.setNombreLider(nombre);
            lider.setApellidoLider(apellido);
            liderRepository.save(lider);
        }
        return null;
    }

    @PostMapping("/cambiarContrasenia")
    @ResponseBody
    public String cambiarContraseña(@RequestParam("username") String username, @RequestParam("password") String password) {
        iUserService.cambiarContraseña(username, password);
        return null;
    }

    @GetMapping("/list")
    public String listaUsuarios(Model model) {
        model.addAttribute("usuarios", iUserService.listarUsers());
        return "backoffice/user/users";
    }

    @GetMapping("/prueba")
    @ResponseBody
    UserDetails nose(Model model, Principal principal) {
        return userDetailsService.loadUserByUsername(principal.getName());
    }

    @GetMapping("/export-xls/empleados")
    public ResponseEntity<ByteArrayResource> exportXlsEmpleados() throws IOException {
        return iEmpleadoService.export();
    }

    //METODOS PARA EXPOTAR USUARIOS EN FORMATO PDF - XLS (POR MOTIVOS DE NEGOCIO NO SE USAN - PERO GUARDO PARA REUTILIZARLO)
    /*
    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("petsReport", "usuariosReporte.pdf");
        return ResponseEntity.ok().headers(headers).body(iUserService.exportPdf());
    }

    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment").filename("usuariosReporte" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().headers(headers).body(iUserService.exportXls());
    }*/

}
