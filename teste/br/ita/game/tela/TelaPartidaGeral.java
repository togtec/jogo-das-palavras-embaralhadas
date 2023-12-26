/* A classe TelaPartidaGeral estende a classe TelaPartida apenas para fins de teste. Como a classe TelaPartida � abstrata, 
 * s� pode ser testada atrav�s de uma de suas subclasses concretas */

package br.ita.game.tela;

import br.ita.game.Jogador;
import br.ita.game.mec.MecanicaDoJogo;

public class TelaPartidaGeral extends TelaPartida {
	
	//m�todo construtor
	public TelaPartidaGeral(Jogador jogador, MecanicaDoJogo mec) {
		super("TelaPartidaGeral", jogador, mec);
	}
	
	//m�todo de implementa��o obrigat�ria (m�todo abstrato da superclasse)
	protected void processaCmd(String cmd) {
		return;
	}	
}
