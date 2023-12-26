/* A classe TelaNavCursos estende a classe TelaNav apenas para fins de teste. Como a classe TelaNav é abstrata, 
 * só pode ser testada através de uma de suas subclasses concretas. 
 * 
 * Nesse teste a classe TelaNavSobreEmpresa foi instanciada e exibida por TelaNavHome. Ou seja, ao fechar TelaNavSobreEmpresa,
 * o usuário deve retornar para TelaNavHome */

package br.ita.game.tela;

public class TelaNavSobreEmpresa extends TelaNav{
	//método construtor
	public TelaNavSobreEmpresa() {
		super("Sobre a Empresa");
		addConteudo("Lorem Ipsum is simply dummy text of the printing and typesetting");
		addConteudo("industry. Lorem Ipsum has been the industry's standard dummy");
		addConteudo("text ever since the 1500s, when an unknown printer took a galley");
		addConteudo("of type and scrambled it to make a type specimen book. It has");
		addConteudo("survived not only five centuries." + "\n");
	}
	
	//método de implementação obrigatória (método abstrato da superclasse)
	protected void processaCmd(String cmd) {
		return;
	}	
}
