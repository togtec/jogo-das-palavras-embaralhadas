package br.ita.game.emb;

import java.util.ArrayList;

import br.ita.game.enu.TipoEmbaralhador;

public abstract class Embaralhador {
	private TipoEmbaralhador tipoEmb;
	
	//método construtor
	Embaralhador(TipoEmbaralhador tipoEmb) {
		this.tipoEmb = tipoEmb;
	}
	
	//retorna o nível de experiência
	public String getNivelExp() {
		return tipoEmb.getNivelExp();
	}
	
	//retorna o tipo do embaralhador
	public String getTipoEmb() {
		return tipoEmb.getTipoEmb();
	}
	
	//retorna o multiplicador
	public int getMult() {
		return tipoEmb.getMult();
	}
		
	//retorna true se todas as Strings do array matriz forem iguais
	private static boolean simetrica(String[] matriz) {
		boolean resposta = true;
					
		try {
			for (int cont = 0; cont < matriz.length; cont++) {
				if (matriz[cont].contentEquals(matriz[cont + 1])) {
					resposta = true;
				} else {
					resposta = false;
				}
			}
		} catch (ArrayIndexOutOfBoundsException excp) { 
			//exceção lançada sempre que o número de elementos do array matriz for ímpar }
		}		
		return resposta;
	}	
	
	//retorna uma String com os elementos da matriz separados por um espaço em branco
	private static String addEspacamento(String[] matriz) {
		StringBuilder espacado = new StringBuilder();
		
		for(int cont = 0; cont < matriz.length; cont++) {
			espacado.append(matriz[cont]);
			//se houver mais elementos, adiciona espaço em branco
			if (cont < matriz.length - 1) {
				espacado.append(" ");
			}
		}
		return espacado.toString();
	}
	
	private static boolean saoDiferentes(String palavra, String palavraEmbaralhada) {
		//elimina os espaços em branco
		String p = palavra.replace(" ", "");
		String pEmb = palavraEmbaralhada.replace(" ", "");
		//efetua comparação
		if (pEmb.equalsIgnoreCase(p))
			return false;
		else
			return true;
	}	
		
	//método embaralhamento invertido (para matriz com até 2 elementos)
	private static String embInvert(String[] matriz) {
		//executa se matriz possui até 2 elementos
		if (matriz.length < 3) {
			/* imprime a matriz recebida para efeito de testes
			for (int cont = 0; cont < matriz.length; cont++) {
				System.out.print(matriz[cont]);
			}
			System.out.print(" - método embInvert"); */
			
			String palavraEmbaralhada = null;			
						
			//executa se matriz possui 2 elementos
			if (matriz.length == 2) {
				if (matriz[1] == null) {
					palavraEmbaralhada = matriz[0];
				} else {
					palavraEmbaralhada = matriz[1] + " " + matriz[0];
				}				
			} else {
			//executa se matriz possui 1 elemento
				palavraEmbaralhada = matriz[0];
			}
			
			/* imprime palavraEmbaralhada para efeito de testes
			System.out.println(" - " + palavraEmbaralhada); */
			return palavraEmbaralhada;
			
		} else {
		//executa se matriz possui mais de dois elementos
			return "erro: método embInvert só para até dois elementos!";
		}	
	}
	
	//método embaralhamento aleatório (para matriz com mais de dois elementos)
	private static String embAleat(String[] matriz) {
		//executa se matriz possui mais de dois elementos
		if (matriz.length > 2) {
			/* imprime a matriz recebida para efeito de testes
			for (int cont = 0; cont < matriz.length; cont++) {
				System.out.print(matriz[cont]);
			}
			System.out.print(" - método embAleat"); */			
			
			StringBuilder palavra = new StringBuilder();
			StringBuilder palavraEmbaralhada = new StringBuilder();
			
			//transforma o array matriz em String
			for(String str : matriz) {
				palavra.append(str);
			}
			
			//transforma o array matriz em ArrayList 
			ArrayList<String> lista = new ArrayList<String>();
			for (String elemento : matriz) {
					lista.add(elemento);
			}
			
			//enquanto houver elementos na lista
			while(!lista.isEmpty()) {
				//cria um número aleatório entre 0 e o maior índice da lista 
				int indiceAleat = (int) (Math.random() * lista.size());
					
				//adiciona o elemento correspondente ao índice aleatório à String de retorno
				palavraEmbaralhada.append(lista.get(indiceAleat));
				//remove da lista o elemento correspondente ao índice aleatório
				lista.remove(indiceAleat);
						
				//se houver mais elementos na lista
				if (!lista.isEmpty()) {
					//adiciona o espaço separador à String de retorno
					palavraEmbaralhada.append(" ");
				}
			} //fecha while
			
			if (saoDiferentes(palavra.toString(), palavraEmbaralhada.toString())) {
				/* imprime palavraEmbaralhada para efeito de testes
				System.out.println(" - " + palavraEmbaralhada.toString()); */
				return palavraEmbaralhada.toString();
			} else {
			//embaralhar novamente	
				return embAleat(matriz);   		
			}
			
		} else {
		//executa se matriz possui menos de três elementos
			return "erro: método embAleat só para mais de dois elementos!";
		}
	}
		
	protected static String embaralharMatriz(String[] matriz) {
		if (simetrica(matriz)) {  
			//não embaralha; embaralhar uma matriz simétrica com mais de dois elementos causa loop infinito
			return addEspacamento(matriz);
		} else if (matriz.length == 2) {
			//retorna embaralhamento invertido
			return embInvert(matriz);
		} else {
			//retorna embaralhamento aleatório
			return embAleat(matriz);
		}
	} //fim do método embaralhar
	
	public abstract String embaralharPalavra(String palavra);
}























