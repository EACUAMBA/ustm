package com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.console;

import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Jogador;
import com.eacuamba.dev.iii_semestre.programacao_ii.trabalho_pratico.domain.models.Pessoa;
import com.eacuamba.dev.utils.ConsoleBase;

public class JogadorConsole extends ConsoleBase {
    public static Jogador criarJogador(){
        System.out.println("Formulário de criação de jogador");
        String nome = getTextoFromConsole("Nome");

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);

        Jogador jogador = new Jogador();
        jogador.setPessoa(pessoa);

        return jogador;
    }

    public void jogar(){

    }
}
