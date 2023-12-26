/* A classe TelaPartidaGeral estende a classe TelaPartida apenas para fins de teste. Como a classe TelaPartida é abstrata, 
 * só pode ser testada através de uma de suas subclasses concretas */

package br.ita.game.tela;

import br.ita.game.Jogador;
import br.ita.game.mec.MecanicaDoJogo;

public class TelaPartidaGeral extends TelaPartida {
	
	//método construtor
	public TelaPartidaGeral(Jogador jogador, MecanicaDoJogo mec) {
		super("TelaPartidaGeral", jogador, mec);
	}
	
	//método de implementação obrigatória (método abstrato da superclasse)
	protected void processaCmd(String cmd) {
		return;
	}	
}
