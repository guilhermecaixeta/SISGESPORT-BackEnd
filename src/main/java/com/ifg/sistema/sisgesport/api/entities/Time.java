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
@Table(name="time")
public class Time implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="num_vitoria")
	private int num_vitoria;
	
	@Column(name="num_derrota")
	private int num_derrota;
	
	@Column(name="num_empate")
	private int num_empate;
	
	@Column(name="pontuacao")
	private int pontuacao;
	
	@ManyToOne
	@JoinColumn(name="equipe", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo equipe não pode ser nulo.")
	private Equipe equipe;
	
	@ManyToOne
	@JoinColumn(name="modalidade", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo modalidade não pode ser nulo.")
	private Modalidade modalidade;
	
	@OneToMany(mappedBy="time", cascade= CascadeType.ALL , orphanRemoval = true, fetch= FetchType.LAZY)
	private List<Jogador> jogadores= new ArrayList<>();

	public Time() {	}
	
	public void AdicionarJogador(Jogador obj) {
	obj.setTime(this);
	this.jogadores.add(obj);
	}
	
	public void RemoverJogador(int id) {
		this.jogadores.remove(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNum_vitoria() {
		return num_vitoria;
	}

	public void setNum_vitoria(int num_vitoria) {
		this.num_vitoria = num_vitoria;
	}

	public int getNum_derrota() {
		return num_derrota;
	}

	public void setNum_derrota(int num_derrota) {
		this.num_derrota = num_derrota;
	}

	public int getNum_empate() {
		return num_empate;
	}

	public void setNum_empate(int num_empate) {
		this.num_empate = num_empate;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
}
