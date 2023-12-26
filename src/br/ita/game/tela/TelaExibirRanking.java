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
	//m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
	
	@Override
	//m�todo sobrescrito para carregar ranking de jogadores � cada exibi��o da tela
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
