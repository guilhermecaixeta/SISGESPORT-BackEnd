package com.ifg.sistema.sisgesport.api.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UsuarioRetornoDTO {
	private String token;
	private Collection<? extends GrantedAuthority> authorities;

	public UsuarioRetornoDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}
