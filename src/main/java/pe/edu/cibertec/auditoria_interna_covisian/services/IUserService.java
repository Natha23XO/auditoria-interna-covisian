package pe.edu.cibertec.auditoria_interna_covisian.services;


import pe.edu.cibertec.auditoria_interna_covisian.models.bd.User;
import pe.edu.cibertec.auditoria_interna_covisian.models.dto.UserDto;
import pe.edu.cibertec.auditoria_interna_covisian.models.dto.UserEmpleado;
import pe.edu.cibertec.auditoria_interna_covisian.models.dto.UserLider;
import java.util.List;

public interface IUserService {
	public  List<User> listarUsers();
	int ultimoId();
	User getUserById(Long id);
    void saveUser(User user);
    void deleteUser(Long id);
	void cambiarContraseña(String username, String password);
	void saveUserAndEmpleado(UserEmpleado userEmpleado);
	void saveUserAndAuditor(UserDto userDto);
	void saveUserAndLider(UserLider userLider);
}
