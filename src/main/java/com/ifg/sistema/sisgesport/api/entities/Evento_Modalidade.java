package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Check;

@Entity
@Table(name="evento_modalidade")
public class Evento_Modalidade implements Serializable{

	private static final long serialVersionUID = 4161788124589946685L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="evento", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_evento_evento_modalidade"))
	@NotNull(message="O campo evento não pode ser nulo.")
	private Evento evento;
	
	@ManyToOne
	@JoinColumn(name="modalidade", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_modalidade_evento_modalidade"))
	@NotNull(message="O campo modalidade não pode ser nulo.")
	private Modalidade modalidade;
	
	@Column(name="sexo", nullable= false, length=1)
	@Check(constraints="CONSTRAINT check_Sexo CHECK (sexo IN('F', 'M'))")
	private char sexo;
	
	@Column(name="idade_maxima")
	private int idadeMaximaPermitida;

	public Evento_Modalidade() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public int getIdadeMaximaPermitida() {
		return idadeMaximaPermitida;
	}

	public void setIdadeMaximaPermitida(int idadeMaximaPermitida) {
		this.idadeMaximaPermitida = idadeMaximaPermitida;
	}
	
}
