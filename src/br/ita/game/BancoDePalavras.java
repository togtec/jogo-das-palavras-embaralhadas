/* A classe BancoDePalavras:
 * 		-l� as palavras do arquivo �palavras.txt�
 * 		-transform�-as em objetos Palavra
 * 		-armazen�-os em uma lista
 * 		-retorna uma lista aleat�ria de objetos Palavra */

package br.ita.game;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BancoDePalavras {
	private static final File file = new File("src/br/ita/game/files/palavras.txt");
	
	//cria um objeto palavra com o conte�do de uma linha do arquivo
	private static Palavra criarPalavra(String line) {
		//divide a linha do arquivo em tr�s peda�os: s�labas, valor da palavra e dica
		String[] aux = line.split("#");
		//cria e retorna um objeto Palavra
		return new Palavra(aux[0], Integer.parseInt(aux[1]), aux[2].replace("@", "\n"));
	}
	
	//l� o arquivo palavras.txt; transforma cada linha do arquivo em um objeto palavra; armazena-os em uma lista
	private static ArrayList<Palavra> getPalavrasDoArquivo() {
		//cria a lista de retorno
		ArrayList<Palavra> listaPalavrasDoArquivo = new ArrayList<Palavra>();
				
		try {
			//cria um fluxo de cadeia encadeado a um fluxo de conex�o que l� caracteres e n�o bytes
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			//String que armazena a linha lida do arquivo
			String line = null;

			//enquanto houver linhas a serem lidas
			while ((line = reader.readLine()) != null) {
				//transforma a linha lida do arquivo em um objeto Palavra
				Palavra palavra = criarPalavra(line);
				//adicion�-o � lista
				listaPalavrasDoArquivo.add(palavra);
			}
		
			//fecha o fluxo de entrada
			reader.close();
		} catch(FileNotFoundException ex) {
			System.out.println("Erro: arquivo palavras.txt n�o encontrado!");
		} catch(IOException ex) {
			System.out.println("Erro: erro de leitura no arquivo palavras.txt");
		}
			
		return listaPalavrasDoArquivo;		
	} //fecha o m�todo getPalavrasDoArquivo
	
	public static ArrayList<Palavra> getListaPalavrasAleat(int qtdPalavras) {
		//obt�m a lista de palavras do arquivo
		ArrayList<Palavra> listaPalavrasDoArquivo = getPalavrasDoArquivo();
		//cria a lista de retorno
		ArrayList<Palavra> listaPalavrasAleat = new ArrayList<Palavra>();
		
		/* ajusta a quantidade de palavras a ser retornada */		
		//se a quantidade de palavras a ser retornada for maior que a quantidade de palavras do arquivo
		if (qtdPalavras > listaPalavrasDoArquivo.size()) 		
			qtdPalavras = listaPalavrasDoArquivo.size();
		//se a quantidade de palavras a ser retornada for negativa ou zero
		else if (qtdPalavras <= 0)
			qtdPalavras = 1;
		
		//adiciona as palavras � lista de retorno
		for (int cont = 0; cont < qtdPalavras; cont++) {
			//cria um n�mero aleat�rio entre 0 e a quantidade de palavras dispon�veis
			int indiceAleat = (int) (Math.random() * listaPalavrasDoArquivo.size());
			//adiciona a palavra correspondente ao �ndice aleat�rio � lista de retorno
			listaPalavrasAleat.add(listaPalavrasDoArquivo.get(indiceAleat));
			//remove a palavra rec�m adicionada da lista de palavras do arquivo
			listaPalavrasDoArquivo.remove(indiceAleat);
		}
		
		return listaPalavrasAleat;		
	} //fim do m�todo getListaPalavrasAleat
}
