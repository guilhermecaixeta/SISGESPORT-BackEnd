package com.ifg.sistema.sisgesport.api.dto.commom_entities;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;

public class PessoaDTO extends EntidadeComumDTO {
	@NotEmpty(message="O campo nome não pode ser vazio.")
	protected String nome;
	protected Character sexo;
	@NotEmpty(message="O campo senha não pode ser vazio.")
	@Length(min= 5, max= 60, message="O campo senha deve conter entre 5 e até 60 caracteres.")
	protected String senha;
	@NotEmpty(message="O campo senha não pode ser vazio.")
	@Length(max= 60, message="O campo email deve conter até 60 caracteres.")
	@Email(message="Email inválido.")
	protected String email;
	protected Date dataNascimento;
	protected PerfilSistema perfil;

	public PessoaDTO() {
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
