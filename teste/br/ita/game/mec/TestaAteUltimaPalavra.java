/*** A classe TestaAteUltimaPalavra testa as classes MecanicaDoJogo e AteUltimaPalavra
 	obs: Como MecanicaDoJogo do jogo � uma classe abstrata, s� pode ser testada atrav�s de uma de suas subclasses concretas ***/

package br.ita.game.mec;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import br.ita.game.emb.FabricaEmbaralhador;
import br.ita.game.enu.GrauDificuldade;
import br.ita.game.enu.NivelExperiencia;
import br.ita.game.enu.PartidaStatus;
import br.ita.game.emb.Embaralhador;

class TestaAteUltimaPalavra {

	@Test
	//testa o encerramento da mec�nica por time-out (obs: esse teste leva 5 minutos para concluir)
	void emCurso() throws Exception {
		//cria a mec�nica
		Embaralhador embIniciante = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
		
		
		
		//captura data-hora inicial
		long dhInicial = System.currentTimeMillis();		
		//inicia mec�nica
		mec.iniciar();
		//testa
		assertTrue(mec.emCurso());
		
		boolean continua = true;
		while(continua) {
			if (!mec.emCurso()) {
				continua = false;
			}
			Thread.sleep(1000); //dorme 1 segundo
		}

		//testa
		assertFalse(mec.emCurso());
		//captura data-hora final
		long dhFinal = System.currentTimeMillis();
		
		
		
		//calcula o tempo utilizado
		long tempoUtilizado = dhFinal - dhInicial;
		//imprime tempo utilizado
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(tempoUtilizado);
		SimpleDateFormat formatador = new SimpleDateFormat("mm:ss");
		System.out.println("\n" + "Tempo utilizado: " + formatador.format(calendar.getTime()));
	} 	/* 
		foram realizados 3 testes:
		 	resultado teste 1: Tempo utilizado: 05:05
		 	resultado teste 2: Tempo utilizado: 05:04
		 	resultado teste 3: Tempo utilizado: 05:04
		 	
		obs: independente de tempo, testar mec.emCurso() como condi��o do while n�o funciona aqui no JUnit. Exemplo:
			while(mec.emCurso()) { ... }
			
			resultado: o loop n�o � interrompido nunca, nem ap�s o encerramento da din�mica por time-out
		 */
	
	@Test
	//testa o m�todo concluir
	void concluir() {
		//cria a mec�nica
		Embaralhador embIniciante = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
		
		mec.iniciar();
		assertTrue(mec.emCurso());
		
		mec.concluir();
		assertFalse(mec.emCurso());
	}
	
	@Test
	//testa m�todos de captura (NivelExperiencia.INICIANTE, GrauDificuldade.FACIL)
	void get1() {
		Embaralhador embIniciante = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mecInicianteFacil = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
		
		assertTrue(mecInicianteFacil instanceof AteUltimaPalavra);
		
		mecInicianteFacil.iniciar();
			assertEquals("Iniciante", mecInicianteFacil.getNivelExp());
			assertEquals("S�labas", mecInicianteFacil.getTipoEmb());
			assertEquals(1, mecInicianteFacil.getMult());
			assertEquals("F�cil", mecInicianteFacil.getGrauDif());
			assertEquals("(palpites sem limite)", mecInicianteFacil.getTipoMecanica());		
			assertEquals(0, mecInicianteFacil.getPlacar());
			assertEquals(PartidaStatus.DERROTA, mecInicianteFacil.getPartidaStatus());
			
			System.out.println(mecInicianteFacil.getBarraStatus());
			System.out.println(); //pula uma linha
			System.out.println(mecInicianteFacil.getConteudo());
			System.out.println(); //pula uma linha
			System.out.println(mecInicianteFacil.getRelProg());
		mecInicianteFacil.concluir();
	} //resultado esperado
	
	@Test
	//testa m�todos de captura (NivelExperiencia.INTERMEDIARIO, GrauDificuldade.FACIL)
	void get2() {
		Embaralhador embIntermediario = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INTERMEDIARIO);
		MecanicaDoJogo mecIntermFacil = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIntermediario);
		
		assertTrue(mecIntermFacil instanceof AteUltimaPalavra);
		
		mecIntermFacil.iniciar();
			assertEquals("Intermedi�rio", mecIntermFacil.getNivelExp());
			assertEquals("Letras Padronizado", mecIntermFacil.getTipoEmb());
			assertEquals(2, mecIntermFacil.getMult());
			assertEquals("F�cil", mecIntermFacil.getGrauDif());
			assertEquals("(palpites sem limite)", mecIntermFacil.getTipoMecanica());		
			assertEquals(0, mecIntermFacil.getPlacar());
			assertEquals(PartidaStatus.DERROTA, mecIntermFacil.getPartidaStatus());
			
			System.out.println(mecIntermFacil.getBarraStatus());
			System.out.println(); //pula uma linha
			System.out.println(mecIntermFacil.getConteudo());
			System.out.println(); //pula uma linha
			System.out.println(mecIntermFacil.getRelProg());
		mecIntermFacil.concluir();		
	}
	
	@Test
	//testa m�todos de captura (NivelExperiencia.AVANCADO, GrauDificuldade.FACIL)
	void get3() {
		Embaralhador embAvancado = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.AVANCADO);
		MecanicaDoJogo mecAvancadoFacil = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embAvancado);
		
		assertTrue(mecAvancadoFacil instanceof AteUltimaPalavra);
		
		mecAvancadoFacil.iniciar();
			assertEquals("Avan�ado", mecAvancadoFacil.getNivelExp());
			assertEquals("Letras Aleat�rio", mecAvancadoFacil.getTipoEmb());
			assertEquals(3, mecAvancadoFacil.getMult());
			assertEquals("F�cil", mecAvancadoFacil.getGrauDif());
			assertEquals("(palpites sem limite)", mecAvancadoFacil.getTipoMecanica());		
			assertEquals(0, mecAvancadoFacil.getPlacar());
			assertEquals(PartidaStatus.DERROTA, mecAvancadoFacil.getPartidaStatus());
			
			System.out.println(mecAvancadoFacil.getBarraStatus());
			System.out.println(); //pula uma linha
			System.out.println(mecAvancadoFacil.getConteudo());
			System.out.println(); //pula uma linha
			System.out.println(mecAvancadoFacil.getRelProg());
		mecAvancadoFacil.concluir();		
	}	
}

/*** checklist do teste funcional realizado na classe AteUltimaPalavra rodado em ambiente de produ��o (o sistema todo funcionando)
  obs: Para que a classe AteUltimaPalavra seja selecionada como mec�nica pelo sistema, � necess�rio escolher o Grau de Dificuldade: F�cil

1- acertar nenhuma palavra e perder por time-out:              ok
2- acertar a primeira palavra e perder por time-out:           ok
3- acertar a 1� e a 2� palavra e perder por time-out:          ok
4- acertar a 1�, 2� e a 3� palavra e perder por time-out:      ok
5- acertar a 1�, 2�, 3� e 4� palavra e perder por time-out:    ok
6- acertar todas as palavras
	N�vel: Iniciante:                                          ok
	N�vel: Intermedi�rio:                                      ok
	N�vel: Avan�ado:                                           ok (esse n�vel exibe dica)
7- enviar o palpite correto ap�s o timeout:                    ok (palpite ignorado)
8- acertar quatro palavras e abortar a partida:                ok (pontos obtidos s�o descartados)

***/