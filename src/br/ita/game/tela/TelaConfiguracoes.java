package br.ita.game.tela;

public class TelaConfiguracoes extends TelaNav {
	private static int qtdPalavrasPorPartida = 5;
	private static int tempoMaxEmMinutos = 5;
	
	//construtor
	public TelaConfiguracoes() {
		super("Configura��es");
		addConteudo("Quantidade de palavras por partida: " + qtdPalavrasPorPartida);
		addConteudo("Tempo m�ximo em minutos: " + tempoMaxEmMinutos);
	}
	
	@Override
	//m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
	
	//m�todo de captura
	public static int getQtdPalavrasPorPartida() {
		return qtdPalavrasPorPartida;
	}
	
	public static int getTempoMaxEmMinutos() {
		return tempoMaxEmMinutos;
	}
}
