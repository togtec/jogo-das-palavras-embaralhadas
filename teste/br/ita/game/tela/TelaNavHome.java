/* A classe TelaNavHome estende a classe TelaNav apenas para fins de teste. Como a classe TelaNav � abstrata, 
 * s� pode ser testada atrav�s de uma de suas subclasses concretas.
 * 
 * Nesse teste a classe TelaNavHome instancia e exibe: 
 * 		TelaNavCursos
 * 		TelaNavSobreEmpresa
 *
 * Ao fechar TelaNavCursos ou TelaNavSobreEmpresa o usu�rio dever� retornar para TelaNavHome. */

package br.ita.game.tela;

public class TelaNavHome extends TelaNav{
	//m�todo construtor
	public TelaNavHome() {
		super("Home");
		addConteudo("c- cursos");
		addConteudo("s- sobre a empresa");
	}
	
	//m�todo de implementa��o obrigat�ria (m�todo abstrato da superclasse)
	protected void processaCmd(String cmd) {
		if (cmd.equalsIgnoreCase("c")) {
			new TelaNavCursos().exibirTela();
		} else if (cmd.equalsIgnoreCase("s")) {
			new TelaNavSobreEmpresa().exibirTela();
		}
	}
}
