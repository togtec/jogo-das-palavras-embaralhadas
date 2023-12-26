/* A classe TelaNavCursos estende a classe TelaNav apenas para fins de teste. Como a classe TelaNav � abstrata, 
 * s� pode ser testada atrav�s de uma de suas subclasses concretas. 
 * 
 * Nesse teste a classe TelaNavCursos foi instanciada e exibida por TelaNavHome. Ou seja, ao fechar TelaNavCursos,
 * o usu�rio deve retornar para TelaNavHome */

package br.ita.game.tela;

public class TelaNavCursos extends TelaNav{
	//m�todo construtor
	public TelaNavCursos() {
		super("Cursos");
		addConteudo("Inform�tica");
		addConteudo("Ingl�s");
		addConteudo("Administra��o");
	}
	
	//m�todo de implementa��o obrigat�ria (m�todo abstrato da superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
}
