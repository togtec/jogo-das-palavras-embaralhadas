/* A classe TelaNavCursos estende a classe TelaNav apenas para fins de teste. Como a classe TelaNav é abstrata, 
 * só pode ser testada através de uma de suas subclasses concretas. 
 * 
 * Nesse teste a classe TelaNavCursos foi instanciada e exibida por TelaNavHome. Ou seja, ao fechar TelaNavCursos,
 * o usuário deve retornar para TelaNavHome */

package br.ita.game.tela;

public class TelaNavCursos extends TelaNav{
	//método construtor
	public TelaNavCursos() {
		super("Cursos");
		addConteudo("Informática");
		addConteudo("Inglês");
		addConteudo("Administração");
	}
	
	//método de implementação obrigatória (método abstrato da superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
}
