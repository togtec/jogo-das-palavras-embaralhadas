package br.ita.game.enu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteNivelExperiencia {

	@Test
	void testeNivelExperiencia() {				
		NivelExperiencia nv1 = NivelExperiencia.INICIANTE;
		assertEquals("Iniciante", nv1.toString());
		
		NivelExperiencia nv2 = NivelExperiencia.INTERMEDIARIO;
		assertEquals("Intermedi�rio", nv2.toString());
		
		NivelExperiencia nv3 = NivelExperiencia.AVANCADO;
		assertEquals("Avan�ado", nv3.toString());
	}
}
