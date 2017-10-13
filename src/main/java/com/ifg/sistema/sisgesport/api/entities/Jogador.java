package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@JoinColumn(name="jogador", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo jogador não pode ser nulo.")
	private Aluno jogador;
	
	@ManyToOne
	@JoinColumn(name="time", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo time não pode ser nulo.")
	private Time time;
	
	@ManyToOne
	@JoinColumn(name="posicao", referencedColumnName="id")
	private Posicao posicao;
	
	@OneToMany(mappedBy="jogador", cascade= CascadeType.ALL , orphanRemoval = true, fetch= FetchType.LAZY)
	private List<Ponto_Partida> pontos_partida = new ArrayList<>();

	public Jogador() {	}
	
	public void AdicionarPonto_Partida(Ponto_Partida obj) {
	obj.setJogador(this);
	this.pontos_partida.add(obj);
	}
	
	public void RemoverPonto_Partida(int id) {
		this.pontos_partida.remove(id);
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

	public List<Ponto_Partida> getPontos_partida() {
		return pontos_partida;
	}

	public void setPontos_partida(List<Ponto_Partida> pontos_partida) {
		this.pontos_partida = pontos_partida;
	}
	
}
