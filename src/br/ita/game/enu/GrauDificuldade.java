/* A enumeração GrauDificuldade será utilizada para classificar o TipoMecanica 
 * GrauDificuldade.FACIL   -> para TipoMecanica.PalpitsSemLimite 
 * GrauDificuldade.NORMAL  -> para TipoMecanica.PalpitsPorPalavra 
 * GrauDificuldade.DIFICIL -> para TipoMecanica.PalpitsPorPartida*/

package br.ita.game.enu;

public enum GrauDificuldade {
	FACIL("Fácil"),
	NORMAL("Normal"),
	DIFICIL("Difícil");
	
	//variáveis de instância
	private String grauDif;
	
	//construtor
	private GrauDificuldade(String grauDif) {
		this.grauDif = grauDif;
	}
	
	public String toString() {
		return grauDif;
	}
}
