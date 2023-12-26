/* A enumera��o GrauDificuldade ser� utilizada para classificar o TipoMecanica 
 * GrauDificuldade.FACIL   -> para TipoMecanica.PalpitsSemLimite 
 * GrauDificuldade.NORMAL  -> para TipoMecanica.PalpitsPorPalavra 
 * GrauDificuldade.DIFICIL -> para TipoMecanica.PalpitsPorPartida*/

package br.ita.game.enu;

public enum GrauDificuldade {
	FACIL("F�cil"),
	NORMAL("Normal"),
	DIFICIL("Dif�cil");
	
	//vari�veis de inst�ncia
	private String grauDif;
	
	//construtor
	private GrauDificuldade(String grauDif) {
		this.grauDif = grauDif;
	}
	
	public String toString() {
		return grauDif;
	}
}
