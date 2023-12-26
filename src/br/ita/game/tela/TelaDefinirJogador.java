/* TelaDefinirJogador é a primeira tela do jogo. Através dela é possível:
  	1- criar um novo jogador
  	2- selecionar um jogador já existente
  	3- encerrar o aplicativo */

package br.ita.game.tela;

public class TelaDefinirJogador extends TelaNav {
	//método construtor
	public TelaDefinirJogador() {
		super("Definir Jogador(a)");
		limpaBarraMenu();
		addConteudo("1- Criar Jogador(a)");
		addConteudo("2- Selecionar Jogador(a) Existente");
		addConteudo("3- Sair");
		//substitui tecla de retorno "v" por uma combinação desconhecida,
		//desta forma, para sair, usuário será obrigado a utilizar opção 3
		setReturnKey("ksjdkejdtjsd1"); 
	}
	
	@Override
	//método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		if (cmd.equalsIgnoreCase("1")) {
			new TelaCriarJogador().exibirTela();
			
		} else if (cmd.equalsIgnoreCase("2")) {
			new TelaSelecionarJogador().exibirTela();
			
		} else if (cmd.equalsIgnoreCase("3")) {
			setVisible(false); //tela não será exibida no retorno
			new TelaCreditos().exibirTela();
			
		}
	} //fim do método processaCmd
}
