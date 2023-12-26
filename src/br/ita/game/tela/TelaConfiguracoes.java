package br.ita.game.tela;

public class TelaConfiguracoes extends TelaNav {
	private static int qtdPalavrasPorPartida = 5;
	private static int tempoMaxEmMinutos = 5;
	
	//construtor
	public TelaConfiguracoes() {
		super("Configurações");
		addConteudo("Quantidade de palavras por partida: " + qtdPalavrasPorPartida);
		addConteudo("Tempo máximo em minutos: " + tempoMaxEmMinutos);
	}
	
	@Override
	//método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
	
	//método de captura
	public static int getQtdPalavrasPorPartida() {
		return qtdPalavrasPorPartida;
	}
	
	public static int getTempoMaxEmMinutos() {
		return tempoMaxEmMinutos;
	}
}
