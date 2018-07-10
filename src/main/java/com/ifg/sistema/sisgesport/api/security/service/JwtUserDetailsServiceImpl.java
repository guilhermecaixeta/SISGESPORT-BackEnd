package com.ifg.sistema.sisgesport.api.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Pessoa;
import com.ifg.sistema.sisgesport.api.security.JwtUserFactory;
import com.ifg.sistema.sisgesport.api.services.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService uS;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Pessoa> usuario;

		usuario = this.uS.BuscarPorMatricula(username);
		if (usuario.isPresent()) {
			return JwtUserFactory.create(usuario.get());
		}
		throw new UsernameNotFoundException("Matricula não encontrada.");
	}
}
