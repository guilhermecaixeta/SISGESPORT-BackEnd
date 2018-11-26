package com.ifg.sistema.sisgesport.api.dto.dto_retorno;

import com.ifg.sistema.sisgesport.api.dto.PosicaoDTO;

public class JogadorRetornoDTO {

    private Long id;
    private int numCamisa;
    private AlunoRetornoDTO jogador;
    private PosicaoDTO posicao;
    private TimeRetornoDTO time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumCamisa() {
        return numCamisa;
    }

    public void setNumCamisa(int numCamisa) {
        this.numCamisa = numCamisa;
    }

    public AlunoRetornoDTO getJogador() {
        return jogador;
    }

    public void setJogador(AlunoRetornoDTO jogador) {
        this.jogador = jogador;
    }

    public PosicaoDTO getPosicao() {
        return posicao;
    }

    public void setPosicao(PosicaoDTO posicao) {
        this.posicao = posicao;
    }

    public TimeRetornoDTO getTime() {
        return time;
    }

    public void setTime(TimeRetornoDTO time) {
        this.time = time;
    }
}
