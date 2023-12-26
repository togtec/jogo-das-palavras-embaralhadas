/* A enumeração TipoMecanica será utilizada para classificar as subclasses de MecanicaDoJogo 
 * TipoMecanica.PalpitsSemLimite -> para subclasses de MecanicaDoJogo que não possuem limite de palpites
 * TipoMecanica.PalpitsPorPalavra -> para subclasses de MecanicaDoJogo que definem um número máximo de palpites por palavra 
 * TipoMecanica.PalpitsPorPartida -> para subclasses de MecanicaDoJogo que definem um número máximo de palpites por partida 
 * 
 * O Jogador(a) que acertar todas as palavras da partida ganha "bônus por vitória" e "bonus por tempo restante", cujos os 
 * valores dependem do TipoMecanica que a subclasse utiliza.
 * 		TipoMecanica.PalpitsSemLimite:
 * 			bônus por vitória: 5 pontos
 * 			bonus por tempo restante: 1 ponto à cada 10 segundos de tempo não utilizado
 * 
 * 		TipoMecanica.PalpitsPorPalavra:
 * 			bônus por vitória: 20 pontos
 * 			bonus por tempo restante: 2 pontos à cada 10 segundos de tempo não utilizado
 * 
 * 		TipoMecanica.PalpitsPorPartida:
 * 			bônus por vitória: 50 pontos
 * 			bonus por tempo restante: 5 pontos à cada 10 segundos de tempo não utilizado */

package br.ita.game.enu;

public enum TipoMecanica {
	PALPITS_SEM_LIMITE(GrauDificuldade.FACIL, "(palpites sem limite)", 5, 1),
	PALPITS_POR_PALAVRA(GrauDificuldade.NORMAL, "(palpites por palavra)", 20, 2),
	PALPITS_POR_PARTIDA(GrauDificuldade.DIFICIL, "(palpites por partida)", 50, 5);

	//variáveis de instância
	private GrauDificuldade grauDif;
	private String tipoMecanica;
	private int bonusVitoria;
	private int bonusTempoRest;
	
	//construtor
	private TipoMecanica(GrauDificuldade grauDif, String tipoMecanica, int bonusVitoria, int bonusTempoRest) {
		this.grauDif = grauDif;
		this.tipoMecanica = tipoMecanica;
		this.bonusVitoria = bonusVitoria;
		this.bonusTempoRest = bonusTempoRest;
	}
	
	//retorna o grau de dificuldade
	public String getGrauDif() {
		return grauDif.toString();
	}
	
	//retorna o tipo da mecânica
	public String getTipoMecanica() {
		return tipoMecanica;
	}
	
	//retorna o bônus por vitória
	public int getBonusVitoria() {
		return bonusVitoria;
	}
	
	//retorna o bônus por tempo restante
	public int getBonusTempoRest() {
		return bonusTempoRest;
	}
}
