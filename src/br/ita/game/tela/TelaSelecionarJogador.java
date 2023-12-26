/* TelaSelecionarJogador permite selecionar um jogador já existente */

package br.ita.game.tela;

import br.ita.game.AdmJogadorFile;
import br.ita.game.Jogador;

public class TelaSelecionarJogador extends TelaNav {
	
	//construtor
	public TelaSelecionarJogador() {
		super("Selecionar Jogador(a)");
		addMenu("r", "ranking");
	}
	
	@Override
	//método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		if (cmd.equalsIgnoreCase("r")) {
			new TelaExibirRanking(null).exibirTela(); //null -> jogador a ser identificado no ranking
		} else {		
			//verifica se cmd pode ser convertido em um número inteiro
			int comando = 0;
			try {
				comando = Integer.parseInt(cmd);
			} catch (NumberFormatException ex) {
				comando = -1;
			}
		
			//se comando for um número inteiro maior que 0
			if (comando > 0) {
				try {
					Jogador jogador = AdmJogadorFile.getJogador(comando);
					if (jogador != null) {
						new TelaSelecionarNivelExperiencia(jogador).exibirTela();
					}
				} catch (Exception ex) {
					limpaConteudo();
					addConteudo("Erro: " + ex.getMessage());
				}
			}
		}
	}
	
	@Override
	//método sobrescrito para carregar lista de jogadores à cada exibição da tela
	//motivo: jogadores podem ser modificados nas telas seguintes
	protected void impConteudo() {
		carregarListaJogadores();
		super.impConteudo();
	}
	
	private void carregarListaJogadores() {
		limpaConteudo();
		String[] listaJogadores;
		
		try {
			listaJogadores = AdmJogadorFile.getListaJogadores();
			if (listaJogadores.length == 0) {
				addConteudo("Nenhum(a) jogador(a) encontrado(a)!");
				addConteudo("Favor retornar e criar um(a) novo(a) jogador(a)!");
			} else {
				addConteudo("Por favor digite o id do(a) Jogador(a) que deseja escolher:");
				addConteudo("");
				for (String jogador : listaJogadores) {
					addConteudo(jogador);
				}
			}
		} catch (Exception ex) {
			addConteudo("Erro: " + ex.getMessage());
		}
	}		
}
