package br.ita.game.tela;

import br.ita.game.Jogador;
import br.ita.game.emb.FabricaEmbaralhador;
import br.ita.game.enu.NivelExperiencia;

public class TelaSelecionarNivelExperiencia extends TelaPartida {
	
	//m�todo construtor
	public TelaSelecionarNivelExperiencia(Jogador jogador) {
		super("Selecionar N�vel de Experi�ncia do(a) Jogador(a)", jogador, null); //null = mec�nica
		
		//se jogador for null 
		if (jogador == null) {
			//tela n�o deve ser exibida
			setVisible(false);
		} else {
			//configura barra de menu
			addMenu("a", "ajuda");
			addMenu("e", "editar jogador");
			addMenu("x", "excluir jogador");
			//configura conte�do
			addConteudo("Por favor selecione o n�vel de experi�ncia do(a) Jogador(a):");
			addConteudo("");
			addConteudo("1- N�vel Iniciante");
			addConteudo("2- N�vel Intermedi�rio");
			addConteudo("3- N�vel Avan�ado");
		}
	}
	
	@Override
	//m�todo de implementa��o obrigat�ria (abstrato na superclasse)
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
			//retorna  1 se jogador foi exclu�do
			//retorna -1 se jogador n�o foi exclu�do
			int result = tela.excluirJogador();
			
			//se jogador foi exclu�do
			if (result == 1) {
				//esta tela n�o deve mais ser exibida
				setVisible(false);
			}
		}
	}
	
	private void selNivExp(NivelExperiencia nivelExp) {
		new TelaSelecionarGrauDificuldade(jogador, FabricaEmbaralhador.getEmbaralhador(nivelExp)).exibirTela();
	}
}
