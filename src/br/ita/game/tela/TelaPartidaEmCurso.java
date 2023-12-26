package br.ita.game.tela;

import br.ita.game.Jogador;
import br.ita.game.mec.MecanicaDoJogo;

public class TelaPartidaEmCurso extends TelaPartida {

	//construtor
	public TelaPartidaEmCurso(Jogador jogador, MecanicaDoJogo mec) {
		super("Partida em Curso", jogador, mec);
		limpaBarraMenu();
		addMenu("v", "voltar (interrompe a partida e descarta os pontos obtidos)");
	}
	
	@Override //método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
	
	@Override
	//sobrescrito para interagir com a mecânica
	public void exibirTela() {
		mec.iniciar();
		while (mec.emCurso()) {
			setBarraStatus(mec.getBarraStatus()); //carrega barra de status
			setConteudo(mec.getConteudo());       //carrega conteúdo
			
			String comando = leEntrada();         //obs: enquanto aguarda entrada do usuário, partida pode ser encerrada por time-out
			
			if (mec.emCurso()) { //partida não foi encerrada por time-out
				if (comando.equalsIgnoreCase( getReturnKey())) { //usuário abortou a partida
					mec.concluir(); //para encerrar o cronometro
					return; //para não exibir relatório de progresso e não registrar o sccore
				} else {
					mec.processaPalpite(comando);
				}
			}
		} //fim do while
		encerrar();
	} //fim do método exibirTela
	
	private void encerrar() {
		try {
			jogador.incScore(mec.getPartidaStatus(), mec.getPlacar());
			new TelaPartidaEncerrada(jogador, mec).exibirTela();
		} catch (Exception ex) {
			addConteudo("Erro: " + ex.getMessage());
		}
	}
}
