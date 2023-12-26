/* A enumera��o NivelExperiencia ser� utilizada para classificar o TipoEmbaralhador 
 * NivelExperiencia.INICIANTE     -> para TipoEmbaralhador.SILABAS 
 * NivelExperiencia.INTERMEDIARIO -> para TipoEmbaralhador.LETRASPAD 
 * NivelExperiencia.AVANCADO      -> para TipoEmbaralhador.LETRASALEAT */

package br.ita.game.enu;

public enum NivelExperiencia {	
	INICIANTE("Iniciante"),
	INTERMEDIARIO("Intermedi�rio"),
	AVANCADO("Avan�ado");

	//vari�veis de inst�ncia
	private String nivelExp;
	
	//construtor
	private NivelExperiencia(String nivelExp) {
		this.nivelExp = nivelExp;
	}
	
	public String toString() {
		return nivelExp;
	}	
}

