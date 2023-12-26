/* A enumera��o TipoEmbaralhador ser� utilizada para classificar as subclasses de Embaralhador 
 * TipoEmbaralhador.SILABAS -> para subclasses de Embaralhador que embaralham apenas as s�labas 
 * TipoEmbaralhador.LETRASPAD -> para subclasses de Embaralhador que embaralham as letras de forma padronizada 
 * TipoEmbaralhador.LETRASALEAT -> para subclasses de Embaralhador que embaralham as letras de forma aleat�ria 
 * 
 * Cada tipo embaralhador possui um multiplicador que ser� utilizado para definir o valor do acerto. Por exemplo, imagine que
 * o(a) Jogador(a) acerte uma palavra que vale 3 pontos: 
 * 		Se ela foi embaralhada com um embaralhador tipo SILABAS, o(a) Jogador(a) ganha apenas 3 pontos: 3 x 1 
 * 		Se ela foi embaralhada com um embaralhador tipo LETRASPAD, o(a) Jogador(a) ganha 6 pontos: 3 x 2 
 * 		Se ela foi embaralhada com um embaralhador tipo LETRASALEAT, o(a) Jogador(a) ganha 9 pontos: 3 x 3 */

package br.ita.game.enu;

public enum TipoEmbaralhador {
	SILABAS(NivelExperiencia.INICIANTE, "S�labas", 1), 
	LETRASPAD(NivelExperiencia.INTERMEDIARIO, "Letras Padronizado", 2),
	LETRASALEAT(NivelExperiencia.AVANCADO , "Letras Aleat�rio", 3);

	//vari�veis de inst�ncia
	private NivelExperiencia nivelExp;
	private String tipoEmb;
	private int mult;
	
	//construtor
	private TipoEmbaralhador(NivelExperiencia nivelExp, String tipoEmb, int mult) {
		this.nivelExp = nivelExp;
		this.tipoEmb = tipoEmb;
		this.mult = mult;
	}
	
	//retorna o n�vel de experi�ncia
	public String getNivelExp() {
		return nivelExp.toString();
	}
	
	//retorna o tipo do embaralhador
	public String getTipoEmb() {
		return tipoEmb;
	}
	
	//retorna o multiplicador
	public int getMult() {
		return mult;
	}
}
