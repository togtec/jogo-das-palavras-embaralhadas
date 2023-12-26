/* A classe FabricaEmbaralhador retorna o embaralhador de acordo com o nível de experiência do jogador:
 	NivelExperiencia.INICIANTE      --> TipoEmbaralhador.SILABAS     --> Classes: EmbSilabas
 	NivelExperiencia.INTERMEDIARIO  --> TipoEmbaralhador.LETRASPAD   --> Classes: EmbInverteLetrasParImpar
 	NivelExperiencia.AVANCADO       --> TipoEmbaralhador.LETRASALEAT --> Classes: EmbLetrasAleat
 */

package br.ita.game.emb;

import br.ita.game.enu.NivelExperiencia;

public class FabricaEmbaralhador {
	public static Embaralhador getEmbaralhador(NivelExperiencia nivelExp) {
		if (nivelExp == NivelExperiencia.INICIANTE)
			return new EmbSilabas();
		else if (nivelExp == NivelExperiencia.INTERMEDIARIO)
			return new EmbInverteLetrasParImpar();
		else if (nivelExp == NivelExperiencia.AVANCADO)
			return new EmbLetrasAleat();
		else
			return null;
	}
}
