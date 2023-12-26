package br.ita.game.tela;

import br.ita.game.AdmJogadorFile;
import br.ita.game.Jogador;

public class TelaExcluirJogador extends TelaModificarJogador {
	
	//construtor
	public TelaExcluirJogador(Jogador jogador) {
		super("Excluir Jogador(a)", jogador);
		addMenu("c", "confirmar exclus�o");
		addConteudo("Tem certeza que deseja excluir " + jogador.getNome() + "?");
	}
	
	//retorna  1 se jogador foi exclu�do
	//retorna -1 se jogador n�o foi exclu�do
	public int excluirJogador() {
		exibirTela();
		
		//retorna o resultado pois a tela anterior, que fez a chamada desse m�todo,
		//n�o deve ser exibida caso o jogador seja realmente exclu�do
		if (jogador == null) {
			return 1;
		} else {
			return -1;
		}
	}
	
	@Override
	//m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		if (jogador != null) {
			if (cmd.equalsIgnoreCase("c") ) {
				try {
					AdmJogadorFile.excluir(jogador);					
					encerrar();					
					addConteudo("Jogador(a) exclu�do(a)!");					
				} catch (Exception ex) {
					encerrar();					
					addConteudo("Erro: " + ex.getMessage());
				}
			}
		}
	} //fim do m�todo processaCmd
	
	private void encerrar() {
		jogador = null; //impede que a barra de jogador seja impressa
		limpaBarraMenu();
		addMenu("v", "voltar");
		limpaConteudo();
	}
}
