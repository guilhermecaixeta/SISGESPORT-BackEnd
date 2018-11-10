package com.ifg.sistema.sisgesport.api.dto;

import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.entities.Modalidade;

public class EventoModalidadeDTO {
	private Long id;
	private ModalidadeDTO modalidade;
	private char sexo;
	private int idadeMaximaPermitida;

	public EventoModalidadeDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ModalidadeDTO getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeDTO modalidade) {
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
