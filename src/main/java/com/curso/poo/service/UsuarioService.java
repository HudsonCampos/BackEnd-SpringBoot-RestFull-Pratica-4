package com.curso.poo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.curso.poo.config.DozerConverter;
import com.curso.poo.model.Usuario;
import com.curso.poo.model.vo.UsuarioVO;
import com.curso.poo.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public UsuarioVO created(UsuarioVO usuarioVO) {		
		Usuario usuar = DozerConverter.parseObjetct(usuarioVO, Usuario.class);
		
		Optional<Usuario> usuario = usuarioRepository.findByUsername(usuar.getUsername());			
		if(usuario.isEmpty() && usuar.getUsername() != "") {
			usuarioRepository.save(usuar);
		} else {			
			throw new ResponseStatusException(HttpStatus.OK, "Usuario ja existe ou invalido!");
		}
		
		var entity = DozerConverter.parseObjetct(usuarioVO, Usuario.class);		
		return DozerConverter.parseObjetct(entity, UsuarioVO.class);
				
		
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não localizado!"));
		
		return User.builder()
				.username(usuario.getUsername())
				.password(usuario.getSenha())
				.roles("USER")
				.build();
	}
	
	
}
