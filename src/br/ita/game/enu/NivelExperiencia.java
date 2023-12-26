/* A enumeração NivelExperiencia será utilizada para classificar o TipoEmbaralhador 
 * NivelExperiencia.INICIANTE     -> para TipoEmbaralhador.SILABAS 
 * NivelExperiencia.INTERMEDIARIO -> para TipoEmbaralhador.LETRASPAD 
 * NivelExperiencia.AVANCADO      -> para TipoEmbaralhador.LETRASALEAT */

package br.ita.game.enu;

public enum NivelExperiencia {	
	INICIANTE("Iniciante"),
	INTERMEDIARIO("Intermediário"),
	AVANCADO("Avançado");

	//variáveis de instância
	private String nivelExp;
	
	//construtor
	private NivelExperiencia(String nivelExp) {
		this.nivelExp = nivelExp;
	}
	
	public String toString() {
		return nivelExp;
	}	
}

