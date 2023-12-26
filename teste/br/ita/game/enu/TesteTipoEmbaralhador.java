package br.ita.game.enu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteTipoEmbaralhador {

	@Test
	void testeTipoEmbaralhador() {				
		TipoEmbaralhador tipoEmb1 = TipoEmbaralhador.SILABAS;
		assertEquals("Iniciante",tipoEmb1.getNivelExp());
		assertEquals("S�labas", tipoEmb1.getTipoEmb());
		assertEquals(1, tipoEmb1.getMult());
		
		TipoEmbaralhador tipoEmb2 = TipoEmbaralhador.LETRASPAD;
		assertEquals("Intermedi�rio", tipoEmb2.getNivelExp());
		assertEquals("Letras Padronizado", tipoEmb2.getTipoEmb());
		assertEquals(2, tipoEmb2.getMult());
		
		TipoEmbaralhador tipoEmb3 = TipoEmbaralhador.LETRASALEAT;
		assertEquals("Avan�ado", tipoEmb3.getNivelExp());
		assertEquals("Letras Aleat�rio", tipoEmb3.getTipoEmb());
		assertEquals(3, tipoEmb3.getMult());
	}

}
