package br.ita.game.emb;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testeEmbLetrasAleat {

	@Test
	void embaralharPalavra() {
		Embaralhador emb = new EmbLetrasAleat();
		
		assertEquals("Avançado", emb.getNivelExp());
		assertEquals("Letras Aleatório", emb.getTipoEmb());
		assertEquals(3, emb.getMult());
		
		assertEquals("a", emb.embaralharPalavra("a"));
		assertEquals("á p", emb.embaralharPalavra("pá"));
		
		assertNotEquals("pai", emb.embaralharPalavra("pai"));
			System.out.println("pai" + "   --->   " + emb.embaralharPalavra("pai"));
		assertNotEquals("par is", emb.embaralharPalavra("par is"));
			System.out.println("par is" + "   --->   " + emb.embaralharPalavra("par is"));
		assertNotEquals("sa ci", emb.embaralharPalavra("sa ci"));
			System.out.println("sa ci" + "   --->   " + emb.embaralharPalavra("sa ci"));
		assertNotEquals("bi go de", emb.embaralharPalavra("bi go de"));
			System.out.println("bi go de" + "   --->   " + emb.embaralharPalavra("bi go de"));
		assertNotEquals("ba lei a", emb.embaralharPalavra("ba lei a"));
			System.out.println("ba lei a" + "   --->   " + emb.embaralharPalavra("ba lei a"));
		assertNotEquals("ca be lo", emb.embaralharPalavra("ca be lo"));
			System.out.println("ca be lo" + "   --->   " + emb.embaralharPalavra("ca be lo"));
		assertNotEquals("pa ra le le pí pe do", emb.embaralharPalavra("pa ra le le pí pe do"));
			System.out.println("pa ra le le pí pe do" + "   --->   " + emb.embaralharPalavra("pa ra le le pí pe do"));
	}
}
