package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Entidade_Comum;
import com.ifg.sistema.sisgesport.api.utils.GeradorCodigoUtils;

@Entity
@Table(name="evento")
public class Evento extends Entidade_Comum implements Serializable{

	private static final long serialVersionUID = 1281537055341966013L;

	@Column(name="qnt_equipe", nullable= false)
	private int qnt_equipes;
	
	@Column(name="codigo_evento" ,nullable = false, length=15, unique=true)
	private String codigo_evento;
	
	@Column(name="nome" ,nullable = false, length=45)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 45,message="O campo possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="descricao" ,nullable = false, length=400)
	@NotNull(message="O campo descricao não pode ser nulo.")
	@NotBlank(message="O campo descricao não pode ser em branco.")
	@Length(max= 400,message="O descricao possui o limite máximo de {max} caracteres.")
	private String descricao;

	@Column(name="data_inicio_inscricao" ,nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_inicio_inscricao;
	
	@Column(name="data_fim_inscricao" ,nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_fim_inscricao;

	@Column(name="data_criacao" ,nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_criacao;
	
	@Column(name="data_inicio" ,nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_inicio;
	
	@Column(name="data_fim" ,nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_fim;
	
	@ManyToOne
	@JoinColumn(name="servidor", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_servidor_evento"))
	private Servidor criador;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="evento")
	private List<Informacao_Evento> informacao_evento= new ArrayList<Informacao_Evento>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="evento")
	private List<Evento_Modalidade> evento_modalidade = new ArrayList<Evento_Modalidade>();
	
	public Evento() {}

	public int getQnt_equipes() {
		return qnt_equipes;
	}

	public void setQnt_equipes(int qnt_equipes) {
		this.qnt_equipes = qnt_equipes;
	}

	public String getCodigo_evento() {
		return codigo_evento;
	}

	public void setCodigo_evento(String codigo_evento) {
		this.codigo_evento = codigo_evento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_inicio_inscricao() {
		return data_inicio_inscricao;
	}

	public void setData_inicio_inscricao(Date data_inicio_inscricao) {
		this.data_inicio_inscricao = data_inicio_inscricao;
	}

	public Date getData_fim_inscricao() {
		return data_fim_inscricao;
	}

	public void setData_fim_inscricao(Date data_fim_inscricao) {
		this.data_fim_inscricao = data_fim_inscricao;
	}

	public Date getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}

	public Servidor getCriador() {
		return criador;
	}

	public void setCriador(Servidor criador) {
		this.criador = criador;
	}

	public List<Informacao_Evento> getInformacao_evento() {
		return informacao_evento;
	}

	public void setInformacao_evento(List<Informacao_Evento> informacao_evento) {
		this.informacao_evento = informacao_evento;
	}

	public List<Evento_Modalidade> getEvento_modalidade() {
		return evento_modalidade;
	}

	public void setEvento_modalidade(List<Evento_Modalidade> evento_modalidade) {
		this.evento_modalidade = evento_modalidade;
	}

	@PrePersist
	public void PrePersist() {
		codigo_evento = GeradorCodigoUtils.GerarCodigoUnicoEvento();
		data_criacao = new Date();
	}
}
