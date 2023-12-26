package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class TestaTelaConfiguracoes {

	@Test
	void exibirTela() {
		assertEquals(5, TelaConfiguracoes.getQtdPalavrasPorPartida());
		assertEquals(5, TelaConfiguracoes.getTempoMaxEmMinutos());
		
		new TelaConfiguracoes().exibirTela();;
	} //resultado do teste: (esperado) - tela exibida com sucesso

}
