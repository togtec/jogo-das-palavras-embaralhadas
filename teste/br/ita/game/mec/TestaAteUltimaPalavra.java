/*** A classe TestaAteUltimaPalavra testa as classes MecanicaDoJogo e AteUltimaPalavra
 	obs: Como MecanicaDoJogo do jogo é uma classe abstrata, só pode ser testada através de uma de suas subclasses concretas ***/

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
	//testa o encerramento da mecânica por time-out (obs: esse teste leva 5 minutos para concluir)
	void emCurso() throws Exception {
		//cria a mecânica
		Embaralhador embIniciante = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
		
		
		
		//captura data-hora inicial
		long dhInicial = System.currentTimeMillis();		
		//inicia mecânica
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
		 	
		obs: independente de tempo, testar mec.emCurso() como condição do while não funciona aqui no JUnit. Exemplo:
			while(mec.emCurso()) { ... }
			
			resultado: o loop não é interrompido nunca, nem após o encerramento da dinâmica por time-out
		 */
	
	@Test
	//testa o método concluir
	void concluir() {
		//cria a mecânica
		Embaralhador embIniciante = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
		
		mec.iniciar();
		assertTrue(mec.emCurso());
		
		mec.concluir();
		assertFalse(mec.emCurso());
	}
	
	@Test
	//testa métodos de captura (NivelExperiencia.INICIANTE, GrauDificuldade.FACIL)
	void get1() {
		Embaralhador embIniciante = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mecInicianteFacil = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
		
		assertTrue(mecInicianteFacil instanceof AteUltimaPalavra);
		
		mecInicianteFacil.iniciar();
			assertEquals("Iniciante", mecInicianteFacil.getNivelExp());
			assertEquals("Sílabas", mecInicianteFacil.getTipoEmb());
			assertEquals(1, mecInicianteFacil.getMult());
			assertEquals("Fácil", mecInicianteFacil.getGrauDif());
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
	//testa métodos de captura (NivelExperiencia.INTERMEDIARIO, GrauDificuldade.FACIL)
	void get2() {
		Embaralhador embIntermediario = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INTERMEDIARIO);
		MecanicaDoJogo mecIntermFacil = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIntermediario);
		
		assertTrue(mecIntermFacil instanceof AteUltimaPalavra);
		
		mecIntermFacil.iniciar();
			assertEquals("Intermediário", mecIntermFacil.getNivelExp());
			assertEquals("Letras Padronizado", mecIntermFacil.getTipoEmb());
			assertEquals(2, mecIntermFacil.getMult());
			assertEquals("Fácil", mecIntermFacil.getGrauDif());
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
	//testa métodos de captura (NivelExperiencia.AVANCADO, GrauDificuldade.FACIL)
	void get3() {
		Embaralhador embAvancado = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.AVANCADO);
		MecanicaDoJogo mecAvancadoFacil = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embAvancado);
		
		assertTrue(mecAvancadoFacil instanceof AteUltimaPalavra);
		
		mecAvancadoFacil.iniciar();
			assertEquals("Avançado", mecAvancadoFacil.getNivelExp());
			assertEquals("Letras Aleatório", mecAvancadoFacil.getTipoEmb());
			assertEquals(3, mecAvancadoFacil.getMult());
			assertEquals("Fácil", mecAvancadoFacil.getGrauDif());
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

/*** checklist do teste funcional realizado na classe AteUltimaPalavra rodado em ambiente de produção (o sistema todo funcionando)
  obs: Para que a classe AteUltimaPalavra seja selecionada como mecênica pelo sistema, é necessário escolher o Grau de Dificuldade: Fácil

1- acertar nenhuma palavra e perder por time-out:              ok
2- acertar a primeira palavra e perder por time-out:           ok
3- acertar a 1ª e a 2ª palavra e perder por time-out:          ok
4- acertar a 1ª, 2ª e a 3ª palavra e perder por time-out:      ok
5- acertar a 1ª, 2ª, 3ª e 4ª palavra e perder por time-out:    ok
6- acertar todas as palavras
	Nível: Iniciante:                                          ok
	Nível: Intermediário:                                      ok
	Nível: Avançado:                                           ok (esse nível exibe dica)
7- enviar o palpite correto após o timeout:                    ok (palpite ignorado)
8- acertar quatro palavras e abortar a partida:                ok (pontos obtidos são descartados)

***/