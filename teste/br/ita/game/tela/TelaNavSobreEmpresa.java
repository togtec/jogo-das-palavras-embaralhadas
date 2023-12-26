/* A classe TelaNavCursos estende a classe TelaNav apenas para fins de teste. Como a classe TelaNav � abstrata, 
 * s� pode ser testada atrav�s de uma de suas subclasses concretas. 
 * 
 * Nesse teste a classe TelaNavSobreEmpresa foi instanciada e exibida por TelaNavHome. Ou seja, ao fechar TelaNavSobreEmpresa,
 * o usu�rio deve retornar para TelaNavHome */

package br.ita.game.tela;

public class TelaNavSobreEmpresa extends TelaNav{
	//m�todo construtor
	public TelaNavSobreEmpresa() {
		super("Sobre a Empresa");
		addConteudo("Lorem Ipsum is simply dummy text of the printing and typesetting");
		addConteudo("industry. Lorem Ipsum has been the industry's standard dummy");
		addConteudo("text ever since the 1500s, when an unknown printer took a galley");
		addConteudo("of type and scrambled it to make a type specimen book. It has");
		addConteudo("survived not only five centuries." + "\n");
	}
	
	//m�todo de implementa��o obrigat�ria (m�todo abstrato da superclasse)
	protected void processaCmd(String cmd) {
		return;
	}	
}
