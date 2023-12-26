package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ita.game.Jogador;

@SuppressWarnings("unused")
class TestaTelaSelecionarNivelExperiencia {

	@Test
	//exibir tela com o jogador
	void exibirTela1() throws Exception {
		//cria jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria e exibe tela
		TelaSelecionarNivelExperiencia tela = new TelaSelecionarNivelExperiencia(jog);
		tela.exibirTela();
	} //resultado do teste: (esperado) - tela exibida com sucesso
	
	@Test
	//exibir tela sem o jogador (jogador null)
	void exibirTela2() throws Exception {
		TelaSelecionarNivelExperiencia tela = new TelaSelecionarNivelExperiencia(null);
		tela.exibirTela();
	} //resultado do teste: (esperado) - sem jogador, tela não é exibida

	@Test
	void testarNavegacao() throws Exception {
		//cria jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria e exibe tela
		TelaSelecionarNivelExperiencia tela = new TelaSelecionarNivelExperiencia(jog);
		tela.exibirTela();
	} //resultado do teste: (esperado)
			//opção 1 -> chama TelaSelecionarGrauDificuldade 
			//opção 2 -> chama TelaSelecionarGrauDificuldade
			//opção 3 -> chama TelaSelecionarGrauDificuldade
			//opção a -> chama TelaAjudaSelecionarNivelExperiencia
			//opção e -> chama TelaEditarJogador
			//opção x -> chama TelaExcluirJogador
}
