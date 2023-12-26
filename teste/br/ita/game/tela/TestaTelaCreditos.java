package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class TestaTelaCreditos {

	@Test
	//o que deve ser testado:
		//1- texto a ser exibido
		//2- TelaCreditos não tem loop
		//3- TelaCreditos não lê a entrada do usuário
	void exibirTela() {
		new TelaCreditos().exibirTela();
	} //resultado do teste: (esperado)

}
