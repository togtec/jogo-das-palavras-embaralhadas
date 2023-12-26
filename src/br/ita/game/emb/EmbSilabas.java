/* A classe EmbSilabas embaralha as s�labas da palavra de forma aleat�ria */

package br.ita.game.emb;

import br.ita.game.enu.TipoEmbaralhador;

public class EmbSilabas extends Embaralhador {	
	//construtor
	EmbSilabas() {
		super(TipoEmbaralhador.SILABAS);
	}
	
	public String embaralharPalavra(String palavra) {
		//cria uma matriz com as silabas da palavra 
		String[] matrizSilabas = palavra.split(" ");
		
		//embaralha
		return embaralharMatriz(matrizSilabas);
	}
}
