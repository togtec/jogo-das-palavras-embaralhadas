package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class TestaTelaCreditos {

	@Test
	//o que deve ser testado:
		//1- texto a ser exibido
		//2- TelaCreditos n�o tem loop
		//3- TelaCreditos n�o l� a entrada do usu�rio
	void exibirTela() {
		new TelaCreditos().exibirTela();
	} //resultado do teste: (esperado)

}
