/* A enumera��o TipoMecanica ser� utilizada para classificar as subclasses de MecanicaDoJogo 
 * TipoMecanica.PalpitsSemLimite -> para subclasses de MecanicaDoJogo que n�o possuem limite de palpites
 * TipoMecanica.PalpitsPorPalavra -> para subclasses de MecanicaDoJogo que definem um n�mero m�ximo de palpites por palavra 
 * TipoMecanica.PalpitsPorPartida -> para subclasses de MecanicaDoJogo que definem um n�mero m�ximo de palpites por partida 
 * 
 * O Jogador(a) que acertar todas as palavras da partida ganha "b�nus por vit�ria" e "bonus por tempo restante", cujos os 
 * valores dependem do TipoMecanica que a subclasse utiliza.
 * 		TipoMecanica.PalpitsSemLimite:
 * 			b�nus por vit�ria: 5 pontos
 * 			bonus por tempo restante: 1 ponto � cada 10 segundos de tempo n�o utilizado
 * 
 * 		TipoMecanica.PalpitsPorPalavra:
 * 			b�nus por vit�ria: 20 pontos
 * 			bonus por tempo restante: 2 pontos � cada 10 segundos de tempo n�o utilizado
 * 
 * 		TipoMecanica.PalpitsPorPartida:
 * 			b�nus por vit�ria: 50 pontos
 * 			bonus por tempo restante: 5 pontos � cada 10 segundos de tempo n�o utilizado */

package br.ita.game.enu;

public enum TipoMecanica {
	PALPITS_SEM_LIMITE(GrauDificuldade.FACIL, "(palpites sem limite)", 5, 1),
	PALPITS_POR_PALAVRA(GrauDificuldade.NORMAL, "(palpites por palavra)", 20, 2),
	PALPITS_POR_PARTIDA(GrauDificuldade.DIFICIL, "(palpites por partida)", 50, 5);

	//vari�veis de inst�ncia
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
	
	//retorna o tipo da mec�nica
	public String getTipoMecanica() {
		return tipoMecanica;
	}
	
	//retorna o b�nus por vit�ria
	public int getBonusVitoria() {
		return bonusVitoria;
	}
	
	//retorna o b�nus por tempo restante
	public int getBonusTempoRest() {
		return bonusTempoRest;
	}
}
