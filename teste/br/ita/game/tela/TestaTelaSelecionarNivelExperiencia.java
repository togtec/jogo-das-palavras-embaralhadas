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
	} //resultado do teste: (esperado) - sem jogador, tela n�o � exibida

	@Test
	void testarNavegacao() throws Exception {
		//cria jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria e exibe tela
		TelaSelecionarNivelExperiencia tela = new TelaSelecionarNivelExperiencia(jog);
		tela.exibirTela();
	} //resultado do teste: (esperado)
			//op��o 1 -> chama TelaSelecionarGrauDificuldade 
			//op��o 2 -> chama TelaSelecionarGrauDificuldade
			//op��o 3 -> chama TelaSelecionarGrauDificuldade
			//op��o a -> chama TelaAjudaSelecionarNivelExperiencia
			//op��o e -> chama TelaEditarJogador
			//op��o x -> chama TelaExcluirJogador
}
