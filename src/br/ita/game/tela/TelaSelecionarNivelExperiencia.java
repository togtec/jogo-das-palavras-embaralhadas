package br.ita.game.tela;

import br.ita.game.Jogador;
import br.ita.game.emb.FabricaEmbaralhador;
import br.ita.game.enu.NivelExperiencia;

public class TelaSelecionarNivelExperiencia extends TelaPartida {
	
	//método construtor
	public TelaSelecionarNivelExperiencia(Jogador jogador) {
		super("Selecionar Nível de Experiência do(a) Jogador(a)", jogador, null); //null = mecânica
		
		//se jogador for null 
		if (jogador == null) {
			//tela não deve ser exibida
			setVisible(false);
		} else {
			//configura barra de menu
			addMenu("a", "ajuda");
			addMenu("e", "editar jogador");
			addMenu("x", "excluir jogador");
			//configura conteúdo
			addConteudo("Por favor selecione o nível de experiência do(a) Jogador(a):");
			addConteudo("");
			addConteudo("1- Nível Iniciante");
			addConteudo("2- Nível Intermediário");
			addConteudo("3- Nível Avançado");
		}
	}
	
	@Override
	//método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		if (cmd.equalsIgnoreCase("1")) {
			selNivExp(NivelExperiencia.INICIANTE);
			
		} else if (cmd.equalsIgnoreCase("2")) {
			selNivExp(NivelExperiencia.INTERMEDIARIO);
			
		} else if (cmd.equalsIgnoreCase("3")) {
			selNivExp(NivelExperiencia.AVANCADO);
			
		} else if (cmd.equalsIgnoreCase("a")) {
			new TelaAjudaSelecionarNivelExperiencia().exibirTela();
			
		} else if (cmd.equalsIgnoreCase("e")) {
			new TelaEditarJogador(jogador).exibirTela();
			
		} else if (cmd.equalsIgnoreCase("x")) {
			TelaExcluirJogador tela = new TelaExcluirJogador(jogador);
			//retorna  1 se jogador foi excluído
			//retorna -1 se jogador não foi excluído
			int result = tela.excluirJogador();
			
			//se jogador foi excluído
			if (result == 1) {
				//esta tela não deve mais ser exibida
				setVisible(false);
			}
		}
	}
	
	private void selNivExp(NivelExperiencia nivelExp) {
		new TelaSelecionarGrauDificuldade(jogador, FabricaEmbaralhador.getEmbaralhador(nivelExp)).exibirTela();
	}
}
