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
	//método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		if (cmd.equalsIgnoreCase("r")) {
			new TelaExibirRanking(jogador).exibirTela();
		}
	}
}
