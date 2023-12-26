package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class TestaTelaDefinirJogador {

	@Test
	//o que deve ser testado:
		//1- chama TelaCriarJogador
		//2- chama TelaSelecionarJogador
		//3- chama TelaCreditos
	void testaNavegacao() {
		new TelaDefinirJogador().exibirTela();
	}  //resultado do teste: (esperado) navegação testada com sucesso

}
