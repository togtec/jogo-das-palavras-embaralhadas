/* A classe TelaNavGeral estende a classe TelaNav apenas para fins de teste. Como a classe TelaNav é abstrata, 
 * só pode ser testada através de uma de suas subclasses concretas */

package br.ita.game.tela;

public class TelaNavGeral extends TelaNav {
	//método construtor
	public TelaNavGeral() {
		super("TelaNavGeral");
	}
	
	//método de implementação obrigatória (método abstrato da superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
}
