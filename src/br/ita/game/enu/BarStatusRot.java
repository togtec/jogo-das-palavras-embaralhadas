/* A enumeração BarStatusRot define e padroniza os rótulos utilizados na barra de status. A enumeração
 * será utilizada nas classes: TelaPartida */

package br.ita.game.enu;

public enum BarStatusRot {
	PALPITES_UTILIZADOS("Palpites utilizados"),
	PALPITES_RESTANTES("Palpites restantes"),
	PONTOS("Pontos marcados"),
	TEMPO("Tempo restante");
	
	//variáveis de instância
	private String key;
	
	//construtor
	private BarStatusRot(String key) {
		this.key = key;
	}
	
	public String toString() {
		return key;
	}
}
