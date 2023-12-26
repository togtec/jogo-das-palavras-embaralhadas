package br.ita.game.tela;

import br.ita.game.Jogador;

public class TelaEditarJogador extends TelaModificarJogador {
	private boolean nomeAtualizado = false;
	private boolean apelidoAtualizado = false;
	
	//construtor
	public TelaEditarJogador(Jogador jogador) {
		super("Editar Jogador(a)", jogador);
		addMenu("m", "manter");
		addConteudo("Por favor digite o nome do(a) Jogador(a) na linha de comando!");	
	}

	@Override
	//método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		if (!nomeAtualizado) {
			atualizarNome(cmd);
			
		} else if (!apelidoAtualizado) {
			atualizarApelido(cmd);
		}

	} //fim do método processaCmd

	private void atualizarNome(String cmd) {
		if (cmd.equalsIgnoreCase("m")) {
			setNomeAtualizado();
		} else {
			if (Jogador.nomeValido(cmd)) {
				try {
					jogador.setNome(cmd);
					setNomeAtualizado();
				} catch (Exception ex) {
					setErro(ex);
				}
			}
		}
	} //fim do método atualizarNome
	
	private void atualizarApelido(String cmd) {
		if (cmd.equalsIgnoreCase("m")) {
			setApelidoAtualizado();
		} else {
			if (Jogador.apelidoValido(cmd)) {
				try {
					jogador.setApelido(cmd);
					setApelidoAtualizado();
				} catch (Exception ex) {
					setErro(ex);
				}
			}
		}
	} //fim do método atualizarApelido	
	
	private void setNomeAtualizado() {
		nomeAtualizado = true;
		//atualiza conteúdo da janela
		limpaConteudo();
		addConteudo("Por favor digite o apelido do(a) Jogador(a) na linha de comando!");
	}
	
	private void setApelidoAtualizado() {
		apelidoAtualizado = true;
		//atualiza barra de menu
		limpaBarraMenu();
		addMenu("v", "voltar");
		//atualiza conteúdo da janela
		limpaConteudo();
		addConteudo("Jogador(a) editado(a) com sucesso!");
	}
	
	private void setErro(Exception ex) {
		jogador = null; //impede que a barra de jogador seja impressa
		//atualiza barra de menu
		limpaBarraMenu();
		addMenu("v", "voltar");
		//atualiza conteúdo da janela
		limpaConteudo();
		addConteudo("Erro: " + ex.getMessage());
		//conclui
		nomeAtualizado = true;
		apelidoAtualizado = true;
	}
}
