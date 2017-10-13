package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable ;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Integer id;
	
	@Column(name="nome" ,nullable = false, length=50)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 50,message="O campo possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="sexo" ,nullable = false, length=1)
	@NotNull(message="O campo sexo não pode ser nulo.")
	private Character sexo;
	
	@Column(name="login" ,nullable = false, length=25, unique=true)
	@NotNull(message="O campo login não pode ser nulo.")
	@NotBlank(message="O campo login não pode ser em branco.")
	@Length(max= 25,message="O login possui o limite máximo de {max} caracteres.")
	private String login;
	
	@Column(name="senha" ,nullable = false, length=60)
	@NotNull(message="O campo senha não pode ser nulo.")
	@NotBlank(message="O campo senha não pode ser em branco.")
	@Length(max= 60,message="A senha possui o limite máximo de {max} caracteres.")
	private String senha;
	
	@Column(name="data_nasc" ,nullable = false)
	private Calendar data_nasc;
	
	@ManyToOne
	@JoinColumn(name="endereco", referencedColumnName="id")
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name="imagem", referencedColumnName="id")
	private Imagem imagem;
	
	public Pessoa() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Calendar getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Calendar data_nasc) {
		this.data_nasc = data_nasc;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	
	
}
