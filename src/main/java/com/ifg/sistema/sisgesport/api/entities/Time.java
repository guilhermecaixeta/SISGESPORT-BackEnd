package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="time")
public class Time implements Serializable {
	
	private static final long serialVersionUID = 3923786373890196396L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="num_vitoria")
	private int numVitoria;
	
	@Column(name="num_derrota")
	private int numDerrota;
	
	@Column(name="num_empate")
	private int numEmpate;
	
	@Column(name="pontuacao")
	private int pontuacao;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="time")
	private List<Jogador> jogador = new ArrayList<Jogador>();
	
	@ManyToOne
	@JoinColumn(name="equipe", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_equipe_time"))
	@NotNull(message="O campo equipe não pode ser nulo.")
	private Equipe equipe;
	
	@ManyToOne
	@JoinColumn(name="evento_modalidade", referencedColumnName="id", nullable=false,
            foreignKey = @ForeignKey(name="fk_time_evento_modalidade"))
	@NotNull(message="O campo evento modalidade não pode ser nulo.")
	private EventoModalidade eventoModalidade;
	
	public Time() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumVitoria() {
		return numVitoria;
	}

	public void setNumVitoria(int numVitoria) {
		this.numVitoria = numVitoria;
	}

	public int getNumDerrota() {
		return numDerrota;
	}

	public void setNumDerrota(int numDerrota) {
		this.numDerrota = numDerrota;
	}

	public int getNumEmpate() {
		return numEmpate;
	}

	public void setNumEmpate(int numEmpate) {
		this.numEmpate = numEmpate;
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

    public List<Jogador> getJogador() {
        return jogador;
    }

    public void setJogador(List<Jogador> jogador) {
        this.jogador = jogador;
    }

	public EventoModalidade getEventoModalidade() {
		return eventoModalidade;
	}

	public void setEventoModalidade(EventoModalidade eventoModalidade) {
		this.eventoModalidade = eventoModalidade;
	}
}
