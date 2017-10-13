package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable ;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="servidor")
public class Servidor extends Pessoa implements Serializable{
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Integer id;
	
	@Column(name="matricula_siap", nullable=false, unique= true, length=20)
	private String matriculasiap;
	
	@Column(name="admin_sistema", nullable=false)
	@NotNull(message="O campo não pode ser nulo.")
	private Boolean admin_sistema;
	
	@ManyToOne
	@NotNull(message="O campo cargo não pode ser nulo.")
	@JoinColumn(name="cargo", referencedColumnName="id", nullable=false)
	private Cargo cargo;

	@OneToMany(mappedBy="criador", cascade= CascadeType.ALL , orphanRemoval = true, fetch= FetchType.LAZY)
	private List<Evento> eventos = new ArrayList<>();
	
	public Servidor() {}
	
	public void AdicionarEvento(Evento obj) {
	obj.setCriador(this);
	this.eventos.add(obj);
	}

	public void RemoverEvento(int id) {
		this.eventos.remove(id);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula_siap() {
		return matriculasiap;
	}

	public void setMatricula_siap(String matriculasiap) {
		this.matriculasiap = matriculasiap;
	}

	public Boolean getAdmin_sistema() {
		return admin_sistema;
	}

	public void setAdmin_sistema(Boolean admin_sistema) {
		this.admin_sistema = admin_sistema;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}
