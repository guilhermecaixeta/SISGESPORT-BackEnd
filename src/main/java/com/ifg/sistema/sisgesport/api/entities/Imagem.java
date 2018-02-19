package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table ;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Entidade_Comum;

@Entity
@Table(name="imagem")
public class Imagem implements Serializable {
	
	private static final long serialVersionUID = -1633920788416730387L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Integer id;
	
	@NotNull(message="O campo imagem deve ser preenchido.")
	@Column(name="imagem", nullable=false)
	@Lob
	private byte[] imagem;
	
	@Column(name="descricao_imagem", length=120)
	@Length(max=120, message="O campo descricao possui o limite máximo de {max} caracteres.")
	private String descricao_imagem;
	
	@Column(name="nome", length=120)
	@Length(max=120, message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="tamanho")
	@Min(value= 0, message="O campo não aceita valores negativos.")
	private Double tamanho;
	
	@Column(name="data_imagem")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_imagem;

	@ManyToOne
	@JoinColumn(name="entidade_comum", referencedColumnName="id", nullable=true, foreignKey = @ForeignKey(name="fk_entidade_comum_imagem"))
	private Entidade_Comum entidade_comum;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="informacao_evento_imagem", 
	joinColumns=
	@JoinColumn(name="imagem", referencedColumnName="id"),
	inverseJoinColumns =
	@JoinColumn(name="informacao_evento", referencedColumnName="id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"imagem", "informacao_evento"})})
	private List<Informacao_Evento> informacaoEvento = new ArrayList<Informacao_Evento>();
	
	public Imagem() {	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getDescricao_imagem() {
		return descricao_imagem;
	}

	public void setDescricao_imagem(String descricao_imagem) {
		this.descricao_imagem = descricao_imagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getTamanho() {
		return tamanho;
	}

	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}

	public Date getData_imagem() {
		return data_imagem;
	}

	public void setData_imagem(Date data_imagem) {
		this.data_imagem = data_imagem;
	}

	public Entidade_Comum getEntidade_comum() {
		return entidade_comum;
	}

	public void setEntidade_comum(Entidade_Comum entidade_comum) {
		this.entidade_comum = entidade_comum;
	}
	
	public List<Informacao_Evento> getInformacao_evento() {
		return informacaoEvento;
	}

	public void setInformacao_evento(List<Informacao_Evento> informacaoEvento) {
		this.informacaoEvento = informacaoEvento;
	}

	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}

	@PrePersist
	public void PrePersist() {
		data_imagem = new Date();
	}
}
