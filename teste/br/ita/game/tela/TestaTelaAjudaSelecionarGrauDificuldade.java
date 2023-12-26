package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class TestaTelaAjudaSelecionarGrauDificuldade {

	@Test
	//avaliar o texto
	void exibirTela() {
		new TelaAjudaSelecionarGrauDificuldade().exibirTela();
	} //resultado do teste: (esperado) - texto exibido de forma apropriada

}
