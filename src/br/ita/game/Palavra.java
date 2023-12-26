/* A classe Palavra representa a palavra a ser adivinhada */

package br.ita.game;

import br.ita.game.enu.PalpiteStatus;

public class Palavra {
	private String silabas;
	private int valorPalavra;
	private String dica;
	
	//construtor
	public Palavra(String silabas, int valorPalavra, String dica) {
		this.silabas = silabas;
		this.valorPalavra = valorPalavra;
		this.dica = dica;
	}
	
	//retorna sílabas
	public String getSilabas() {
		return silabas;
	}
	
	//retorna valor da palavra
	public int getValorPalavra() {
		return valorPalavra;
	}
	
	//retorna dica
	public String getDica() {
		return dica;
	}
	
	public PalpiteStatus getPalpiteStatus(String palpite) {
		if (palpite.replace(" ", "").equalsIgnoreCase(silabas.replace(" ", "")))
			return PalpiteStatus.ACERTO;
		else
			return PalpiteStatus.ERRO;
	}
}
