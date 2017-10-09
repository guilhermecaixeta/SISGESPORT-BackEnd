package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table ;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="instituicao")
public class Instituicao implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="descricao", nullable=false, length=60)
	@NotNull(message="O campo descricao não pode ser nulo.")
	@NotBlank(message="O campo descricao não pode ser em branco.")
	@Length(max= 60,message="O campo descricao possui o limite máximo de {max} caracteres.")
	private String descricao;
	
	@Column(name="nome", nullable=false, length=20)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 20,message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;

	@NotNull(message="O campo endereco não pode ser nulo.")
	@ManyToOne
	@JoinColumn( name="endereco", referencedColumnName="id", nullable=false)
	private Endereco endereco;
	
	@OneToMany(mappedBy="instituicao", cascade= CascadeType.ALL , orphanRemoval = true, fetch= FetchType.LAZY)
	private List<Curso> cursos = new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="instituicao_cargo", 
	joinColumns=
	@JoinColumn(name="instituicao", referencedColumnName="id", nullable= false),
	inverseJoinColumns =
	@JoinColumn(name="cargo", referencedColumnName="id", nullable= false),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"instituicao", "cargo"})})
	private List<Cargo> cargos = new ArrayList<>();
	
	public Instituicao() {	}

	public void AdicionarCurso(Curso obj) {
	obj.setInstituicao(this);
	this.cursos.add(obj);
	}
	
	public void RemoverCurso(int id) {
		this.cursos.remove(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
}
