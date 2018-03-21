package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable ;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import com.ifg.sistema.sisgesport.api.entities.commom_entities.EntidadeComum;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public class Pessoa extends EntidadeComum implements Serializable{

	private static final long serialVersionUID = 2848996214116693556L;

	@Column(name="nome" ,nullable = false, length=50)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 50,message="O campo possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="sexo" ,nullable = false, length=1)
	@NotNull(message="O campo sexo não pode ser nulo.")
	private Character sexo;
	
	@Column(name="login" ,nullable = false, length=15, unique=true)
	@NotNull(message="O campo login não pode ser nulo.")
	@NotBlank(message="O campo login não pode ser em branco.")
	@Length(max= 15,message="O login possui o limite máximo de {max} caracteres.")
	private String login;
	
	@Column(name="senha" ,nullable = false, length=60)
	@NotNull(message="O campo senha não pode ser nulo.")
	@NotBlank(message="O campo senha não pode ser em branco.")
	@Length(max= 60,message="A senha possui o limite máximo de {max} caracteres.")
	private String senha;
	
	@Column(name="data_nasc" ,nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message="O campo data de nascimento não pode ser nulo.")
	private Date dataNascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="perfil" ,nullable = false)
	private PerfilSistema perfil;
	
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
