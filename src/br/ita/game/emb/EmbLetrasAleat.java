/* A classe EmbLetrasAleat embaralha as letras da palavra de forma aleatória */

package br.ita.game.emb;

import br.ita.game.enu.TipoEmbaralhador;

public class EmbLetrasAleat extends Embaralhador {
	//construtor
	EmbLetrasAleat() {
		super(TipoEmbaralhador.LETRASALEAT);
	}
	
	public String embaralharPalavra(String palavra) {
		//remove os espaços separador de sílabas
		palavra = palavra.replace(" ", "");
		
		//cria uma matriz com as letras da palavra
		String[] matrizLetras = new String[palavra.length()];
			for (int cont = 0; cont < palavra.length(); cont++) {
				matrizLetras[cont] = Character.toString(palavra.charAt(cont));
		}
		
		return embaralharMatriz(matrizLetras);
	}	
}
