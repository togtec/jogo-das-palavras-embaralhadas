/* A classe EmbInverteLetrasParImpar coloca as letras pares na posição das letras ímpares. E vice versa */

package br.ita.game.emb;

import br.ita.game.enu.TipoEmbaralhador;

public class EmbInverteLetrasParImpar extends Embaralhador {
	//construtor
	EmbInverteLetrasParImpar() {
		super(TipoEmbaralhador.LETRASPAD);
	}
	
	public String embaralharPalavra(String palavra) {
		//remove os espaços separador de sílabas
		palavra = palavra.replace(" ", "");
		//transforma palavra em array
		char[] p = palavra.toCharArray();
		
		StringBuilder palavraEmbaralhada = new StringBuilder();
		
		//percorre o array p invertendo os pares de letras
		int cont = 1;
		while (cont <= p.length) {
			try {
				//armazena o par de letras invertido
				palavraEmbaralhada.append(p[cont]);
				palavraEmbaralhada.append(" ");
				palavraEmbaralhada.append(p[cont - 1]);
				
				//se houver mais letras
				if (cont < p.length - 1)
					//adiciona espaço entre pares
					palavraEmbaralhada.append(" ");
				
			} catch (ArrayIndexOutOfBoundsException ex) {
				//exceção lançada sempre que o número de letras da palavra for ímpar
				palavraEmbaralhada.append(p[cont - 1]);
			} finally {
				//incrementa o contador
				cont+= 2;
			}
		}
		
		return palavraEmbaralhada.toString();
	}
}
