package br.ita.game.tela;

public class TelaAjudaSelecionarNivelExperiencia extends TelaNav {

	//construtor
	public TelaAjudaSelecionarNivelExperiencia() {
		super("Ajuda/Selecionar Nível de Experiência do(a) Jogador(a)");
		addConteudo("O nível de experiência do(a) Jogador(a) define o \"Tipo Embaralhador\" que será utilizado.");
		addConteudo("");
		addConteudo("No Nível Iniciante apenas as sílabas são embaralhadas - Tipo Embaralhador: Sílabas");
		addConteudo("No Nível Intermediário as letras são embaralhadas de forma padronizada - Tipo Embaralhador: Letras Padronizado");
		addConteudo("No Nível Avançado as letras são embaralhadas de forma aleatória - Tipo Embaralhador: Letras Aleatório");
		addConteudo("");
		addConteudo("Além de definir a forma do embaralhamento, cada Tipo Embaralhador também possui um multiplicador que determina a");
		addConteudo("quantidade de pontos ganhos à cada acerto. Imagine, por exemplo, que o(a) Jogador(a) acerte uma palavra que vale 3 pontos:");
		addConteudo("");
		addConteudo("No Nível Iniciante - Tipo Embaralhador Sílabas - (Multiplicador 1) - o(a) Jogador(a) ganha 3 pontos: 3 x 1");
		addConteudo("No Nível Intermediário - Tipo Embaralhador Letras Padronizado - (Multiplicador 2) - o(a) Jogador(a) ganha 6 pontos: 3 x 2");
		addConteudo("No Nível Avançado, Tipo Embaralhador Letras Aleatório - (Multiplicador 3) - o(a) Jogador(a) ganha 9 pontos: 3 x 3");
	}
	
	@Override
	//método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		return;
	}	
}
