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
	
	@Override //m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
	
	@Override
	//sobrescrito para interagir com a mec�nica
	public void exibirTela() {
		mec.iniciar();
		while (mec.emCurso()) {
			setBarraStatus(mec.getBarraStatus()); //carrega barra de status
			setConteudo(mec.getConteudo());       //carrega conte�do
			
			String comando = leEntrada();         //obs: enquanto aguarda entrada do usu�rio, partida pode ser encerrada por time-out
			
			if (mec.emCurso()) { //partida n�o foi encerrada por time-out
				if (comando.equalsIgnoreCase( getReturnKey())) { //usu�rio abortou a partida
					mec.concluir(); //para encerrar o cronometro
					return; //para n�o exibir relat�rio de progresso e n�o registrar o sccore
				} else {
					mec.processaPalpite(comando);
				}
			}
		} //fim do while
		encerrar();
	} //fim do m�todo exibirTela
	
	private void encerrar() {
		try {
			jogador.incScore(mec.getPartidaStatus(), mec.getPlacar());
			new TelaPartidaEncerrada(jogador, mec).exibirTela();
		} catch (Exception ex) {
			addConteudo("Erro: " + ex.getMessage());
		}
	}
}
