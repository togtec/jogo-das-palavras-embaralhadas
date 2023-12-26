/* A classe AdmJogadorFile permite salvar, recuperar e obter listas de objetos Jogador salvos/serializados em arquivo.
 * Para evitar problemas de concorrência, os método públicos retransmitem as chamadas recebidas para o método privado
 * sincronizado "crud". O método crud, por sua vez, retransmite as chamadas recebidas para os métodos privados que realmente 
 * executam as operações. O método crud, como dito antes, é sicronizado. Ou seja, só aceita uma chamada por vez. Desta forma, 
 * em uma versão futura que atenda a vários usuários simultaneamente (rodando em múltiplos segmentos) evitar-se-á problemas de
 * concorrência, uma vez que todos atuarão sobre o mesmo arquivo (jog.ser).
      
 	|--------------------------------------------------|----------------------------------------------------------------------------------|
 	|                                                  | métodos públicos utilitários:                                                    |
 	|--------------------------------------------------|----------------------------------------------------------------------------------|
 	|                                                  |    boolean listaCompleta(ArrayList<Object> listObjDoArq)                         |
 	|									               |    ArrayList<Object> getListObjDoArq(File fileJog)                               |
 	|                                                  |    svListObjNoArq(ArrayList<Object> listObjDoArq, File fileJog)                  |
 	|                                                  |----------------------------------------------------------------------------------|
 	|                                                  | método privado crud (sincronizado):                                              |
 	|                                                  |----------------------------------------------------------------------------------|
 	|                                                  |    synchronized Object crud(Operacao op, Jogador jogador, int id)                |
 	|--------------------------------------------------|----------------------------------------------------------------------------------|
 	| métodos públicos (chamam o crud):                | métodos privados que realizam as operações (são chamados pelo crud):             |
 	|--------------------------------------------------|----------------------------------------------------------------------------------|
 	|    void salvar(Jogador jogador)                  |    void svObjJog(ArrayList<Object> listObjDoArq, Jogador jogador)                |
 	|    void excluir(Jogador jogador)                 |    void deleteObjJog(ArrayList<Object> listObjDoArq, Jogador jogador)            |
 	|    Jogador getJogador(int id)                    |    Jogador getObjJog(ArrayList<Object> listObjDoArq, int id)                     |
 	|    String[] getListaJogadores()                  |    String[] getListaJog(ArrayList<Object> listObjDoArq)                          |
 	|    String[] getRankingJogadores(Jogador jogador) |    String[] getRankingJog(ArrayList<Object> listObjDoArq, Jogador jogador)       |
 	|--------------------------------------------------|----------------------------------------------------------------------------------| */

package br.ita.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import br.ita.game.tela.TelaNav;

public class AdmJogadorFile {
	/* objeto que representa o arquivo
	 	obs: o primeiro objeto do arquivo é um objeto Integer que representa o último id utilizado */
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");
	
	//enumeração - classifica as operações realizadas pelo método crud 
	enum Operacao { SALVAR, EXCLUIR, RECUPERAR, LISTJOG, RANKJOG }
	
	
	
/*** métodos públicos disponíveis ***/
	public static void salvar(Jogador jogador) throws Exception {
		crud(Operacao.SALVAR, jogador, -1);
	} 
	public static void excluir(Jogador jogador) throws Exception {
		crud(Operacao.EXCLUIR, jogador, -1);
	}
	public static Jogador getJogador(int id) throws Exception {
		return (Jogador) crud(Operacao.RECUPERAR, null, id);
	}
	public static String[] getListaJogadores() throws Exception {
		return (String[]) crud(Operacao.LISTJOG, null, -1);
	}
	public static String[] getRankingJogadores(Jogador jogador) throws Exception {
		return (String[]) crud(Operacao.RANKJOG, jogador, -1);
	}
	
	
	
/*** método crud (sicronizado) ***/	
	private static synchronized Object crud(Operacao op, Jogador jogador, int id) throws Exception {
	//private static Object crud(Operacao op, Jogador jogador, int id) throws Exception { //versão não sincronizada para testes
		ArrayList<Object> listObjDoArq = getListObjDoArq(fileJog); 
		if (!listaCompleta(listObjDoArq)) throw new Exception("Método AdmJogadorFile.crud: Lista de objetos do arquivo incompleta!");
		//obs: métodos svObjJog, deleteObjJog, getObjJog, getListaJog e getRankingJog só podem operar com lista completa
		
		if (op == Operacao.SALVAR) {
			svObjJog(listObjDoArq, jogador);
		} else if (op == Operacao.EXCLUIR) {
			deleteObjJog(listObjDoArq, jogador);
		} else if (op == Operacao.RECUPERAR) {
			return getObjJog(listObjDoArq, id);
		} else if(op == Operacao.LISTJOG) {
			return getListaJog(listObjDoArq);
		} else if(op == Operacao.RANKJOG) {
			return getRankingJog(listObjDoArq, jogador);
		} else {
			throw new Exception("Método AdmJogadorFile.crud: Parâmetro op não possui método correspondente!");
		}
		return null;
	} //fim do método crud
	
	
	
/*** métodos públicos utilitário - serão utilizados internamente pelos métodos privados que realizam as tarefas ***/
	//listaCompleta: retorna true se a lista não é nula e possui na posição 0 o objeto Integer que representa o último id utilizado
	public static boolean listaCompleta(ArrayList<Object> listObjDoArq) {
		if (listObjDoArq != null && !listObjDoArq.isEmpty() && listObjDoArq.get(0) instanceof Integer) {
			return true;
		} else {
			return false;
		}
	}
		
	//getListObjDoArq: retorna uma lista de objetos salvos/serializados no arquivo
	//obs: caso o arquivo esteja vazio, getListObjDoArq cria na posição 0 da lista um objeto Integer que representa o último id utilizado
	@SuppressWarnings("deprecation")
	public static ArrayList<Object> getListObjDoArq(File fileJog) throws Exception {
		if (fileJog == null) throw new Exception("Método AdmJogadorFile.getListObjDoArq: Parâmetro fileJog null!");
		
		ArrayList<Object> listObjDoArq = new ArrayList<Object>(); //lista de retorno
		FileInputStream fileIn = null;    //fluxo de conexão
		ObjectInputStream objectIn = null; //fluxo de cadeia

		try {
			//cria o fluxo de conexão: lança: FileNotFoundException, SecurityException
				//FileNotFoundException: se arquivo não existe; se existe mas é um diretório; se não pode ser aberto para leitura
			fileIn = new FileInputStream(fileJog);
			
			//cria o fluxo de cadeia: lança: EOFException, StreamCorruptedException (subclasses de IOException)
				//EOFException: se o arquivo estiver completamente vazio
				//StreamCorruptedException: se o conteúdo do arquivo não for de objetos serializados (for texto comum, por exemplo)
			objectIn = new ObjectInputStream(fileIn);
			
		} catch (FileNotFoundException ex) {
			throw new Exception("Método AdmJogadorFile.getListObjDoArq: Arquivo " + fileJog.getName() + " não encontrado ou protegido contra leitura!");
		} catch (SecurityException ex) {
			throw new Exception("Método AdmJogadorFile.getListObjDoArq: Arquivo " + fileJog.getName() + " protegido pelo security manager!");
		} catch (IOException ex) {
			//adiciona na lista objeto que representa último id utilizado
			listObjDoArq.add(new Integer(0));
			//fecha fluxo de conexão
			fileIn.close();
			//retorna lista
			return listObjDoArq;
		}
		
		//lê arquivo
		try {
			//readObject() lança EOFException (subclasse de IOException) quando não houver mais objetos à serem lidos
			Object obj = null;			
			while ((obj = objectIn.readObject()) != null) {
				//adiciona objeto lido à lista
				listObjDoArq.add(obj);
			}
		} catch (IOException ex) {
			//fecha fluxo de conexão e de cadeia
			fileIn.close();    
			objectIn.close();  
		}
		
		return listObjDoArq;
	} //fim do método getListObjDoArq
		
	//svListObjNoArq: serializa uma lista de objetos no arquivo
	public static void svListObjNoArq(ArrayList<Object> listObjDoArq, File fileJog) throws Exception {
		if (fileJog == null) throw new Exception("Método AdmJogadorFile.svListObjNoArq: Parâmetro fileJog null!");
		if (!listaCompleta(listObjDoArq)) throw new Exception("Método AdmJogadorFile.svListObjNoArq: Lista de objetos a ser salva no arquivo incompleta!");
		
		FileOutputStream fileOut = null; //fluxo de conexão
		ObjectOutputStream objectOut = null; //fluxo de cadeia
		
		try {
			//cria o fluxo de conexão: lança FileNotFoundException, SecurityException
				//FileNotFoundException: se fileJog existe mas é um diretório; se é um arquivo mas não pode ser aberto; se não existe e não pode ser criado
			fileOut = new FileOutputStream(fileJog);
			
			//cria o fluxo de cadeia: lança IOException if an I/O error occurs while writing stream header  
			objectOut = new ObjectOutputStream(fileOut);
					
			//grava objetos no arquivo: writeObject(obj) lança IOException if any exception thrown by the underlying OutputStream 
			for (Object obj : listObjDoArq) { 
				objectOut.writeObject(obj);
			}
		} catch (FileNotFoundException ex) {
			throw new Exception("Método AdmJogadorFile.svListObjNoArq: Erro ao abrir arquivo " + fileJog.getName() + "!");
		} catch (SecurityException ex) {
			throw new Exception("Método AdmJogadorFile.svListObjNoArq: Arquivo " + fileJog.getName() + " protegido pelo security manager!");
		} catch (IOException ex) {
			throw new Exception("Método AdmJogadorFile.svListObjNoArq: Erro ao gravar dados no arquivo " + fileJog.getName() + "!");
		} finally {
			//fecha os fluxos	
			try {
				//fecha fluxo de conexão
				if (fileOut != null) { fileOut.close(); }	
				//fecha fluxo de cadeia
				if (objectOut != null) { objectOut.close(); }							
			} catch (IOException ex) { System.out.println(ex.getMessage()); }
		} //fecha finaly
	} //fim do método svListObjNoArq
	
	
	
/*** métodos privados que realizam as tarefas ***/	
	//svObjJog: adiciona jogador à lista e chama o método svListObjNoArq
	private static void svObjJog(ArrayList<Object> listObjDoArq, Jogador jogador) throws Exception {
		//recupera último id utilizado
		Integer ultIdUt = (Integer) listObjDoArq.get(0);

		//remove último id utilizado da lista
		listObjDoArq.remove(0);
			
		//se jogador já pertence à lista
		if (listObjDoArq.contains(jogador)) {				
			//obtém a posição do jogador na lista
			int index = listObjDoArq.indexOf(jogador);
			//apaga jogador da lista
			listObjDoArq.remove(index);
			//adiciona parâmetro jogador à lista
			listObjDoArq.add(index, jogador);					
			
		//senão, jogador foi recém criado e está sendo salvo em disco pela primeria vez
		} else {				
			//atualiza último id utilizado
			ultIdUt++;
			//atribui último id utilizado ao novo objeto Jogador
			jogador.setId(ultIdUt);				
			//adiciona jogador ao fim da lista
			listObjDoArq.add(jogador);
		}
		
		//reinsere último id utilizado à lista
		listObjDoArq.add(0, ultIdUt);
			
		//salva lista de objetos no arquivo
		svListObjNoArq(listObjDoArq, fileJog);
	} //fim do método svObjJog
	
	//deleteObjJog: remove jogador da lista e chama o método svListObjNoArq
	private static void deleteObjJog(ArrayList<Object> listObjDoArq, Jogador jogador) throws Exception {
		//percorre a lista
		for (int cont = 1; cont < listObjDoArq.size(); cont++) {
			if (listObjDoArq.get(cont).equals(jogador)) {
				//remove jogador
				listObjDoArq.remove(cont);
				//salva lista
				svListObjNoArq(listObjDoArq, fileJog);
				//interrompe o for
				break;
			}
		}
	} //fim do método deleteObjJog

	//getObjJog: 
		//retorna um objeto Jogador pertencente à lista 
		//retorna null caso o objeto Jogador não pertença à lista
	private static Jogador getObjJog(ArrayList<Object> listObjDoArq, int id) throws Exception {
		//percorre a lista
		for (int cont = 1; cont < listObjDoArq.size(); cont++) {
			Jogador jog = (Jogador) listObjDoArq.get(cont);
			//se jogador corresponde ao objeto procurado
			if (jog.getId() == id) {
				//retorna jogador
				return jog;
			}
		} //fecha for		

		return null;
	} //fim  do método getObjJog

	//getListaJog: retorna uma lista contendo id, nome e apelido dos jogadores pertencentes à lista
	private static String[] getListaJog(ArrayList<Object> listObjDoArq) throws Exception {
		String[] matrizRet = new String[listObjDoArq.size() -1]; //-1 porque o primeiro objeto da lista não é Jogador

		//percorre a lista de objetos do arquivo
		for (int cont = 1; cont < listObjDoArq.size(); cont++) {
			Jogador jog = (Jogador) listObjDoArq.get(cont);
			//organiza as informações em formato de coluna
			String colunaId = getFormatoColuna(jog.getId(), 4, null); //4 = número de dígitos
			String colunaNome = jog.getNome();
			String colunaApelido = "(" + jog.getApelido() + ")";
			//abastece a lista de retorno
			matrizRet[cont - 1] = colunaId + " - " + colunaNome + " " + colunaApelido;
		}	
		
		return matrizRet;
	} //fim do método getListaJog
	
	//getRankingJog: retorna uma lista contendo os dados dos jogadores pertencentes à lista, classificados pelo número de pontos ganhos
	private static String[] getRankingJog(ArrayList<Object> listObjDoArq, Jogador jogador) throws Exception {
		ArrayList<Jogador> rankingJogadores = new ArrayList<Jogador>();			

		//converte a lista de ArrayList<Object> para ArrayList<Jogador>
		for (int cont = 1; cont < listObjDoArq.size(); cont++) {
			rankingJogadores.add( (Jogador) listObjDoArq.get(cont) );
		}

		//classifica pela quantidade de pontos marcados
		Collections.sort(rankingJogadores, new ComparaJogadorPontos());		

		//cria a matriz de retorno
		String[] matrizRet = new String[rankingJogadores.size()];
		
		//percorre a lista de jogadores
		int cont = 0;
		for (Jogador jog : rankingJogadores) {
			//organiza as informações em formato de coluna
			String colunaOrdem = getFormatoColuna(cont + 1, 4, "º(ª)"); //4 = número de dígitos
			String colunaNome = getFormatoColuna(25, jog.getNome()); //25 = largura da coluna
			String colunaPontos = getFormatoColuna(jog.getPontos(), 6, " pt(s)");
			String colunaPartidas = getFormatoColuna(jog.getPartidas(), 4, " partida(s)");
			String colunaVitorias = getFormatoColuna(jog.getVitorias(), 4, " vitória(s)");
			
			if (jogador != null && jogador.equals(jog)) {
				//abastece a lista de retorno com as colunas (identificando o jogador)
				matrizRet[cont] = colunaOrdem + " <-- " + 
						          colunaNome + TelaNav.margemEsquerda +
						          colunaPontos + " <-- " + 
						          colunaPartidas + " <-- " + 
						          colunaVitorias + " <-- ";
				
			} else {
				//abastece a lista de retorno com as colunas (sem identificar jogador)
				matrizRet[cont] = colunaOrdem +
				TelaNav.margemEsquerda + colunaNome + 
				TelaNav.margemEsquerda + colunaPontos + 
				TelaNav.margemEsquerda + colunaPartidas + 
				TelaNav.margemEsquerda + colunaVitorias;
			}
			//incrementa contador
			cont++;			
		}
		
		return matrizRet;
	} //fim do método getRankingJog
	
	private static String getFormatoColuna(int valor, int nDigitos, String texto) {
		StringBuilder coluna = new StringBuilder();
		if (texto != null)
			coluna.insert(0, texto);
		coluna.insert(0, valor);
		
		//acrescenta espaços em branco no início de acordo com o número de dígitos
		int valorSize = Integer.toString(valor).length();		
		if (valorSize < nDigitos) {
			for (; valorSize < nDigitos; valorSize++) {
				coluna.insert(0, " ");
			}
			//insere marcador (apenas para efeito de teste)
			//coluna.insert(0, "|");
		}
		
		return coluna.toString();
	}	
	private static String getFormatoColuna(int size, String texto) {
		if (texto.length() < size) {
			//adiciona espaços em branco até chegar em size
			StringBuilder coluna = new StringBuilder(texto);
			for (int cont = texto.length(); cont < size; cont++) {
				coluna.append(" ");
			}
			return coluna.toString();			
		
		} else if (texto.length() == size) {
			return texto;		
		
		} else {
			return texto.substring(0, size);
		}
	}	
}