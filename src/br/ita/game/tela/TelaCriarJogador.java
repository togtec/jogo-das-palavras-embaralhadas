package br.ita.game.tela;

import br.ita.game.Jogador;

public class TelaCriarJogador extends TelaNav {
	private String nome;
	private String apelido;
	
	//m�todo construtor
	public TelaCriarJogador() {
		super("Criar Jogador(a)");
		addConteudo("                                                     *obrigat�rio");
		addConteudo("*Por favor digite o nome do(a) Jogador(a) na linha de comando!");
	}
	
	@Override
	//m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		if (nome == null) {
			if (Jogador.nomeValido(cmd)) {
				nome = cmd;
				limpaConteudo();
				addConteudo("Nome: " + nome);
				addConteudo(getLinhaDivisoria(TelaNav.margemEsquerda, separadorInterno).trim()); /* nesse caso:
					1- � necess�rio colocar margem esquerda na linha divis�ria para que o tracejado fique no tamanho certo
					2- � necess�rio remover a margem esquerda com trim() pois todo conte�do adicionado � impresso com margem automaticamente */
				addConteudo("Por favor digite o apelido do(a) Jogador(a) na linha de comando!");
			}
		} else if (apelido == null) {
			if (Jogador.apelidoValido(cmd)) {
				apelido = cmd;
				criarJogador();
			}
		}
	} //fim do m�todo processaCmd
	
	private void criarJogador() {
		try {
			Jogador jog = Jogador.getInstance(nome, apelido);
			limpaConteudo();
			addConteudo("Parab�ns! Jogador(a) " + jog.getNome() + " criado(a) com sucesso!");
		} catch (Exception ex) {
			limpaConteudo();
			addConteudo("Erro: " + ex.getMessage());
		}
	}
}
