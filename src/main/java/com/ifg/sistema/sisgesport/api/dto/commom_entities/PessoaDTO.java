package com.ifg.sistema.sisgesport.api.dto.commom_entities;

import java.util.Date;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;

public class PessoaDTO {
	
	protected String nome;
	protected Character sexo;
	protected String login;
	protected String senha;
	protected Date dataNascimento;
	protected PerfilSistema perfil;
	public PessoaDTO() {}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Character getSexo() {
		return sexo;
	}
	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public PerfilSistema getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilSistema perfil) {
		this.perfil = perfil;
	}
}
