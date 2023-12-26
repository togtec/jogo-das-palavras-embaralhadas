/* A enumera��o BarPartRot define e padroniza os r�tulos utilizados na barra de partida. A enumera��o
 * ser� utilizada nas classes: TelaPartida e TelaSelecionarGrauDificuldade */

package br.ita.game.enu;

public enum BarPartRot {
	JOGADOR("Jogador(a)"),
	NIVEL_EXPERIENCIA("N�vel"),
	TIPO_EMBARALHADOR("Tipo Embaralhador"),
	MULTIPLICADOR("Multiplicador"),
	DIFICULDADE("Dificuldade");
	
	//vari�veis de inst�ncia
	private String key;
	
	//construtor
	private BarPartRot(String key) {
		this.key = key;
	}
	
	public String toString() {
		return key;
	}
}
