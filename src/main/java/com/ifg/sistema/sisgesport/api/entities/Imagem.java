package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id;
import javax.persistence.Table ;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="imagem")
public class Imagem implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="imagem", nullable=false)
	private Byte[] imagem;
	
	@Column(name="descricao_imagem", length=60)
	@Length(max=60, message="O campo descricao possui o limite máximo de {max} caracteres.")
	private String descricao_imagem;
	
	@Column(name="data_imagem")
	private Calendar data_imagem;


	public Imagem() {	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Byte[] getImagem() {
		return imagem;
	}


	public void setImagem(Byte[] imagem) {
		this.imagem = imagem;
	}


	public String getDescricao_imagem() {
		return descricao_imagem;
	}


	public void setDescricao_imagem(String descricao_imagem) {
		this.descricao_imagem = descricao_imagem;
	}


	public Calendar getData_imagem() {
		return data_imagem;
	}


	public void setData_imagem(Calendar data_imagem) {
		this.data_imagem = data_imagem;
	}
	
}
