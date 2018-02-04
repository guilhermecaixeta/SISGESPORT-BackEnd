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

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Integer id;
	
	@Column(name="num_camisa")
	private int num_camisa;

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
	private List<Partida_Penalidade> partida_penalidade = new ArrayList<Partida_Penalidade>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="jogador")
	private List<Partida_Ponto> partida_ponto = new ArrayList<Partida_Ponto>();

	public Jogador() {	}

	public void AdicionarPartidaPenalidade(Partida_Penalidade obj) {
	obj.setJogador(this);
	this.partida_penalidade.add(obj);
	}

	public void RemoverPartidaPenalidade(int id) {
		this.partida_penalidade.remove(id);
	}
	
	public void AdicionarPartidaPonto(Partida_Ponto obj) {
	obj.setJogador(this);
	this.partida_ponto.add(obj);
	}

	public void RemoverPartidaPonto(int id) {
		this.partida_ponto.remove(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNum_camisa() {
		return num_camisa;
	}

	public void setNum_camisa(int num_camisa) {
		this.num_camisa = num_camisa;
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

	public List<Partida_Penalidade> getPartida_penalidade() {
		return partida_penalidade;
	}

	public void setPartida_penalidade(List<Partida_Penalidade> partida_penalidade) {
		this.partida_penalidade = partida_penalidade;
	}

	public List<Partida_Ponto> getPartida_ponto() {
		return partida_ponto;
	}

	public void setPartida_ponto(List<Partida_Ponto> partida_ponto) {
		this.partida_ponto = partida_ponto;
	}

}
