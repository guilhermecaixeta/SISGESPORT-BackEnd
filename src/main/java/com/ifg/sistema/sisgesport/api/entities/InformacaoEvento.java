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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "informacao_evento")
public class InformacaoEvento implements Serializable {

	private static final long serialVersionUID = 6469404273216225968L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "tipo_informacao", nullable = false)
	@Check(constraints = "check_tipo_informacao CHECK (tipo_informacao IN('P', 'S'))")
	private char tipoInformacao;

	@Column(name = "", nullable = false, length = 255)
	@Length(max = 255, message = "O campo descricao possui o limite máximo de {max} caracteres.")
	private String titulo;

	@Column(name = "", nullable = false, length = 8000)
	@Length(max = 8000, message = "O campo descricao possui o limite máximo de {max} caracteres.")
	private String descricao;

	@Column(name = "data_postagem")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPostagem;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "informacao_evento_imagem", joinColumns = @JoinColumn(name = "informacao_evento", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "imagem", referencedColumnName = "id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "informacao_evento", "imagem" }) })
	private List<Imagem> imagem = new ArrayList<Imagem>();

	@ManyToOne
	@JoinColumn(name = "evento", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_evento_informacao_evento"))
	@NotNull(message = "O campo evento não pode ser nulo.")
	private Evento evento;

	public InformacaoEvento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public char getTipoInformacao() {
		return tipoInformacao;
	}

	public void setTipoInformacao(char tipoInformacao) {
		this.tipoInformacao = tipoInformacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public List<Imagem> getImagem() {
		return imagem;
	}

	public void setImagem(List<Imagem> imagem) {
		this.imagem = imagem;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@PrePersist
	public void PrePersist() {
		dataPostagem = new Date();
	}
}
