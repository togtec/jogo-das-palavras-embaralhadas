/*** A classe TestaAdmJogadorFile1 testa os m�todos p�blicos:
	  	listaCompleta
	 	getListObjDoArq
	 	svListObjNoArq

		salvar
		excluir
		getJogador
		getListaJogadores
		getRankingJogadores ***/

package br.ita.game;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.ita.game.enu.PartidaStatus;

class TestaAdmJogadorFile1 {
	//objeto que representa o arquivo inexistente (obs: este arquivo n�o existe e nem ser� criado em disco)
	private static final File fileInexist = new File("teste/br/ita/game/files/000_inexist.ser");
	//o arquivo completamente vazio (somente leitura) n�o possui objeto Integer (que representa o �ltimo id utilizado) nem objetos Jogador
	private static final File fileCompVazio = new File("teste/br/ita/game/files/001_compVazio.ser");
	//o arquivo zero jogador (somente leitura) possui o objeto Integer (que representa o �ltimo id utilizado) mas n�o possui objetos Jogador
	private static final File fileZeroJog = new File("teste/br/ita/game/files/002_zeroJog.ser");
	//o arquivo um jogador (somente leitura) possui um objeto Integer (que representa o �ltimo id utilizado) e um objeto Jogador
	private static final File fileUmJog = new File("teste/br/ita/game/files/003_umJog.ser");
	//o arquivo sete jogadores (somente leitura) possui um objeto Integer (que representa o �ltimo id utilizado) e sete objetos Jogador
	private static final File fileSeteJog = new File("teste/br/ita/game/files/004_seteJog.ser");
	//ao contr�rio dos anteriores, o arquivo lista gravada n�o � protegido contra grava��o. Pode ser utilizado para salavar uma lista de objetos 
	private static final File fileListGrav = new File("teste/br/ita/game/files/005_listGravada.ser");
	//arquivo protegido contra leitura (na caixa de di�logo propriedades do arquivo do windows)
	private static final File fileProtLeit = new File("teste/br/ita/game/files/007_protContraLeit.ser");
	//arquivo protegido contra grava��o (na caixa de di�logo propriedades do arquivo do windows)
	private static final File fileProtGrav = new File("teste/br/ita/game/files/008_protContraGrav.ser");	
	//arquivo de produ��o
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");
	
	private static ArrayList<Object> listaNula;
	private static ArrayList<Object> listaVazia;
	private static ArrayList<Object> listaIncompletaUmJog;
	private static ArrayList<Object> listaIncompletaSeteJog;
	
	private static ArrayList<Object> listaValidaZeroJog;
	private static ArrayList<Object> listaValidaUmJog;
	private static ArrayList<Object> listaValidaSeteJog;
	
	
	
/*** m�todos utilit�rios (n�o s�o m�todos de teste) ***/	
	//Copia arquivo desejado, para o arquivo de destino
	//Se o arquivo de destino n�o existir, ele ser� criado
	//fonte: https://www.devmedia.com.br/aprenda-a-copiar-arquivos-utilizando-o-java/1209
	void copy(File src, File dst) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);  // Transferindo bytes de entrada para sa�da
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}	
	//imprime uma lista de objetos (obs: m�todo sobrecarregado)
	void imprimir(ArrayList<Object> lista) {
		if (!lista.isEmpty()) {
			for (int cont = 0; cont < lista.size(); cont++) {
				if (lista.get(cont) instanceof Integer) {
					System.out.println("\t �ltimo id utilizado: " + lista.get(cont));
				} else {
					//converte de Object para Jogador
					Jogador jog = (Jogador) lista.get(cont);
					System.out.println("\t "+ jog.getId() + " " + jog.getNome() + " - " + jog.getApelido());
				}
			}			
		}
	}
	//imprime uma lista de jogadores
	void imprimir(String[] lista) {
		for(String jog : lista) {
			System.out.println("\t" + jog);
		}
	}
	
		
	
/*** prepara o ambiente de testes ***/	
	@BeforeAll
	static void preparaListas() throws Exception {		
		listaNula = null;
		listaVazia = new ArrayList<Object>();
		listaIncompletaUmJog = AdmJogadorFile.getListObjDoArq(fileUmJog);
			listaIncompletaUmJog.remove(0);
		listaIncompletaSeteJog = AdmJogadorFile.getListObjDoArq(fileSeteJog);
			listaIncompletaSeteJog.remove(0);
			
		listaValidaZeroJog = AdmJogadorFile.getListObjDoArq(fileZeroJog);
		listaValidaUmJog = AdmJogadorFile.getListObjDoArq(fileUmJog);
		listaValidaSeteJog = AdmJogadorFile.getListObjDoArq(fileSeteJog);
	}
	
	@Test
	void testaPreparaListas() {
		assertNull(listaNula); 
		System.out.println("listaNula: " + listaNula);

		assertTrue(listaVazia.isEmpty());
		System.out.println("\n" + "listaVazia: " + listaVazia);
		
		assertEquals(1, listaIncompletaUmJog.size());
		System.out.println("\n" + "listaIncompletaUmJog:");
		imprimir(listaIncompletaUmJog);
		
		assertEquals(7, listaIncompletaSeteJog.size());
		System.out.println("\n" + "listaIncompletaSeteJog:");
		imprimir(listaIncompletaSeteJog);
		
		assertEquals(1, listaValidaZeroJog.size());
		System.out.println("\n" + "listaValidaZeroJog");
		imprimir(listaValidaZeroJog);
		
		assertEquals(2, listaValidaUmJog.size());
		System.out.println("\n" + "listaValidaUmJog");
		imprimir(listaValidaUmJog);
		
		assertEquals(8, listaValidaSeteJog.size());
		System.out.println("\n" + "listaValidaSeteJog");
		imprimir(listaValidaSeteJog);
	}
	
	
	
/*** testes do m�todo listaCompleta:
   		retorna true se a lista n�o � nula e possui na posi��o 0 o objeto Integer que representa o �ltimo id utilizado ***/
	@Test
	void listaCompleta() {
		assertFalse(AdmJogadorFile.listaCompleta(listaNula));
		assertFalse(AdmJogadorFile.listaCompleta(listaVazia));
		assertFalse(AdmJogadorFile.listaCompleta(listaIncompletaUmJog));
		assertFalse(AdmJogadorFile.listaCompleta(listaIncompletaSeteJog));
		
		assertTrue(AdmJogadorFile.listaCompleta(listaValidaZeroJog));
		assertTrue(AdmJogadorFile.listaCompleta(listaValidaUmJog));
		assertTrue(AdmJogadorFile.listaCompleta(listaValidaSeteJog));
	}
	
	
	
/*** testes do m�todo getListObjDoArq: 
 		retorna uma lista de objetos salvos/serializados no arquivo 
		obs: caso o arquivo esteja vazio, getListObjDoArq cria na posi��o 0 da lista um objeto Integer que representa o �ltimo id utilizado ***/
	@Test
	void getListObjDoArq() throws Exception {
		//lan�a exce��o ao tentar ler um arquivo null
		Exception excecaoFileNull = assertThrows(Exception.class, () -> AdmJogadorFile.getListObjDoArq(null));
		System.out.println("excecaoFileNull: " + excecaoFileNull.getMessage());
		
		//lan�a exce��o ao tentar ler um arquivo inexistente
		Exception excecaoFileInexistente = assertThrows(Exception.class, () -> AdmJogadorFile.getListObjDoArq(fileInexist));
		System.out.println("\n" + "excecaoFileInexistente: " + excecaoFileInexistente.getMessage());
		
		//lan�a exce��o ao tentar ler um arquivo protegido contra leitura
		//obs: ap�s ser criado manualmente, o arquivo foi protegido contra leitura atrav�s da caixa de di�logo propriedades do arquivo do windows
		Exception exFileProtLeit = assertThrows(Exception.class, () -> AdmJogadorFile.getListObjDoArq(fileProtLeit));
		System.out.println("\n" + "exFileProtLeit: " + exFileProtLeit.getMessage());
		
		//neste teste o m�todo getListObjDoArq acrescenta � lista um objeto Integer que representa o �ltimo id utilizado 
		//ao ler um arquivo completamente vazio
		ArrayList<Object> listaArqVazio = AdmJogadorFile.getListObjDoArq(fileCompVazio);
		assertEquals(1, listaArqVazio.size());
		System.out.println("\n" + "Lista de um arquivo completamente vazio:");
		imprimir(listaArqVazio);
		
		//l� um arquivo em que todos os 7 jogadores foram exclu�dos
		ArrayList<Object> listaArqZeroJog = AdmJogadorFile.getListObjDoArq(fileZeroJog);
		assertEquals(1, listaArqZeroJog.size());
		System.out.println("\n" + "Lista de um arquivo em que todos os 7 jogadores foram exclu�dos:");
		imprimir(listaArqZeroJog);
		
		//l� um arquivo com um jogador
		ArrayList<Object> listaArqUmJog = AdmJogadorFile.getListObjDoArq(fileUmJog);
		assertEquals(2, listaArqUmJog.size());
		System.out.println("\n" + "Lista de um arquivo com um jogador:");
		imprimir(listaArqUmJog);
		
		//l� um arquivo com sete jogadores
		ArrayList<Object> listaSeteJog = AdmJogadorFile.getListObjDoArq(fileSeteJog);
		assertEquals(8, listaSeteJog.size());
		System.out.println("\n" + "Lista de um arquivo com sete jogadores:");
		imprimir(listaSeteJog);
	}   
	@Test
	//neste teste:
		//1- o m�todo getListObjDoArq l� um arquivo completamente vazio (isso significa que o fluxo de conex�o ser� criado mas o fluxo de cadeia n�o)
		//2- ap�s ler o conte�do do arquivo e obter uma lista, o teste tenta apagar o arquivo
	//objetivo: testar se o m�todo getListObjDoArq fecha corretamente os fluxos de conex�o e de cadeia
		//se sim, o arquivo ser� exclu�do
		//se n�o, o arquivo n�o ser� exclu�do
	void getListObjDoArq2() throws Exception {
		//cria o objeto que representa o arquivo
		File file = new File("teste/br/ita/game/files/010_tempCompVazio.ser");
		
		//apaga o arquivo caso o mesmo j� exista
		file.delete();
		assertFalse(file.exists());
		//recria o arquivo
		try {
			file.createNewFile();
		} catch (IOException ex) { System.out.println(ex.getMessage()); }
		assertTrue(file.exists());
		
		//obt�m uma lista de objetos do arquivo
		ArrayList<Object> listObjDoArq = AdmJogadorFile.getListObjDoArq(file);
		assertEquals(1, listObjDoArq.size());
		//imprime para efeito de controle
		imprimir(listObjDoArq);
		
		//apaga o arquivo
		file.delete();
		assertFalse(file.exists());
	}
	@Test
	//neste teste:
		//1- o m�todo getListObjDoArq l� um arquivo com sete jogadores
		//2- ap�s ler o conte�do do arquivo e obter uma lista, o teste tenta apagar o arquivo
	//objetivo: testar se o m�todo getListObjDoArq fecha corretamente os fluxos de conex�o e cadeia
		//se sim, o arquivo ser� exclu�do
		//se n�o, o arquivo n�o ser� exclu�do
	void getListObjDoArq3() throws Exception {
		//cria o objeto que representa o arquivo de teste
		File file = new File("teste/br/ita/game/files/010_tempCompVazio.ser");
		
		//apaga o arquivo caso o mesmo j� exista
		file.delete();		
		assertFalse(file.exists());
		
		//cria o arquivo copiando o conte�do de um arquivo j� existente
		try {
			copy(fileSeteJog, file);
		} catch (IOException ex) { System.out.println(ex.getMessage()); }
		assertTrue(file.exists());
		
		//obt�m uma lista de objetos do arquivo
		ArrayList<Object> listObjDoArq = AdmJogadorFile.getListObjDoArq(file);
		assertEquals(8, listObjDoArq.size());
		//imprime para efeito de controle
		imprimir(listObjDoArq);
		
		//apaga o arquivo
		file.delete();
		assertFalse(file.exists());
	}
	
	
		
/*** testes do m�todo svListObjNoArq:
		serializa uma lista de objetos no arquivo ***/
	@Test
	void svListObjNoArq() throws Exception {
		//lan�a exce��o ao tentar gravar uma lista nula
		Exception excecaoListaNula = assertThrows(Exception.class, () -> AdmJogadorFile.svListObjNoArq(listaNula, fileListGrav));
		System.out.println("excecaoListaNula: " + excecaoListaNula.getMessage());
		
		//lan�a exce��o ao tentar gravar uma lista vazia
		Exception excecaoListaVazia = assertThrows(Exception.class, () -> AdmJogadorFile.svListObjNoArq(listaVazia, fileListGrav));
		System.out.println("\n" + "excecaoListaVazia: " + excecaoListaVazia.getMessage());
		
		//lan�a exce��o ao tentar gravar uma lista incompleta
		Exception excecaoListaIncompletaUmJog = assertThrows(Exception.class, () -> AdmJogadorFile.svListObjNoArq(listaIncompletaUmJog, fileListGrav));
		System.out.println("\n" + "excecaoListaIncompletaUmJog: " + excecaoListaIncompletaUmJog.getMessage());
		
		//lan�a exce��o ao tentar gravar uma lista incompleta
		Exception excecaoListaIncompletaSeteJog = assertThrows(Exception.class, () -> AdmJogadorFile.svListObjNoArq(listaIncompletaSeteJog, fileListGrav));
		System.out.println("\n" + "excecaoListaIncompletaSeteJog: " + excecaoListaIncompletaSeteJog.getMessage());
		
		//lan�a exce��o ao tentar salvar lista em arquivo nulo
		Exception excecaoFileNull = assertThrows(Exception.class, () -> AdmJogadorFile.svListObjNoArq(listaValidaSeteJog, null));
		System.out.println("\n" + "excecaoFileNull: " + excecaoFileNull.getMessage());
		
		//lan�a exce��o ao tentar salvar a lista em arquivo protegido contra grava��o
		Exception excecaoFileProtGrav = assertThrows(Exception.class, () -> AdmJogadorFile.svListObjNoArq(listaValidaSeteJog, fileProtGrav));
		System.out.println("\n" + "excecaoFileProtGrav: " + excecaoFileProtGrav.getMessage());
				
		//cria o arquivo ao tentar salvar a lista em um arquivo inexistente
		fileListGrav.delete();
		assertFalse(fileListGrav.exists());
		AdmJogadorFile.svListObjNoArq(listaValidaZeroJog, fileListGrav);
		assertTrue(fileListGrav.exists());
		System.out.println("\n" + "Lista v�lida de zero jogador salva no arquivo:");
		imprimir(AdmJogadorFile.getListObjDoArq(fileListGrav));
		
		//salva lista com um jogador
		AdmJogadorFile.svListObjNoArq(listaValidaUmJog, fileListGrav);
		System.out.println("\n" + "Lista v�lida de um jogador salva no arquivo:");
		imprimir(AdmJogadorFile.getListObjDoArq(fileListGrav));
		
		//salva lista com sete jogadores
		AdmJogadorFile.svListObjNoArq(listaValidaSeteJog, fileListGrav);
		System.out.println("\n" + "Lista v�lida de sete jogadores salva no arquivo:");
		imprimir(AdmJogadorFile.getListObjDoArq(fileListGrav));
		
		//salva a lista e apaga o arquivo, para testar o fechamento dos fluxos
		AdmJogadorFile.svListObjNoArq(listaValidaSeteJog, fileInexist);
		assertTrue(fileInexist.exists());
		fileInexist.delete();
		assertFalse(fileInexist.exists());
	}
	
	
	
/*** testes do m�todo salvar:
		serializa um objeto Jogador no arquivo 
		obs: o m�todo salvar � chamado automaticamente todas as vezes que:
			1- se intancia um objeto Jogador 
			2- se atualiza o nome de um objeto Jogador 
			3- se atualiza o apelido de um objeto Jogador 
			4- se atualiza o score de um objeto Jogador ***/	
	@Test
	void salvar() throws Exception {
		//apaga o arquivo
		fileJog.delete();
		assertFalse(fileJog.exists());
		//recria o arquivo
		fileJog.createNewFile();
		assertTrue(fileJog.exists());
		 
		//cria jogadores
		Jogador maria = Jogador.getInstance("maria", "mama");
		Jogador pedro = Jogador.getInstance("pedro", "pp");
		Jogador sanders = Jogador.getInstance("snake sanders", "cobra");
		Jogador laura = Jogador.getInstance("laura", "");
		//imprime para efeito de controle
		imprimir(AdmJogadorFile.getListObjDoArq(fileJog));

		//atualiza os nomes
		maria.setNome("Maria");
		pedro.setNome("Pedro");
		sanders.setNome("Snake Sanders");
		laura.setNome("Laura");
		//imprime para efeito de controle
		System.out.println();
		imprimir(AdmJogadorFile.getListObjDoArq(fileJog));
		
		//atualiza apelidos
		maria.setApelido("Ma");
		pedro.setApelido("PP");
		sanders.setApelido("Cobra");
		laura.setApelido("Parrot");
		//imprime para efeito de controle
		System.out.println();
		imprimir(AdmJogadorFile.getListObjDoArq(fileJog));		
	}	
	
	
	
/*** testes do m�todo excluir:
 		remove um objeto Jogador do arquivo ***/
	@Test
	void excluir() throws Exception {
		//obt�m objetos jogadores pela posi��o que eles ocupam na lista
		Jogador rodrigo = (Jogador) listaValidaSeteJog.get(1);
			assertEquals("Rodrigo", rodrigo.getNome() );
		Jogador maria = (Jogador) listaValidaSeteJog.get(2);
			assertEquals("Maria", maria.getNome());
		Jogador pedro = (Jogador) listaValidaSeteJog.get(3);
			assertEquals("Pedro", pedro.getNome());
		Jogador laura = (Jogador) listaValidaSeteJog.get(4);
			assertEquals("Laura", laura.getNome());
		Jogador jose = (Jogador) listaValidaSeteJog.get(5);
			assertEquals("Jos�", jose.getNome());
		Jogador fernando = (Jogador) listaValidaSeteJog.get(6);
			assertEquals("Fernando", fernando.getNome());
		Jogador roberto = (Jogador) listaValidaSeteJog.get(7);
			assertEquals("Roberto", roberto.getNome());
		
		//apaga o arquivo
		fileJog.delete();
		assertFalse(fileJog.exists());
		//recria o arquivo com sete jogadores
		try {
			copy(fileSeteJog, fileJog);
		} catch (IOException ex) { System.out.println(ex.getMessage()); }
		assertTrue(fileJog.exists());		

		//imprime arquivo para efeito de controle
		imprimir(AdmJogadorFile.getListObjDoArq(fileJog));
		
		//apaga Rodrigo
		AdmJogadorFile.excluir(rodrigo);
		//imprime arquivo para efeito de controle
		System.out.println();
		imprimir(AdmJogadorFile.getListObjDoArq(fileJog));
		
		//apaga Maria
		AdmJogadorFile.excluir(maria);
		//imprime arquivo para efeito de controle
		System.out.println();
		imprimir(AdmJogadorFile.getListObjDoArq(fileJog));		
		
		//apaga Pedro, Laura e Jos�
		AdmJogadorFile.excluir(pedro);
		AdmJogadorFile.excluir(laura);
		AdmJogadorFile.excluir(jose);
		//imprime arquivo para efeito de controle
		System.out.println();
		imprimir(AdmJogadorFile.getListObjDoArq(fileJog));
		
		//apaga Fernando e Roberto
		AdmJogadorFile.excluir(fernando);
		AdmJogadorFile.excluir(roberto);
		//imprime arquivo para efeito de controle
		System.out.println();
		imprimir(AdmJogadorFile.getListObjDoArq(fileJog));
		
		//apaga Jogador que n�o pertence mais ao arquivo
		try {
			AdmJogadorFile.excluir(roberto);
		} catch (Exception ex) {System.out.println(ex.getMessage()); }
		
		
		//cria novos jogadores
		Jogador.getInstance("Silas", "Sil");
		Jogador.getInstance("Thiago", "Titi");
		Jogador.getInstance("Sabrina", "Sa");
		//imprime arquivo para efeito de controle
		System.out.println();
		imprimir(AdmJogadorFile.getListObjDoArq(fileJog));
	}
	
	
	
/*** testes do m�todo getJogador:
 		retorna um objeto Jogador pertencente � lista 
		retorna null caso o objeto Jogador n�o perten�a � lista ***/
	@Test
	void getObjJog() throws Exception {
		//apaga o arquivo
		fileJog.delete();
		assertFalse(fileJog.exists());
        //recria o arquivo com sete jogadores
		try {
			copy(fileSeteJog, fileJog);
		} catch (IOException ex) { System.out.println(ex.getMessage()); }
		assertTrue(fileJog.exists());
		
		Jogador rodrigo = AdmJogadorFile.getJogador(1);
		System.out.println(rodrigo.getNome());
		
		Jogador maria = AdmJogadorFile.getJogador(2);
		System.out.println(maria.getNome());		

		Jogador pedro = AdmJogadorFile.getJogador(3);
		System.out.println(pedro.getNome());		
		
		Jogador laura = AdmJogadorFile.getJogador(4);
		System.out.println(laura.getNome());	
		
		Jogador jose = AdmJogadorFile.getJogador(5);
		System.out.println(jose.getNome());	

		Jogador fernando = AdmJogadorFile.getJogador(6);
		System.out.println(fernando.getNome());	

		Jogador roberto = AdmJogadorFile.getJogador(7);
		System.out.println(roberto.getNome());
		
		//pesquisando jogador inexistente
		assertNull(AdmJogadorFile.getJogador(8));
	}
	
	
	
/*** testes do m�todo getListaJogadores:
 		retorna uma lista contendo id, nome e apelido dos jogadores ***/
	@Test
	void getListaJogadores() throws Exception {
		//apaga o arquivo
		fileJog.delete();
		//recria o arquivo
		fileJog.createNewFile();
		
		String[] lista1 = AdmJogadorFile.getListaJogadores();
		System.out.println("Lista 1:");
		imprimir(lista1);
		
		Jogador.getInstance("Mauricio", "Mau");
		String[] lista2 = AdmJogadorFile.getListaJogadores();
		System.out.println("\n" + "Lista 2:");
		imprimir(lista2);
		
		Jogador.getInstance("Laura", "Parrot");
		Jogador.getInstance("Thiago", "titi");
		Jogador.getInstance("Sabrina", "Sab");
		String[] lista3 = AdmJogadorFile.getListaJogadores();
		System.out.println("\n" + "Lista 3:");
		imprimir(lista3);	
	}
	
	
	
/*** testes do getRankingJogadores 
 		retorna uma lista contendo os dados dos jogadores, classificada pelo n�mero de pontos ganhos ***/
	@Test
	void getRankingJogadores() throws Exception {
		//apaga o arquivo
		fileJog.delete();
		//recria o arquivo
		fileJog.createNewFile();
		
		//exibe o ranking com o arquivo vazio
		String[] ranking1 = AdmJogadorFile.getRankingJogadores(null);
		System.out.println("Ranking 1:");
		imprimir(ranking1);
		
		//cria jogadores
		Jogador mauricio = Jogador.getInstance("Mauricio", "Mau");
			mauricio.incScore(PartidaStatus.VITORIA, 10);
		Jogador thiago = Jogador.getInstance("Thiago", "titi");
			thiago.incScore(PartidaStatus.VITORIA, 10);
			thiago.incScore(PartidaStatus.VITORIA, 10);
		Jogador laura = Jogador.getInstance("Laura", "Parrot");
			laura.incScore(PartidaStatus.VITORIA, 10);
			laura.incScore(PartidaStatus.VITORIA, 10);
			laura.incScore(PartidaStatus.VITORIA, 10);
			laura.incScore(PartidaStatus.DERROTA, 0);
			laura.incScore(PartidaStatus.DERROTA, 10);

		//exibe novamente o ranking
		String[] ranking2 = AdmJogadorFile.getRankingJogadores(null);
		System.out.println("\n" + "Ranking 2:");
		imprimir(ranking2);
	}
}