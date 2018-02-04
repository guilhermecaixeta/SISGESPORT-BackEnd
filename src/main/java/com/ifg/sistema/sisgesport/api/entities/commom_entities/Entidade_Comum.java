package com.ifg.sistema.sisgesport.api.entities.commom_entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ifg.sistema.sisgesport.api.entities.Endereco;
import com.ifg.sistema.sisgesport.api.entities.Imagem;

@Entity
@Table(name="entidade_comum")
@Inheritance(strategy = InheritanceType.JOINED)
public class Entidade_Comum {
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Integer id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="entidade_comum")
	private List<Endereco> endereco = new ArrayList<Endereco>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="entidade_comum")
	private List<Imagem> imagem = new ArrayList<Imagem>();
	
	public Entidade_Comum() {}

	public void AdicionarEndereco(Endereco obj) {
	obj.setObjeto_comum(this);
	this.endereco.add(obj);
	}

	public void RemoverEndereco(int id) {
		this.endereco.remove(id);
	}
	
	public void AdicionarImagem(Imagem obj) {
	obj.setEntidade_comum(this);
	this.imagem.add(obj);
	}

	public void RemoverImagem(int id) {
		this.imagem.remove(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public List<Imagem> getImagem() {
		return imagem;
	}

	public void setImagem(List<Imagem> imagem) {
		this.imagem = imagem;
	}
}
