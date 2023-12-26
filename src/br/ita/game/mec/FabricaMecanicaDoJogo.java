/* A classe FabricaMecanicaDoJogo retorna a MecanicaDoJogo de acordo com o grau de dificuldade da partida: 
	GrauDificuldade.FACIL   --> TipoMecanica.PALPITS_SEM_LIMITE  --> Classes: AteUltimaPalavra
	GrauDificuldade.NORMAL  --> TipoMecanica.PALPITS_POR_PALAVRA --> Classes: PalpitesPorPalavra
	GrauDificuldade.DIFICIL --> TipoMecanica.PALPITS_POR_PARTIDA --> Classes: PalpitesPorPartida
 */

package br.ita.game.mec;

import br.ita.game.emb.Embaralhador;
import br.ita.game.enu.GrauDificuldade;

public class FabricaMecanicaDoJogo {
	
	public static MecanicaDoJogo getMecanicaDoJogo(GrauDificuldade grauDif, Embaralhador emb) {
		if (grauDif == GrauDificuldade.FACIL) {
			return new AteUltimaPalavra(emb);
			
		} else if (grauDif == GrauDificuldade.NORMAL) {
			return new PalpitesPorPalavra(emb); 
			
		} else if (grauDif == GrauDificuldade.DIFICIL) {
			return new PalpitesPorPartida(emb);
			
		} else {
			return null;
		}
	}
}
