package br.ita.game.emb;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteEmbInverteLetrasParImpar {

	@Test
	void embaralharPalavra() {
		Embaralhador emb = new EmbInverteLetrasParImpar();
		
		assertEquals("Intermediário", emb.getNivelExp());
		assertEquals("Letras Padronizado", emb.getTipoEmb());
		assertEquals(2, emb.getMult());
		
		assertEquals("a", emb.embaralharPalavra("a"));
		assertEquals("á p", emb.embaralharPalavra("pá"));
		assertEquals("a p i", emb.embaralharPalavra("pai"));
		assertEquals("a p i r s", emb.embaralharPalavra("par is"));
		assertEquals("a s i c", emb.embaralharPalavra("sa ci"));
		assertEquals("i b o g e d", emb.embaralharPalavra("bi go de"));
		assertEquals("a b e l a i", emb.embaralharPalavra("ba lei a"));
		assertEquals("a c e b o l", emb.embaralharPalavra("ca be lo"));
		assertEquals("a p a r e l e l í p e p o d", emb.embaralharPalavra("pa ra le le pí pe do"));
	}
}
