package br.ita.game.enu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteTipoMecanica {

	@Test
	void testeTipoMecanica() {
		TipoMecanica tipoMec1 = TipoMecanica.PALPITS_SEM_LIMITE;
		assertEquals("Fácil", tipoMec1.getGrauDif());
		assertEquals("(palpites sem limite)", tipoMec1.getTipoMecanica());
		assertEquals(5, tipoMec1.getBonusVitoria());
		assertEquals(1, tipoMec1.getBonusTempoRest());
		
		TipoMecanica tipoMec2 = TipoMecanica.PALPITS_POR_PALAVRA;
		assertEquals("Normal", tipoMec2.getGrauDif());
		assertEquals("(palpites por palavra)", tipoMec2.getTipoMecanica());
		assertEquals(20, tipoMec2.getBonusVitoria());
		assertEquals(2, tipoMec2.getBonusTempoRest());
		
		TipoMecanica tipoMec3 = TipoMecanica.PALPITS_POR_PARTIDA;
		assertEquals("Difícil", tipoMec3.getGrauDif());
		assertEquals("(palpites por partida)", tipoMec3.getTipoMecanica());
		assertEquals(50, tipoMec3.getBonusVitoria());
		assertEquals(5, tipoMec3.getBonusTempoRest());
	}

}
