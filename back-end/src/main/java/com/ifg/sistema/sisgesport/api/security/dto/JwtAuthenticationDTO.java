package com.ifg.sistema.sisgesport.api.security.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class JwtAuthenticationDTO {
	@NotEmpty(message="A matricula não pode ser vazia.")
	private String matricula;
	@NotEmpty(message="A senha não pode ser vazia.")
	private String senha;

	public JwtAuthenticationDTO() {
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "JWTAuthenticationDTO [matricula="+matricula+", senha="+senha+"];";
	}
}
