package br.ita.game.mec;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ita.game.emb.Embaralhador;
import br.ita.game.emb.FabricaEmbaralhador;
import br.ita.game.enu.GrauDificuldade;
import br.ita.game.enu.NivelExperiencia;

//A classe FabricaMecanicaDoJogo retorna a MecanicaDoJogo de acordo com o grau de dificuldade da partida. 
	//GrauDificuldade.FACIL   --> TipoMecanica.PALPITS_SEM_LIMITE  --> Classe: AteUltimaPalavra
	//GrauDificuldade.NORMAL  --> TipoMecanica.PALPITS_POR_PALAVRA --> Classe: PalpitesPorPalavra
	//GrauDificuldade.DIFICIL --> TipoMecanica.PALPITS_POR_PARTIDA --> Classe: PalpitesPorPartida
//obs: O embaralhador não influencia na escolha!
class TestaFabricaMecanicaDoJogo {

	@Test
	void getMecanicaDoJogo() {
		Embaralhador embIniciante = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		Embaralhador embIntermediario = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INTERMEDIARIO);
		Embaralhador embAvancado = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.AVANCADO);
		
		//GrauDificuldade.FACIL
		MecanicaDoJogo mec1 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
			assertTrue(mec1 instanceof AteUltimaPalavra);
			assertEquals("(palpites sem limite)", mec1.getTipoMecanica());
			
		MecanicaDoJogo mec2 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIntermediario);
			assertTrue(mec2 instanceof AteUltimaPalavra);
			assertEquals("(palpites sem limite)", mec1.getTipoMecanica());
			
		MecanicaDoJogo mec3 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embAvancado);
			assertTrue(mec3 instanceof AteUltimaPalavra);
			assertEquals("(palpites sem limite)", mec1.getTipoMecanica());
			
			
			
		//GrauDificuldade.NORMAL
		MecanicaDoJogo mec4 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.NORMAL, embIniciante);
			assertTrue(mec4 instanceof PalpitesPorPalavra);
			assertEquals("(palpites por palavra)", mec4.getTipoMecanica());
				
		MecanicaDoJogo mec5 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.NORMAL, embIntermediario);
			assertTrue(mec5 instanceof PalpitesPorPalavra);
			assertEquals("(palpites por palavra)", mec5.getTipoMecanica());
		
		MecanicaDoJogo mec6 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.NORMAL, embAvancado);
			assertTrue(mec6 instanceof PalpitesPorPalavra);
			assertEquals("(palpites por palavra)", mec6.getTipoMecanica());
		
		
		
		//GrauDificuldade.DIFICIL
		MecanicaDoJogo mec7 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.DIFICIL, embIniciante);
			assertTrue(mec7 instanceof PalpitesPorPartida);
			assertEquals("(palpites por partida)", mec7.getTipoMecanica());
		
		MecanicaDoJogo mec8 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.DIFICIL, embIntermediario);
			assertTrue(mec8 instanceof PalpitesPorPartida);
			assertEquals("(palpites por partida)", mec8.getTipoMecanica());
		
		MecanicaDoJogo mec9 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.DIFICIL, embAvancado);
			assertTrue(mec9 instanceof PalpitesPorPartida);
			assertEquals("(palpites por partida)", mec9.getTipoMecanica());
	}
}
