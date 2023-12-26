/* TelaDefinirJogador � a primeira tela do jogo. Atrav�s dela � poss�vel:
  	1- criar um novo jogador
  	2- selecionar um jogador j� existente
  	3- encerrar o aplicativo */

package br.ita.game.tela;

public class TelaDefinirJogador extends TelaNav {
	//m�todo construtor
	public TelaDefinirJogador() {
		super("Definir Jogador(a)");
		limpaBarraMenu();
		addConteudo("1- Criar Jogador(a)");
		addConteudo("2- Selecionar Jogador(a) Existente");
		addConteudo("3- Sair");
		//substitui tecla de retorno "v" por uma combina��o desconhecida,
		//desta forma, para sair, usu�rio ser� obrigado a utilizar op��o 3
		setReturnKey("ksjdkejdtjsd1"); 
	}
	
	@Override
	//m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		if (cmd.equalsIgnoreCase("1")) {
			new TelaCriarJogador().exibirTela();
			
		} else if (cmd.equalsIgnoreCase("2")) {
			new TelaSelecionarJogador().exibirTela();
			
		} else if (cmd.equalsIgnoreCase("3")) {
			setVisible(false); //tela n�o ser� exibida no retorno
			new TelaCreditos().exibirTela();
			
		}
	} //fim do m�todo processaCmd
}
