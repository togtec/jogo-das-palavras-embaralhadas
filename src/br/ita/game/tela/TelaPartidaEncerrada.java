package br.ita.game.tela;

import br.ita.game.Jogador;
import br.ita.game.mec.MecanicaDoJogo;

public class TelaPartidaEncerrada extends TelaPartida {
	
	//construtor
	public TelaPartidaEncerrada(Jogador jogador, MecanicaDoJogo mec) {
		super("Partida Encerrada", jogador, mec);
		addMenu("r", "ranking");
		setConteudo(mec.getRelProg());
	}
	
	@Override
	//m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		if (cmd.equalsIgnoreCase("r")) {
			new TelaExibirRanking(jogador).exibirTela();
		}
	}
}
