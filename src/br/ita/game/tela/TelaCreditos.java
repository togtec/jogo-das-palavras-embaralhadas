/* A TelaCreditos é exibida apenas no encerramento do jogo. Ao contrário das demais telas, 
 * TelaCreditos não possue loop e não lê a entrada do usuário. */
package br.ita.game.tela;

public class TelaCreditos extends TelaNav {
	//método construtor
	public TelaCreditos() {
		super("Versão/Crédidos");
		addConteudo("Versão: 1.0");
		addConteudo("Data: 25/05/2021");
		addConteudo("");
		addConteudo("O Jogo das Palavras Embaralhadas foi criado como trabalho final de conclusão de curso");
		addConteudo("");
		addConteudo("Curso: Orientação a Objetos com Java");
		addConteudo("Instituição de Ensino: ITA (Instituto Tecnológico de Aeronáutica)");
		addConteudo("Plataforma de ensino: Coursera");
		addConteudo("Página do curso: www.coursera.org/learn/orientacao-a-objetos-com-java");
		addConteudo("");
		addConteudo("Professores: Clovis Fernandes e Eduardo Guerra");
		addConteudo("Estudante: Rodrigo Tognetta");
		addConteudo("");
		addConteudo("Tempo total de desenvovimento:  .........  667 horas");
		addConteudo("     Projeto:  ..........................   12 horas");
		addConteudo("     Modelagem CRC:  ....................  266 horas");
		addConteudo("     Documentação UML  ..................   73 horas");
		addConteudo("     Programação e Testes:  .............  316 horas");
		addConteudo("");
		addConteudo("Estatísticas Ambiente de Produção:");
		addConteudo("     Classes java normal:  ..............   34");
		addConteudo("     Classes interna:  ..................    1");
		addConteudo("     Enumerações:  ......................    8");
		addConteudo("     Arquivos de informação  ............    2");
		addConteudo("");
		addConteudo("Estatísticas Ambiente de Teste:");
		addConteudo("     Classes de teste normal: ...........    6");
		addConteudo("     Classes de teste JUnit:  ...........   35");
		addConteudo("     Arquivos de informação  ............    8");
		
		limpaBarraMenu();
		setPromptCmd("jogo encerrado> Obrigado por escolher Jogo das Palavras Embaralhadas!");
	}
		
	@Override
	//método sobrescrito para remover o loop e para não ler a entrada do usuário
	public void exibirTela() {
		impCabecalho();
		impConteudo();
		impPromptCmd();
	}
	
	@Override
	//método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}
}
