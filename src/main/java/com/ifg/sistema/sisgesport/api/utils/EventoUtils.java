package com.ifg.sistema.sisgesport.api.utils;

import com.ifg.sistema.sisgesport.api.entities.*;
import com.ifg.sistema.sisgesport.api.response.Response;
import java.util.*;
import static com.ifg.sistema.sisgesport.api.utils.GeradorCodigoUtils.GerarCodigoUnicoEquipe;

/**
 *
 */
public class EventoUtils {

    private final int valorParaCor1 = 0x1095730;
    private final int valorParaCor2 = 0x8996980;
    /**
     *
     * @param quantidadeEquipe
     * @param evento
     */
    public List<Equipe> GerarEquipes(int quantidadeEquipe, Evento evento) {
        Random rand = new Random();
        List<Equipe> lista = new ArrayList<Equipe>();
        String nomeEquipe = "";
        String cor = "";
        while (quantidadeEquipe > 0) {
            Equipe equipe = new Equipe();
            cor = Integer.toHexString(rand.nextInt(valorParaCor1) * valorParaCor2).substring(0,6);
            nomeEquipe = randomIdentifier();
            if(!lista.isEmpty() ){
                while(ExisteNome(lista, nomeEquipe)){
                    nomeEquipe = randomIdentifier();
                }
                while(ExisteCor(lista, cor)){
                    cor = Integer.toHexString(rand.nextInt(valorParaCor1) * valorParaCor2).substring(0,6);
                }
            }
            equipe.setCor("#"+cor);
            equipe.setEvento(evento);
            equipe.setNome(nomeEquipe);
            equipe.setCodigoEquipe(GerarCodigoUnicoEquipe());
            GerarTimes(evento).forEach(t ->  equipe.AdicionarTime(t));
            lista.add(equipe);
            quantidadeEquipe--;
        }

        return lista;
    }

    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    final java.util.Random rand = new java.util.Random();

    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final Set<String> identifiers = new HashSet<>();

    /**
     *
     * @return
     */
    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        builder.append("Equipe ");
            for (int i = 0; i < 3; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        return builder.toString();
    }

    /**
     *
     * @param lista
     * @param nome
     * @return
     */
    public boolean ExisteNome(final List<Equipe> lista, final String nome){
        return lista.stream().filter(e -> e.getNome().equals(nome)).findAny().isPresent();
    }

    public boolean ExisteCor(final List<Equipe> lista, final String cor){
        return lista.stream().filter(e -> e.getCor().equals(cor)).findAny().isPresent();
    }

    public List<Time> GerarTimes(Evento evento){
        List<Time> lista = new ArrayList<Time>();
        int length = evento.getEventoModalidade().size();
        for (EventoModalidade m : evento.getEventoModalidade()) {
            Time time = new Time();
            time.setEventoModalidade(m);
            lista.add(time);
        }
        return lista;
    }

    public Response ValidarJogadorTime(Time time, Aluno jogador, Response response){
        int size = time.getEquipe().getEvento().getEventoModalidade().size();
        Calendar calJogador = Calendar.getInstance();
        Calendar calDataAtual = Calendar.getInstance();
        calDataAtual.setTime(new Date());
        calJogador.setTime(jogador.getDataNascimento());
        int idade = calDataAtual.compareTo(calJogador);
        if(time.getEventoModalidade().getSexo() != jogador.getSexo()){
            response.getErrors().add("Sexo do jogador é diferente do permitido!");
        }
        if(idade > time.getEventoModalidade().getIdadeMaximaPermitida()){
            response.getErrors().add("Jogador possui idade acima da permitida!");
        }
        if(time.getJogador().size() > 0 ) {
            time.getJogador().forEach(t -> {
                if (t.getJogador().getId() == jogador.getId())
                    response.getErrors().add("Jogador já cadastrado nessa equipe!");
            });
        }
        return response;
    }
}