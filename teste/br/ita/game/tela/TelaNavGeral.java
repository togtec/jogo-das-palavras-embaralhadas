/* A classe TelaNavGeral estende a classe TelaNav apenas para fins de teste. Como a classe TelaNav � abstrata, 
 * s� pode ser testada atrav�s de uma de suas subclasses concretas */

package br.ita.game.tela;

public class TelaNavGeral extends TelaNav {
	//m�todo construtor
	public TelaNavGeral() {
		super("TelaNavGeral");
	}
	
	//m�todo de implementa��o obrigat�ria (m�todo abstrato da superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
}
