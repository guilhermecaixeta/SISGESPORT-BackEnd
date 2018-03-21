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

import com.ifg.sistema.sisgesport.api.entities.commom_entities.EntidadeComum;
import com.ifg.sistema.sisgesport.api.utils.GeradorCodigoUtils;

@Entity
@Table(name="evento")
public class Evento extends EntidadeComum implements Serializable{

	private static final long serialVersionUID = 1281537055341966013L;

	@Column(name="qnt_equipe", nullable= false)
	private int qntEquipes;
	
	@Column(name="codigo_evento" ,nullable = false, length=15, unique=true)
	private String codigoEvento;
	
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
	private Date dataInicioInscricao;
	
	@Column(name="data_fim_inscricao" ,nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFimInscricao;

	@Column(name="data_criacao" ,nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Column(name="data_inicio" ,nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	
	@Column(name="data_fim" ,nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;
	
	@ManyToOne
	@JoinColumn(name="servidor", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_servidor_evento"))
	private Servidor criador;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="evento")
	private List<Informacao_Evento> informacaoEvento= new ArrayList<Informacao_Evento>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="evento")
	private List<Evento_Modalidade> eventoModalidade = new ArrayList<Evento_Modalidade>();
	
	public Evento() {}

	public int getQntEquipes() {
		return qntEquipes;
	}


	public void setQntEquipes(int qntEquipes) {
		this.qntEquipes = qntEquipes;
	}


	public String getCodigoEvento() {
		return codigoEvento;
	}


	public void setCodigoEvento(String codigoEvento) {
		this.codigoEvento = codigoEvento;
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


	public Date getDataInicioInscricao() {
		return dataInicioInscricao;
	}


	public void setDataInicioInscricao(Date dataInicioInscricao) {
		this.dataInicioInscricao = dataInicioInscricao;
	}


	public Date getDataFimInscricao() {
		return dataFimInscricao;
	}


	public void setDataFimInscricao(Date dataFimInscricao) {
		this.dataFimInscricao = dataFimInscricao;
	}


	public Date getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public Date getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}


	public Date getDataFim() {
		return dataFim;
	}


	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}


	public Servidor getCriador() {
		return criador;
	}


	public void setCriador(Servidor criador) {
		this.criador = criador;
	}


	public List<Informacao_Evento> getInformacaoEvento() {
		return informacaoEvento;
	}


	public void setInformacaoEvento(List<Informacao_Evento> informacaoEvento) {
		this.informacaoEvento = informacaoEvento;
	}


	public List<Evento_Modalidade> getEventoModalidade() {
		return eventoModalidade;
	}


	public void setEventoModalidade(List<Evento_Modalidade> eventoModalidade) {
		this.eventoModalidade = eventoModalidade;
	}


	@PrePersist
	public void PrePersist() {
		codigoEvento = GeradorCodigoUtils.GerarCodigoUnicoEvento();
		dataCriacao = new Date();
	}
}
