package br.ita.game.tela;

import br.ita.game.Jogador;
import br.ita.game.mec.MecanicaDoJogo;

public class TelaIniciarPartida extends TelaPartida {

	//construtor
	public TelaIniciarPartida(Jogador jogador, MecanicaDoJogo mec) {
		super("Iniciar Partida", jogador, mec);
		this.jogador = jogador;
		this.mec = mec;
		
		addConteudo("1- Iniciar Partida");
		addConteudo("2- Configura��es");
	}
	
	@Override
	//m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		if (cmd.equalsIgnoreCase("1")) {
			new TelaPartidaEmCurso(jogador, mec).exibirTela();
			
		} else if (cmd.equalsIgnoreCase("2")) {
			new TelaConfiguracoes().exibirTela();
		}
	}
}
