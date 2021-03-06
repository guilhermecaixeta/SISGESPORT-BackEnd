package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable ;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Pessoa;

@Entity
@Table(name="servidor")
public class Servidor extends Pessoa implements Serializable{
	
	private static final long serialVersionUID = -4064616397424015889L;
		
	@ManyToOne
	@JoinColumn(name="cargo", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_cargo_servidor"))
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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}
