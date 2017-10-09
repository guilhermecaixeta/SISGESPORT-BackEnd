package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="evento_modalidade")
public class Evento_Modalidade implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private long id;
	
	@ManyToOne
	@JoinColumn(name="evento", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo evento não pode ser nulo.")
	private Evento evento;
	
	@ManyToOne
	@JoinColumn(name="modalidade", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo modalidade não pode ser nulo.")
	private Modalidade modalidade;
	
	@Column(name="sexo", nullable= false, length=1)
	@NotNull(message="O campo sexo não pode ser nulo.")
	@NotBlank(message="O campo sexo não pode ser em branco.")
	private char sexo;

	public Evento_Modalidade() {	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
}
