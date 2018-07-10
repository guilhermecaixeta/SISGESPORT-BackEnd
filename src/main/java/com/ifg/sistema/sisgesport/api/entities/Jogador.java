package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="jogador")
public class Jogador implements Serializable {
	
	private static final long serialVersionUID = -3863616590006001944L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="num_camisa")
	private int numCamisa;

	@ManyToOne
	@JoinColumn(name="jogador", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_aluno_jogador"))
	@NotNull(message="O campo jogador não pode ser nulo.")
	private Aluno jogador;
	
	@ManyToOne
	@JoinColumn(name="time", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_time_jogador"))
	@NotNull(message="O campo time não pode ser nulo.")
	private Time time;
	
	@ManyToOne
	@JoinColumn(name="posicao", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_posicao_jogador"))
	private Posicao posicao;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="jogador")
	private List<PartidaPenalidade> partidaPenalidade = new ArrayList<PartidaPenalidade>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="jogador")
	private List<PartidaPonto> partidaPonto = new ArrayList<PartidaPonto>();

	public Jogador() {	}

	public void AdicionarPartidaPenalidade(PartidaPenalidade obj) {
	obj.setJogador(this);
	this.partidaPenalidade.add(obj);
	}

	public void RemoverPartidaPenalidade(int id) {
		this.partidaPenalidade.remove(id);
	}
	
	public void AdicionarPartidaPonto(PartidaPonto obj) {
	obj.setJogador(this);
	this.partidaPonto.add(obj);
	}

	public void RemoverPartidaPonto(int id) {
		this.partidaPonto.remove(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumCamisa() {
		return numCamisa;
	}

	public void setNumCamisa(int numCamisa) {
		this.numCamisa = numCamisa;
	}

	public Aluno getJogador() {
		return jogador;
	}

	public void setJogador(Aluno jogador) {
		this.jogador = jogador;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	public List<PartidaPenalidade> getPartidaPenalidade() {
		return partidaPenalidade;
	}

	public void setPartidaPenalidade(List<PartidaPenalidade> partidaPenalidade) {
		this.partidaPenalidade = partidaPenalidade;
	}

	public List<PartidaPonto> getPartidaPonto() {
		return partidaPonto;
	}

	public void setPartidaPonto(List<PartidaPonto> partidaPonto) {
		this.partidaPonto = partidaPonto;
	}
	
}
