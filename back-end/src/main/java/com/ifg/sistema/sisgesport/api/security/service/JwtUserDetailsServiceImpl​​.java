package com.ifg.sistema.sisgesport.api.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.security.JwtUserFactory;
import com.ifg.sistema.sisgesport.api.services.AlunoService;
import com.ifg.sistema.sisgesport.api.services.ServidorService;

@Service
public class JwtUserDetailsServiceImpl​​ implements UserDetailsService {

	@Autowired
	private AlunoService aS;
	@Autowired
	private ServidorService sS;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Aluno> aluno;
		Optional<Servidor> servidor;
		if (this.aS.BuscarPorMatricula(username).isPresent()) {
			aluno = this.aS.BuscarPorMatricula(username);
			return JwtUserFactory.create(aluno.get());
		} else {
			servidor = this.sS.BuscarPorMatriculaSiap(username);
	if(servidor.isPresent())return JwtUserFactory.create(servidor.get());
			throw new UsernameNotFoundException("Matricula não encontrada.");
		}
	}

}
