package br.ita.game.emb;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ita.game.enu.NivelExperiencia;

class TesteFabricaEmbaralhador {

	@Test
	void getEmbaralhador() {
		Embaralhador emb1 = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		assertEquals("class br.ita.game.emb.EmbSilabas", emb1.getClass().toString());
			assertEquals("Iniciante", emb1.getNivelExp());
			assertEquals("S�labas", emb1.getTipoEmb());
			assertEquals(1, emb1.getMult());
				
		Embaralhador emb2 = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INTERMEDIARIO);
		assertEquals("class br.ita.game.emb.EmbInverteLetrasParImpar", emb2.getClass().toString());
			assertEquals("Intermedi�rio", emb2.getNivelExp());
			assertEquals("Letras Padronizado", emb2.getTipoEmb());
			assertEquals(2, emb2.getMult());
		
		Embaralhador emb3 = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.AVANCADO);
		assertEquals("class br.ita.game.emb.EmbLetrasAleat", emb3.getClass().toString());
			assertEquals("Avan�ado", emb3.getNivelExp());
			assertEquals("Letras Aleat�rio", emb3.getTipoEmb());
			assertEquals(3, emb3.getMult());
	}
}
