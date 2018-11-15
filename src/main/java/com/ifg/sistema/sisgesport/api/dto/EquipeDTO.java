package com.ifg.sistema.sisgesport.api.dto;
import java.util.ArrayList;
import java.util.List;

public class EquipeDTO {
	private Long id;
	private String nome;
	private String codigoEquipe;
	private String cor;
        private AlunoDTO capitao;
	private ImagemDTO imagem;
	private EventoDTO evento;
	private List<TimeDTO> time = new ArrayList<>();
	private List<AlunoDTO> aluno = new ArrayList<>();

	public EquipeDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoEquipe() {
		return codigoEquipe;
	}

	public void setCodigoEquipe(String codigoEquipe) {
		this.codigoEquipe = codigoEquipe;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public AlunoDTO getCapitao() {
		return capitao;
	}

	public void setCapitao(AlunoDTO capitao) {
		this.capitao = capitao;
	}

	public ImagemDTO getImagem() {
		return imagem;
	}

	public void setImagem(ImagemDTO imagem) {
		this.imagem = imagem;
	}

	public List<AlunoDTO> getAluno() {
		return aluno;
	}

	public void setAluno(List<AlunoDTO> aluno) {
		this.aluno = aluno;
	}

	public EventoDTO getEvento() {
		return evento;
	}

	public void setEvento(EventoDTO evento) {
		this.evento = evento;
	}

    public List<TimeDTO> getTime() {
        return time;
    }

    public void setTime(List<TimeDTO> time) {
        this.time = time;
    }
}
