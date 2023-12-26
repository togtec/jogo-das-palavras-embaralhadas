package br.ita.game.enu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteGrauDificuldade {

	@Test
	void testeGrauDificuldade() {
		GrauDificuldade grauDif1 = GrauDificuldade.FACIL;
		assertEquals("Fácil", grauDif1.toString());
		
		GrauDificuldade grauDif2 = GrauDificuldade.NORMAL;
		assertEquals("Normal", grauDif2.toString());
		
		GrauDificuldade grauDif3 = GrauDificuldade.DIFICIL;
		assertEquals("Difícil", grauDif3.toString());
	}
}
