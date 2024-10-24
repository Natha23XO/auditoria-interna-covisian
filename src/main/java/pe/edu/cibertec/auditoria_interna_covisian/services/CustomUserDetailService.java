package pe.edu.cibertec.auditoria_interna_covisian.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Auditor;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Empleado;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.Lider;
import pe.edu.cibertec.auditoria_interna_covisian.models.bd.User;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.AuditorRepository;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.EmpleadoRepository;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.LiderRepository;
import pe.edu.cibertec.auditoria_interna_covisian.repositories.UserRepository;

//CAMBIAR
@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

	private final UserRepository userRepository;
	private final AuditorRepository auditorRepository;
	private final EmpleadoRepository empleadoRepository;
	private final LiderRepository liderRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		Long codigo = user.getId();
		Auditor auditor = auditorRepository.findByIdUser(codigo);
		Empleado empleado = empleadoRepository.findByIdUser(codigo);
		Lider lider = liderRepository.findByIdUser(codigo);

        return new CustomUserDatail(user,auditor,empleado,lider);

	}

}
