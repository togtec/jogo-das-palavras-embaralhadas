package br.ita.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ita.game.enu.PalpiteStatus;

class TestaPalavra {

	@Test
	void getPalpiteStatus() {
		Palavra palavra = new Palavra("Sa ci", 2, "Uma das mais populares entidades fantásticas do Brasil, negrinho de \numa só perna, de cachimbo e com barrete vermelho (fonte, este último, de \nseus poderes mágicos), e que, consoante a crença popular, persegue os \nviajantes ou lhes arma ciladas pelo caminho (Dicionário Aurélio)");
		
		assertEquals("Sa ci", palavra.getSilabas());
			System.out.println(palavra.getSilabas());
		assertEquals(2, palavra.getValorPalavra());
			System.out.println(palavra.getValorPalavra());
		assertEquals("Uma das mais populares entidades fantásticas do Brasil, negrinho de \numa só perna, de cachimbo e com barrete vermelho (fonte, este último, de \nseus poderes mágicos), e que, consoante a crença popular, persegue os \nviajantes ou lhes arma ciladas pelo caminho (Dicionário Aurélio)", palavra.getDica());
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
