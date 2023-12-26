package br.ita.game.tela;

public class TelaAjudaSelecionarGrauDificuldade extends TelaNav {
	
	//construtor
	public TelaAjudaSelecionarGrauDificuldade() {
		super("Ajuda/Selecionar Grau de Dificuldade da Partida");
		addConteudo("O grau de dificuldade da partida define o \"Tipo da Mec�nica\" que ser� utilizado:");
		addConteudo("");
		addConteudo("Grau F�cil - Tipo da Mec�nica: palpites sem limite:");
		addConteudo("-Jogador(a) s� avan�a para a pr�xima palavra mediante acerto");
		addConteudo("-a partida termina quando o(a) jogador(a) acerta todas as palavras ou estoura o tempo limite");
		addConteudo("-em caso de vit�ria � Jogador(a) acertar todas as palavras:");
		addConteudo(TelaNav.margemEsquerda + "-Jogador(a) ganha \"B�nus por vit�ria\": 5 pontos");
		addConteudo(TelaNav.margemEsquerda + "-Jogador(a) ganha \"B�nus por tempo restante\": 1 ponto � cada 10 segundos de tempo n�o utilizado");
		addConteudo("");
		addConteudo("Grau Normal - Tipo da Mec�nica: palpites por palavra:");
		addConteudo("-Jogador(a) s� avan�a para a pr�xima palavra mediante acerto ou quando acabarem os palpites da palavra em exibi��o");
		addConteudo("-em caso de acerto, os palpites n�o utilizados s�o convertidos em b�nus");
		addConteudo("-a partida termina quando o(a) Jogador(a) acerta todas as palavras, estoura o tempo limite ou utiliza o �ltimo palpite da �ltima palavra");
		addConteudo("-em caso de vit�ria � Jogador(a) acertar todas as palavras:");
		addConteudo(TelaNav.margemEsquerda + "-Jogador(a) ganha \"B�nus por vit�ria\": 20 pontos");
		addConteudo(TelaNav.margemEsquerda + "-Jogador(a) ganha \"B�nus por tempo restante\": 2 pontos � cada 10 segundos de tempo n�o utilizado");
		addConteudo("");
		addConteudo("Grau Dif�cil - Tipo da Mec�nica: palpites por partida:");
		addConteudo("-Jogador(a) s� avan�a para a pr�xima palavra mediante acerto");
		addConteudo("-em caso de acerto, os palpites n�o utilizados s�o convertidos em b�nus");
		addConteudo("-a partida termina quando o(a) Jogador(a) acerta todas as palavras, estoura o tempo limite ou utiliza o �ltimo palpite da partida");
		addConteudo("-em caso de vit�ria � Jogador(a) acertar todas as palavras:");
		addConteudo(TelaNav.margemEsquerda +  "-Jogador(a) ganha \"B�nus por vit�ria\": 50 pontos");
		addConteudo(TelaNav.margemEsquerda +  "-Jogador(a) ganha \"B�nus por tempo restante\": 5 pontos � cada 10 segundos de tempo n�o utilizado");
	}
	
	//m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
}
