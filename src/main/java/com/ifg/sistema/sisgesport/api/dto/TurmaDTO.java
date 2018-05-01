package com.ifg.sistema.sisgesport.api.dto;

import java.util.Calendar;

import com.ifg.sistema.sisgesport.api.entities.Curso;

public class TurmaDTO {
	private Long id;
	private Boolean flgAtivo;
	private String nome;
	private Calendar dataLimite;
	private Calendar dataInicialTurma;
	private Curso curso;

	public TurmaDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(Boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Calendar dataLimite) {
		this.dataLimite = dataLimite;
	}

	public Calendar getDataInicialTurma() {
		return dataInicialTurma;
	}

	public void setDataInicialTurma(Calendar dataInicialTurma) {
		this.dataInicialTurma = dataInicialTurma;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
