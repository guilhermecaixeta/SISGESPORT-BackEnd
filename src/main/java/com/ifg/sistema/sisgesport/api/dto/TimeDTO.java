package com.ifg.sistema.sisgesport.api.dto;

import com.ifg.sistema.sisgesport.api.dto.dto_retorno.EquipeRetornoDTO;
import com.ifg.sistema.sisgesport.api.dto.dto_retorno.JogadorRetornoDTO;

import java.util.ArrayList;
import java.util.List;

public class TimeDTO {
	private Long id;
	private int numVitoria;
	private int numDerrota;
	private int numEmpate;
	private int pontuacao;
	private EquipeRetornoDTO equipe;
	private EventoModalidadeDTO eventoModalidade;
	private List<JogadorRetornoDTO> jogador = new ArrayList<JogadorRetornoDTO>();

	public TimeDTO() {
	}

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

    public List<JogadorRetornoDTO> getJogador() {
        return jogador;
    }

    public void setJogador(List<JogadorRetornoDTO> jogador) {
        this.jogador = jogador;
    }

    public EquipeRetornoDTO getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeRetornoDTO equipe) {
        this.equipe = equipe;
    }

    public EventoModalidadeDTO getEventoModalidade() {
        return eventoModalidade;
    }

    public void setEventoModalidade(EventoModalidadeDTO eventoModalidade) {
        this.eventoModalidade = eventoModalidade;
    }
}