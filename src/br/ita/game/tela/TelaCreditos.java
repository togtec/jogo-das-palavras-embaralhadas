/* A TelaCreditos � exibida apenas no encerramento do jogo. Ao contr�rio das demais telas, 
 * TelaCreditos n�o possue loop e n�o l� a entrada do usu�rio. */
package br.ita.game.tela;

public class TelaCreditos extends TelaNav {
	//m�todo construtor
	public TelaCreditos() {
		super("Vers�o/Cr�didos");
		addConteudo("Vers�o: 1.0");
		addConteudo("Data: 25/05/2021");
		addConteudo("");
		addConteudo("O Jogo das Palavras Embaralhadas foi criado como trabalho final de conclus�o de curso");
		addConteudo("");
		addConteudo("Curso: Orienta��o a Objetos com Java");
		addConteudo("Institui��o de Ensino: ITA (Instituto Tecnol�gico de Aeron�utica)");
		addConteudo("Plataforma de ensino: Coursera");
		addConteudo("P�gina do curso: www.coursera.org/learn/orientacao-a-objetos-com-java");
		addConteudo("");
		addConteudo("Professores: Clovis Fernandes e Eduardo Guerra");
		addConteudo("Estudante: Rodrigo Tognetta");
		addConteudo("");
		addConteudo("Tempo total de desenvovimento:  .........  667 horas");
		addConteudo("     Projeto:  ..........................   12 horas");
		addConteudo("     Modelagem CRC:  ....................  266 horas");
		addConteudo("     Documenta��o UML  ..................   73 horas");
		addConteudo("     Programa��o e Testes:  .............  316 horas");
		addConteudo("");
		addConteudo("Estat�sticas Ambiente de Produ��o:");
		addConteudo("     Classes java normal:  ..............   34");
		addConteudo("     Classes interna:  ..................    1");
		addConteudo("     Enumera��es:  ......................    8");
		addConteudo("     Arquivos de informa��o  ............    2");
		addConteudo("");
		addConteudo("Estat�sticas Ambiente de Teste:");
		addConteudo("     Classes de teste normal: ...........    6");
		addConteudo("     Classes de teste JUnit:  ...........   35");
		addConteudo("     Arquivos de informa��o  ............    8");
		
		limpaBarraMenu();
		setPromptCmd("jogo encerrado> Obrigado por escolher Jogo das Palavras Embaralhadas!");
	}
		
	@Override
	//m�todo sobrescrito para remover o loop e para n�o ler a entrada do usu�rio
	public void exibirTela() {
		impCabecalho();
		impConteudo();
		impPromptCmd();
	}
	
	@Override
	//m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
}
