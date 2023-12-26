/* A classe EmbInverteLetrasParImpar coloca as letras pares na posi��o das letras �mpares. E vice versa */

package br.ita.game.emb;

import br.ita.game.enu.TipoEmbaralhador;

public class EmbInverteLetrasParImpar extends Embaralhador {
	//construtor
	EmbInverteLetrasParImpar() {
		super(TipoEmbaralhador.LETRASPAD);
	}
	
	public String embaralharPalavra(String palavra) {
		//remove os espa�os separador de s�labas
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
					//adiciona espa�o entre pares
					palavraEmbaralhada.append(" ");
				
			} catch (ArrayIndexOutOfBoundsException ex) {
				//exce��o lan�ada sempre que o n�mero de letras da palavra for �mpar
				palavraEmbaralhada.append(p[cont - 1]);
			} finally {
				//incrementa o contador
				cont+= 2;
			}
		}
		
		return palavraEmbaralhada.toString();
	}
}
