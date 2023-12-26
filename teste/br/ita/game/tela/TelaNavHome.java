/* A classe TelaNavHome estende a classe TelaNav apenas para fins de teste. Como a classe TelaNav é abstrata, 
 * só pode ser testada através de uma de suas subclasses concretas.
 * 
 * Nesse teste a classe TelaNavHome instancia e exibe: 
 * 		TelaNavCursos
 * 		TelaNavSobreEmpresa
 *
 * Ao fechar TelaNavCursos ou TelaNavSobreEmpresa o usuário deverá retornar para TelaNavHome. */

package br.ita.game.tela;

public class TelaNavHome extends TelaNav{
	//método construtor
	public TelaNavHome() {
		super("Home");
		addConteudo("c- cursos");
		addConteudo("s- sobre a empresa");
	}
	
	//método de implementação obrigatória (método abstrato da superclasse)
	protected void processaCmd(String cmd) {
		if (cmd.equalsIgnoreCase("c")) {
			new TelaNavCursos().exibirTela();
		} else if (cmd.equalsIgnoreCase("s")) {
			new TelaNavSobreEmpresa().exibirTela();
		}
	}
}
