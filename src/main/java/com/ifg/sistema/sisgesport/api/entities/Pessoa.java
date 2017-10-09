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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private long id;
	
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
	
	@Column(name="senha" ,nullable = false, length=20)
	@NotNull(message="O campo senha não pode ser nulo.")
	@NotBlank(message="O campo senha não pode ser em branco.")
	@Length(max= 20,message="O senha possui o limite máximo de {max} caracteres.")
	private String senha;
	
	@Column(name="data_nasc" ,nullable = false)
	private Calendar data_nasc;
	
	@ManyToOne
	@JoinColumn(name="endereco", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo endereco não pode ser nulo.")
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name="imagem", referencedColumnName="id")
	private Imagem imagem;
	
	public Pessoa() {}
	
	
}
