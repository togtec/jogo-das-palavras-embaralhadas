/*** A classe TestaPalpitesPorPartida testa as classes MecanicaDoJogo e PalpitesPorPartida
 	obs: Como MecanicaDoJogo do jogo é uma classe abstrata, só pode ser testada através de uma de suas subclasses concretas ***/

package br.ita.game.mec;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ita.game.emb.Embaralhador;
import br.ita.game.emb.FabricaEmbaralhador;
import br.ita.game.enu.GrauDificuldade;
import br.ita.game.enu.NivelExperiencia;
import br.ita.game.enu.PartidaStatus;

class TestaPalpitesPorPartida {

	@Test
	void test1() {
		Embaralhador embAvancado = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.AVANCADO);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.DIFICIL, embAvancado);
		
		assertTrue(mec instanceof PalpitesPorPartida);
		
		mec.iniciar();
			assertEquals("Avançado", mec.getNivelExp());
			assertEquals("Letras Aleatório", mec.getTipoEmb());
			assertEquals(3, mec.getMult());
			assertEquals("Difícil", mec.getGrauDif());
			assertEquals("(palpites por partida)", mec.getTipoMecanica());
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

/*** checklist do teste funcional realizado na classe PalpitesPorPartida rodado em ambiente de produção (o sistema todo funcionando)
obs: Para que a classe PalpitesPorPartida seja selecionada como mecênica pelo sistema, é necessário escolher o Grau de Dificuldade: Difícil

 1- acertar 0 e perder por time-out:                             ok
 2- acertar a 1ª palavra e perder por time-out:                  ok
 3- acertar a 1ª e a 2ª palavra e perder por time-out:           ok
 4- acertar a 1ª, 2ª e a 3ª palavra e perder por time-out:       ok
 5- acertar a 1ª, 2ª, 3ª e 4ª palavra e perder por time-out:     ok
 6- acertar todas as palavras:                                   ok
  

***/
