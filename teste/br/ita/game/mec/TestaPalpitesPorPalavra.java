/*** A classe TestaPalpitesPorPalavra testa as classes MecanicaDoJogo e PalpitesPorPalavra
 	obs: Como MecanicaDoJogo do jogo é uma classe abstrata, só pode ser testada através de uma de suas subclasses concretas ***/

package br.ita.game.mec;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ita.game.emb.Embaralhador;
import br.ita.game.emb.FabricaEmbaralhador;
import br.ita.game.enu.GrauDificuldade;
import br.ita.game.enu.NivelExperiencia;
import br.ita.game.enu.PartidaStatus;

class TestaPalpitesPorPalavra {

	@Test
	void test1() {
		Embaralhador embIniciante = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.NORMAL, embIniciante);
		
		assertTrue(mec instanceof PalpitesPorPalavra);
		
		mec.iniciar();
			assertEquals("Iniciante", mec.getNivelExp());
			assertEquals("Sílabas", mec.getTipoEmb());
			assertEquals(1, mec.getMult());
			assertEquals("Normal", mec.getGrauDif());
			assertEquals("(palpites por palavra)", mec.getTipoMecanica());
			assertEquals(0, mec.getPlacar());
			assertEquals(PartidaStatus.DERROTA, mec.getPartidaStatus());
			
			System.out.println(mec.getBarraStatus());
			System.out.println(); //pula uma linha			
			System.out.println(mec.getConteudo());
			System.out.println(); //pula uma linha
			System.out.println(mec.getRelProg());
		mec.concluir();
	}
}

/*** checklist do teste funcional realizado na classe PalpitesPorPalavra rodado em ambiente de produção (o sistema todo funcionando)
obs: Para que a classe PalpitesPorPalavra seja selecionada como mecênica pelo sistema, é necessário escolher o Grau de Dificuldade: Normal

 1- acertar 0 palavras e perder por time-out:                   ok
 2- acertar a 1ª palavra e perder por time-out:                 ok
 3- acertar a 1ª e a 2ª palavra e perder por time-out:          ok
 4- acertar a 1ª, 2ª e a 3ª palavra e perder por time-out:      ok
 5- acertar a 1ª, 2ª, 3ª e 4ª palavra e perder por time-out:    ok
 6- acertar todas as palavras:                                  ok

 7- acertar somente a 5ª palavra:                               ok
 8- acertar somente a 5ª e a 4ª:                                ok
 9- acertar somente a 5ª, 4ª e a 3ª:                            ok
10- acertar somente a 5ª, 4ª, 3ª e 2ª:                          ok

11- errar todas e perder por falta de palpites:                 ok
12- acertar a 1ª, 3ª e 5ª:                                      ok
13- acertar a 2ª e 4ª                                           ok
14- enviar o palpite correto após o timeout:                    ok (palpite ignorado)
15- acertar quatro palavras e abortar a partida:                ok (pontos obtidos são descartados)

***/
