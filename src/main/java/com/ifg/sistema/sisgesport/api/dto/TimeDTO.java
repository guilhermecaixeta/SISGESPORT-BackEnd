package com.ifg.sistema.sisgesport.api.dto;

public class TimeDTO {
	private Long id;
		private int numVitoria;
	private int numDerrota;
	private int numEmpate;
	private int pontuacao;
	private ModalidadeDTO modalidade;

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

	public ModalidadeDTO getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeDTO modalidade) {
		this.modalidade = modalidade;
	}
	
}
