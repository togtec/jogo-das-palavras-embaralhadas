package br.ita.game.tela;

public class TelaAjudaSelecionarNivelExperiencia extends TelaNav {

	//construtor
	public TelaAjudaSelecionarNivelExperiencia() {
		super("Ajuda/Selecionar N�vel de Experi�ncia do(a) Jogador(a)");
		addConteudo("O n�vel de experi�ncia do(a) Jogador(a) define o \"Tipo Embaralhador\" que ser� utilizado.");
		addConteudo("");
		addConteudo("No N�vel Iniciante apenas as s�labas s�o embaralhadas - Tipo Embaralhador: S�labas");
		addConteudo("No N�vel Intermedi�rio as letras s�o embaralhadas de forma padronizada - Tipo Embaralhador: Letras Padronizado");
		addConteudo("No N�vel Avan�ado as letras s�o embaralhadas de forma aleat�ria - Tipo Embaralhador: Letras Aleat�rio");
		addConteudo("");
		addConteudo("Al�m de definir a forma do embaralhamento, cada Tipo Embaralhador tamb�m possui um multiplicador que determina a");
		addConteudo("quantidade de pontos ganhos � cada acerto. Imagine, por exemplo, que o(a) Jogador(a) acerte uma palavra que vale 3 pontos:");
		addConteudo("");
		addConteudo("No N�vel Iniciante - Tipo Embaralhador S�labas - (Multiplicador 1) - o(a) Jogador(a) ganha 3 pontos: 3 x 1");
		addConteudo("No N�vel Intermedi�rio - Tipo Embaralhador Letras Padronizado - (Multiplicador 2) - o(a) Jogador(a) ganha 6 pontos: 3 x 2");
		addConteudo("No N�vel Avan�ado, Tipo Embaralhador Letras Aleat�rio - (Multiplicador 3) - o(a) Jogador(a) ganha 9 pontos: 3 x 3");
	}
	
	@Override
	//m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}	
}
