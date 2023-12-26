/* A enumeração BarPartRot define e padroniza os rótulos utilizados na barra de partida. A enumeração
 * será utilizada nas classes: TelaPartida e TelaSelecionarGrauDificuldade */

package br.ita.game.enu;

public enum BarPartRot {
	JOGADOR("Jogador(a)"),
	NIVEL_EXPERIENCIA("Nível"),
	TIPO_EMBARALHADOR("Tipo Embaralhador"),
	MULTIPLICADOR("Multiplicador"),
	DIFICULDADE("Dificuldade");
	
	//variáveis de instância
	private String key;
	
	//construtor
	private BarPartRot(String key) {
		this.key = key;
	}
	
	public String toString() {
		return key;
	}
}
