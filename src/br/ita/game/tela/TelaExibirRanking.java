package br.ita.game.tela;

import br.ita.game.AdmJogadorFile;
import br.ita.game.Jogador;

public class TelaExibirRanking extends TelaNav {
	private Jogador jogador; //jogador a ser identificado no ranking (obs: esse valor pode ser null)
	
	//construtor
	public TelaExibirRanking(Jogador jogador) {
		super("Ranking");
		this.jogador = jogador;
	}
	
	@Override
	//método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
	
	@Override
	//método sobrescrito para carregar ranking de jogadores à cada exibição da tela
	protected void impConteudo() {
		carregarRankingJogadores();
		super.impConteudo();
	}	
	
	private void carregarRankingJogadores() {
		limpaConteudo();
		String[] rankingJogadores;
		
		try {
			rankingJogadores = AdmJogadorFile.getRankingJogadores(jogador);
			for (String jogador : rankingJogadores) {
				addConteudo(jogador);
			}
		} catch (Exception ex) {
			addConteudo("Erro: " + ex.getMessage());
		}
	}
}
