/* A enumeração TipoEmbaralhador será utilizada para classificar as subclasses de Embaralhador 
 * TipoEmbaralhador.SILABAS -> para subclasses de Embaralhador que embaralham apenas as sílabas 
 * TipoEmbaralhador.LETRASPAD -> para subclasses de Embaralhador que embaralham as letras de forma padronizada 
 * TipoEmbaralhador.LETRASALEAT -> para subclasses de Embaralhador que embaralham as letras de forma aleatória 
 * 
 * Cada tipo embaralhador possui um multiplicador que será utilizado para definir o valor do acerto. Por exemplo, imagine que
 * o(a) Jogador(a) acerte uma palavra que vale 3 pontos: 
 * 		Se ela foi embaralhada com um embaralhador tipo SILABAS, o(a) Jogador(a) ganha apenas 3 pontos: 3 x 1 
 * 		Se ela foi embaralhada com um embaralhador tipo LETRASPAD, o(a) Jogador(a) ganha 6 pontos: 3 x 2 
 * 		Se ela foi embaralhada com um embaralhador tipo LETRASALEAT, o(a) Jogador(a) ganha 9 pontos: 3 x 3 */

package br.ita.game.enu;

public enum TipoEmbaralhador {
	SILABAS(NivelExperiencia.INICIANTE, "Sílabas", 1), 
	LETRASPAD(NivelExperiencia.INTERMEDIARIO, "Letras Padronizado", 2),
	LETRASALEAT(NivelExperiencia.AVANCADO , "Letras Aleatório", 3);

	//variáveis de instância
	private NivelExperiencia nivelExp;
	private String tipoEmb;
	private int mult;
	
	//construtor
	private TipoEmbaralhador(NivelExperiencia nivelExp, String tipoEmb, int mult) {
		this.nivelExp = nivelExp;
		this.tipoEmb = tipoEmb;
		this.mult = mult;
	}
	
	//retorna o nível de experiência
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
