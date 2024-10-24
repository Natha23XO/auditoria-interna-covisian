package pe.edu.cibertec.auditoria_interna_covisian.restclient.errorhandler;


import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomErrorDecoder implements ErrorDecoder {
    //Personalizar mensajes de errores de nuestra aplicacion
    @Override
    public Exception decode(String s, Response response) {
        HttpStatus httpStatus = HttpStatus.valueOf(response.status());
        switch (httpStatus){
            case NOT_FOUND: return new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no encontrado");
            case BAD_REQUEST: return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solicitud incorrecta al API");
            default: return new Exception("Error no identificado"+httpStatus);
        }
    }
}
