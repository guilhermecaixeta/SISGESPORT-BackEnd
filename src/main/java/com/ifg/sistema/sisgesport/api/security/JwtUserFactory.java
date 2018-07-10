package com.ifg.sistema.sisgesport.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Pessoa;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;

public class JwtUserFactory {
	
	private JwtUserFactory() {
	}

	/**
	 * Converte e gera um Aluno JWT a partir dos dados da pessoa
	 * 
	 * @param Aluno
	 * @return JwtUser
	 */
	public static JwtUser create(Pessoa pessoa) {
		return new JwtUser(pessoa.getId(), pessoa.getMatricula(), pessoa.getSenha(),
				mapToGrantedAuthorities(pessoa.getPerfil()));
	}

	/**
	 * Converte o formato de usuário usado pelo sistema pelo usado no spring
	 * security
	 * 
	 * @param perfil
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilSistema perfil) {
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(perfil.toString()));
		return auth;
	}
}