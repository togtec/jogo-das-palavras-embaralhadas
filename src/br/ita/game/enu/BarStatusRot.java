/* A enumera��o BarStatusRot define e padroniza os r�tulos utilizados na barra de status. A enumera��o
 * ser� utilizada nas classes: TelaPartida */

package br.ita.game.enu;

public enum BarStatusRot {
	PALPITES_UTILIZADOS("Palpites utilizados"),
	PALPITES_RESTANTES("Palpites restantes"),
	PONTOS("Pontos marcados"),
	TEMPO("Tempo restante");
	
	//vari�veis de inst�ncia
	private String key;
	
	//construtor
	private BarStatusRot(String key) {
		this.key = key;
	}
	
	public String toString() {
		return key;
	}
}
