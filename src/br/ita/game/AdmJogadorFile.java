/* A classe AdmJogadorFile permite salvar, recuperar e obter listas de objetos Jogador salvos/serializados em arquivo.
 * Para evitar problemas de concorr�ncia, os m�todo p�blicos retransmitem as chamadas recebidas para o m�todo privado
 * sincronizado "crud". O m�todo crud, por sua vez, retransmite as chamadas recebidas para os m�todos privados que realmente 
 * executam as opera��es. O m�todo crud, como dito antes, � sicronizado. Ou seja, s� aceita uma chamada por vez. Desta forma, 
 * em uma vers�o futura que atenda a v�rios usu�rios simultaneamente (rodando em m�ltiplos segmentos) evitar-se-� problemas de
 * concorr�ncia, uma vez que todos atuar�o sobre o mesmo arquivo (jog.ser).
      
 	|--------------------------------------------------|----------------------------------------------------------------------------------|
 	|                                                  | m�todos p�blicos utilit�rios:                                                    |
 	|--------------------------------------------------|----------------------------------------------------------------------------------|
 	|                                                  |    boolean listaCompleta(ArrayList<Object> listObjDoArq)                         |
 	|									               |    ArrayList<Object> getListObjDoArq(File fileJog)                               |
 	|                                                  |    svListObjNoArq(ArrayList<Object> listObjDoArq, File fileJog)                  |
 	|                                                  |----------------------------------------------------------------------------------|
 	|                                                  | m�todo privado crud (sincronizado):                                              |
 	|                                                  |----------------------------------------------------------------------------------|
 	|                                                  |    synchronized Object crud(Operacao op, Jogador jogador, int id)                |
 	|--------------------------------------------------|----------------------------------------------------------------------------------|
 	| m�todos p�blicos (chamam o crud):                | m�todos privados que realizam as opera��es (s�o chamados pelo crud):             |
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
	 	obs: o primeiro objeto do arquivo � um objeto Integer que representa o �ltimo id utilizado */
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");
	
	//enumera��o - classifica as opera��es realizadas pelo m�todo crud 
	enum Operacao { SALVAR, EXCLUIR, RECUPERAR, LISTJOG, RANKJOG }
	
	
	
/*** m�todos p�blicos dispon�veis ***/
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
	
	
	
/*** m�todo crud (sicronizado) ***/	
	private static synchronized Object crud(Operacao op, Jogador jogador, int id) throws Exception {
	//private static Object crud(Operacao op, Jogador jogador, int id) throws Exception { //vers�o n�o sincronizada para testes
		ArrayList<Object> listObjDoArq = getListObjDoArq(fileJog); 
		if (!listaCompleta(listObjDoArq)) throw new Exception("M�todo AdmJogadorFile.crud: Lista de objetos do arquivo incompleta!");
		//obs: m�todos svObjJog, deleteObjJog, getObjJog, getListaJog e getRankingJog s� podem operar com lista completa
		
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
			throw new Exception("M�todo AdmJogadorFile.crud: Par�metro op n�o possui m�todo correspondente!");
		}
		return null;
	} //fim do m�todo crud
	
	
	
/*** m�todos p�blicos utilit�rio - ser�o utilizados internamente pelos m�todos privados que realizam as tarefas ***/
	//listaCompleta: retorna true se a lista n�o � nula e possui na posi��o 0 o objeto Integer que representa o �ltimo id utilizado
	public static boolean listaCompleta(ArrayList<Object> listObjDoArq) {
		if (listObjDoArq != null && !listObjDoArq.isEmpty() && listObjDoArq.get(0) instanceof Integer) {
			return true;
		} else {
			return false;
		}
	}
		
	//getListObjDoArq: retorna uma lista de objetos salvos/serializados no arquivo
	//obs: caso o arquivo esteja vazio, getListObjDoArq cria na posi��o 0 da lista um objeto Integer que representa o �ltimo id utilizado
	@SuppressWarnings("deprecation")
	public static ArrayList<Object> getListObjDoArq(File fileJog) throws Exception {
		if (fileJog == null) throw new Exception("M�todo AdmJogadorFile.getListObjDoArq: Par�metro fileJog null!");
		
		ArrayList<Object> listObjDoArq = new ArrayList<Object>(); //lista de retorno
		FileInputStream fileIn = null;    //fluxo de conex�o
		ObjectInputStream objectIn = null; //fluxo de cadeia

		try {
			//cria o fluxo de conex�o: lan�a: FileNotFoundException, SecurityException
				//FileNotFoundException: se arquivo n�o existe; se existe mas � um diret�rio; se n�o pode ser aberto para leitura
			fileIn = new FileInputStream(fileJog);
			
			//cria o fluxo de cadeia: lan�a: EOFException, StreamCorruptedException (subclasses de IOException)
				//EOFException: se o arquivo estiver completamente vazio
				//StreamCorruptedException: se o conte�do do arquivo n�o for de objetos serializados (for texto comum, por exemplo)
			objectIn = new ObjectInputStream(fileIn);
			
		} catch (FileNotFoundException ex) {
			throw new Exception("M�todo AdmJogadorFile.getListObjDoArq: Arquivo " + fileJog.getName() + " n�o encontrado ou protegido contra leitura!");
		} catch (SecurityException ex) {
			throw new Exception("M�todo AdmJogadorFile.getListObjDoArq: Arquivo " + fileJog.getName() + " protegido pelo security manager!");
		} catch (IOException ex) {
			//adiciona na lista objeto que representa �ltimo id utilizado
			listObjDoArq.add(new Integer(0));
			//fecha fluxo de conex�o
			fileIn.close();
			//retorna lista
			return listObjDoArq;
		}
		
		//l� arquivo
		try {
			//readObject() lan�a EOFException (subclasse de IOException) quando n�o houver mais objetos � serem lidos
			Object obj = null;			
			while ((obj = objectIn.readObject()) != null) {
				//adiciona objeto lido � lista
				listObjDoArq.add(obj);
			}
		} catch (IOException ex) {
			//fecha fluxo de conex�o e de cadeia
			fileIn.close();    
			objectIn.close();  
		}
		
		return listObjDoArq;
	} //fim do m�todo getListObjDoArq
		
	//svListObjNoArq: serializa uma lista de objetos no arquivo
	public static void svListObjNoArq(ArrayList<Object> listObjDoArq, File fileJog) throws Exception {
		if (fileJog == null) throw new Exception("M�todo AdmJogadorFile.svListObjNoArq: Par�metro fileJog null!");
		if (!listaCompleta(listObjDoArq)) throw new Exception("M�todo AdmJogadorFile.svListObjNoArq: Lista de objetos a ser salva no arquivo incompleta!");
		
		FileOutputStream fileOut = null; //fluxo de conex�o
		ObjectOutputStream objectOut = null; //fluxo de cadeia
		
		try {
			//cria o fluxo de conex�o: lan�a FileNotFoundException, SecurityException
				//FileNotFoundException: se fileJog existe mas � um diret�rio; se � um arquivo mas n�o pode ser aberto; se n�o existe e n�o pode ser criado
			fileOut = new FileOutputStream(fileJog);
			
			//cria o fluxo de cadeia: lan�a IOException if an I/O error occurs while writing stream header  
			objectOut = new ObjectOutputStream(fileOut);
					
			//grava objetos no arquivo: writeObject(obj) lan�a IOException if any exception thrown by the underlying OutputStream 
			for (Object obj : listObjDoArq) { 
				objectOut.writeObject(obj);
			}
		} catch (FileNotFoundException ex) {
			throw new Exception("M�todo AdmJogadorFile.svListObjNoArq: Erro ao abrir arquivo " + fileJog.getName() + "!");
		} catch (SecurityException ex) {
			throw new Exception("M�todo AdmJogadorFile.svListObjNoArq: Arquivo " + fileJog.getName() + " protegido pelo security manager!");
		} catch (IOException ex) {
			throw new Exception("M�todo AdmJogadorFile.svListObjNoArq: Erro ao gravar dados no arquivo " + fileJog.getName() + "!");
		} finally {
			//fecha os fluxos	
			try {
				//fecha fluxo de conex�o
				if (fileOut != null) { fileOut.close(); }	
				//fecha fluxo de cadeia
				if (objectOut != null) { objectOut.close(); }							
			} catch (IOException ex) { System.out.println(ex.getMessage()); }
		} //fecha finaly
	} //fim do m�todo svListObjNoArq
	
	
	
/*** m�todos privados que realizam as tarefas ***/	
	//svObjJog: adiciona jogador � lista e chama o m�todo svListObjNoArq
	private static void svObjJog(ArrayList<Object> listObjDoArq, Jogador jogador) throws Exception {
		//recupera �ltimo id utilizado
		Integer ultIdUt = (Integer) listObjDoArq.get(0);

		//remove �ltimo id utilizado da lista
		listObjDoArq.remove(0);
			
		//se jogador j� pertence � lista
		if (listObjDoArq.contains(jogador)) {				
			//obt�m a posi��o do jogador na lista
			int index = listObjDoArq.indexOf(jogador);
			//apaga jogador da lista
			listObjDoArq.remove(index);
			//adiciona par�metro jogador � lista
			listObjDoArq.add(index, jogador);					
			
		//sen�o, jogador foi rec�m criado e est� sendo salvo em disco pela primeria vez
		} else {				
			//atualiza �ltimo id utilizado
			ultIdUt++;
			//atribui �ltimo id utilizado ao novo objeto Jogador
			jogador.setId(ultIdUt);				
			//adiciona jogador ao fim da lista
			listObjDoArq.add(jogador);
		}
		
		//reinsere �ltimo id utilizado � lista
		listObjDoArq.add(0, ultIdUt);
			
		//salva lista de objetos no arquivo
		svListObjNoArq(listObjDoArq, fileJog);
	} //fim do m�todo svObjJog
	
	//deleteObjJog: remove jogador da lista e chama o m�todo svListObjNoArq
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
	} //fim do m�todo deleteObjJog

	//getObjJog: 
		//retorna um objeto Jogador pertencente � lista 
		//retorna null caso o objeto Jogador n�o perten�a � lista
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
	} //fim  do m�todo getObjJog

	//getListaJog: retorna uma lista contendo id, nome e apelido dos jogadores pertencentes � lista
	private static String[] getListaJog(ArrayList<Object> listObjDoArq) throws Exception {
		String[] matrizRet = new String[listObjDoArq.size() -1]; //-1 porque o primeiro objeto da lista n�o � Jogador

		//percorre a lista de objetos do arquivo
		for (int cont = 1; cont < listObjDoArq.size(); cont++) {
			Jogador jog = (Jogador) listObjDoArq.get(cont);
			//organiza as informa��es em formato de coluna
			String colunaId = getFormatoColuna(jog.getId(), 4, null); //4 = n�mero de d�gitos
			String colunaNome = jog.getNome();
			String colunaApelido = "(" + jog.getApelido() + ")";
			//abastece a lista de retorno
			matrizRet[cont - 1] = colunaId + " - " + colunaNome + " " + colunaApelido;
		}	
		
		return matrizRet;
	} //fim do m�todo getListaJog
	
	//getRankingJog: retorna uma lista contendo os dados dos jogadores pertencentes � lista, classificados pelo n�mero de pontos ganhos
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
			//organiza as informa��es em formato de coluna
			String colunaOrdem = getFormatoColuna(cont + 1, 4, "�(�)"); //4 = n�mero de d�gitos
			String colunaNome = getFormatoColuna(25, jog.getNome()); //25 = largura da coluna
			String colunaPontos = getFormatoColuna(jog.getPontos(), 6, " pt(s)");
			String colunaPartidas = getFormatoColuna(jog.getPartidas(), 4, " partida(s)");
			String colunaVitorias = getFormatoColuna(jog.getVitorias(), 4, " vit�ria(s)");
			
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
	} //fim do m�todo getRankingJog
	
	private static String getFormatoColuna(int valor, int nDigitos, String texto) {
		StringBuilder coluna = new StringBuilder();
		if (texto != null)
			coluna.insert(0, texto);
		coluna.insert(0, valor);
		
		//acrescenta espa�os em branco no in�cio de acordo com o n�mero de d�gitos
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
			//adiciona espa�os em branco at� chegar em size
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