package br.ita.game.tela;

public class TelaAjudaSelecionarGrauDificuldade extends TelaNav {
	
	//construtor
	public TelaAjudaSelecionarGrauDificuldade() {
		super("Ajuda/Selecionar Grau de Dificuldade da Partida");
		addConteudo("O grau de dificuldade da partida define o \"Tipo da Mecânica\" que será utilizado:");
		addConteudo("");
		addConteudo("Grau Fácil - Tipo da Mecânica: palpites sem limite:");
		addConteudo("-Jogador(a) só avança para a próxima palavra mediante acerto");
		addConteudo("-a partida termina quando o(a) jogador(a) acerta todas as palavras ou estoura o tempo limite");
		addConteudo("-em caso de vitória — Jogador(a) acertar todas as palavras:");
		addConteudo(TelaNav.margemEsquerda + "-Jogador(a) ganha \"Bônus por vitória\": 5 pontos");
		addConteudo(TelaNav.margemEsquerda + "-Jogador(a) ganha \"Bônus por tempo restante\": 1 ponto à cada 10 segundos de tempo não utilizado");
		addConteudo("");
		addConteudo("Grau Normal - Tipo da Mecânica: palpites por palavra:");
		addConteudo("-Jogador(a) só avança para a próxima palavra mediante acerto ou quando acabarem os palpites da palavra em exibição");
		addConteudo("-em caso de acerto, os palpites não utilizados são convertidos em bônus");
		addConteudo("-a partida termina quando o(a) Jogador(a) acerta todas as palavras, estoura o tempo limite ou utiliza o último palpite da última palavra");
		addConteudo("-em caso de vitória — Jogador(a) acertar todas as palavras:");
		addConteudo(TelaNav.margemEsquerda + "-Jogador(a) ganha \"Bônus por vitória\": 20 pontos");
		addConteudo(TelaNav.margemEsquerda + "-Jogador(a) ganha \"Bônus por tempo restante\": 2 pontos à cada 10 segundos de tempo não utilizado");
		addConteudo("");
		addConteudo("Grau Difícil - Tipo da Mecânica: palpites por partida:");
		addConteudo("-Jogador(a) só avança para a próxima palavra mediante acerto");
		addConteudo("-em caso de acerto, os palpites não utilizados são convertidos em bônus");
		addConteudo("-a partida termina quando o(a) Jogador(a) acerta todas as palavras, estoura o tempo limite ou utiliza o último palpite da partida");
		addConteudo("-em caso de vitória — Jogador(a) acertar todas as palavras:");
		addConteudo(TelaNav.margemEsquerda +  "-Jogador(a) ganha \"Bônus por vitória\": 50 pontos");
		addConteudo(TelaNav.margemEsquerda +  "-Jogador(a) ganha \"Bônus por tempo restante\": 5 pontos à cada 10 segundos de tempo não utilizado");
	}
	
	//método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
}
