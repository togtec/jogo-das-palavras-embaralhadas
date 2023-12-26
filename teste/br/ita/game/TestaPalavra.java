package br.ita.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ita.game.enu.PalpiteStatus;

class TestaPalavra {

	@Test
	void getPalpiteStatus() {
		Palavra palavra = new Palavra("Sa ci", 2, "Uma das mais populares entidades fant�sticas do Brasil, negrinho de \numa s� perna, de cachimbo e com barrete vermelho (fonte, este �ltimo, de \nseus poderes m�gicos), e que, consoante a cren�a popular, persegue os \nviajantes ou lhes arma ciladas pelo caminho (Dicion�rio Aur�lio)");
		
		assertEquals("Sa ci", palavra.getSilabas());
			System.out.println(palavra.getSilabas());
		assertEquals(2, palavra.getValorPalavra());
			System.out.println(palavra.getValorPalavra());
		assertEquals("Uma das mais populares entidades fant�sticas do Brasil, negrinho de \numa s� perna, de cachimbo e com barrete vermelho (fonte, este �ltimo, de \nseus poderes m�gicos), e que, consoante a cren�a popular, persegue os \nviajantes ou lhes arma ciladas pelo caminho (Dicion�rio Aur�lio)", palavra.getDica());
			System.out.println(palavra.getDica());
			
		assertEquals(PalpiteStatus.ACERTO, palavra.getPalpiteStatus("Sa ci"));
		assertEquals(PalpiteStatus.ACERTO, palavra.getPalpiteStatus("sa ci"));
		assertEquals(PalpiteStatus.ACERTO, palavra.getPalpiteStatus("sa Ci"));
		assertEquals(PalpiteStatus.ACERTO, palavra.getPalpiteStatus("saci"));
		assertEquals(PalpiteStatus.ACERTO, palavra.getPalpiteStatus("SaCi"));
		assertEquals(PalpiteStatus.ACERTO, palavra.getPalpiteStatus("s a c i"));
		assertEquals(PalpiteStatus.ACERTO, palavra.getPalpiteStatus("  s a c i  "));
		
		assertEquals(PalpiteStatus.ERRO, palavra.getPalpiteStatus("sac"));
	}

}
