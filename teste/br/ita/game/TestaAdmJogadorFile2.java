/*** 
  	Sobre esta classe: 
  		A classe TestaAdmJogadorFile2 testa o m�todo AdmJogadorFile.crud recebendo chamadas de m�ltiplos segmentos. Entretando, 
 		� importante notar que esta n�o � uma classe de testes JUnit. � uma classe java comum! Portanto, para se ter sucesso
 		nos testes, � importante seguir o roteiro! 
 	
 	Observa��o importante: 
 		Tentei realizar os testes abaixo em uma classe de testes JUnit. Foram dezenas de testes com resultados imprevis�veis, 
 		inconclusivos, il�gicos e malucos - uma tremenda perda de tempo. Ap�s cerca de uma centena de testes, conclu� que o JUnit 
 		realmente n�o foi feito para se testar m�ltiplos segmentos, e desenvolvi essa classe comum para tal. 
 	 	
 	Funcionamento:
 	 	Todo objeto Jogador, ao ser instanciado, � automaticamente serializado em disco: arquivo "jog.ser". 
 		O que esta classe faz � instanciar 31 objetos Jogador, em 31 segmentos diferentes. Isso gera problemas de concorr�ncia
 		pois os 31 segmentos salvam sua inst�ncia de Jogador no mesmo arquivo.
 		
 	Configurando esta classe antes de testar:
 		No m�todo main � poss�vel configurar esta classe para iniciar, 2, 10 ou 31 segmentos ao mesmo tempo. Para isso, basta
 		escolher a op��o desejada e comentar as demais. 
 		
 	Resultados esperados:
 		� importante registrar que ap�s o �ltimo segmento ser conclu�do (n�o importando quantos foram iniciados) o arquivo "jog.ser" � impresso 
 		automaticamente na console. Se no momento do teste o m�todo main estava configurado para iniciar 2 segmentos, ao final do teste, 
 		deve haver 2 objetos Jogador serializados no arquivo. Se foram 10 segmentos, 10 objetos Jogador, e assim por diante.   
 	
 	 Roteiro:
 		1- comente a vers�o sincronizada da assinatura do m�todo AdmJogadorFile.crud, de modo que o m�todo utilize sua assinatura n�o sincronizada.
 		2- configure o m�todo main (logo abaixo) para iniciar apenas 2 segmentos
 		3- rode esta classe como aplicativo java  		
 	 	
 	Resultados obtidos:
 		-com a assinatura n�o sincronizada do m�todo AdmJogadorFile.crud
 			perda de informa��o decorrente de problemas de concorr�ncia com 2, 10 e 31 segmentos
 			
 		-com a assinatura sincronizda do m�todo AdmJogadorFile.crud 
 			nenhuma perda de informa��o! (testes realizados com 2, 10 e 31 segmentos) ***/

package br.ita.game;

import java.io.File;
import java.util.ArrayList;

public class TestaAdmJogadorFile2 {
	//arquivo de produ��o
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");	
	//cria os m�ltiplos segmentos
	private Thread seg1 = new Thread(new CriarJogador("Adelino", "Professor"));
	private Thread seg2 = new Thread(new CriarJogador("Andr�", "Robles"));
	private Thread seg3 = new Thread(new CriarJogador("Andrea", "Flor"));
	private Thread seg4 = new Thread(new CriarJogador("Carolina", "Carol"));
	private Thread seg5 = new Thread(new CriarJogador("Daniel", "Vid�o"));
	private Thread seg6 = new Thread(new CriarJogador("Evelyn", "Eve"));
	private Thread seg7 = new Thread(new CriarJogador("Fl�via", "Fla"));
	private Thread seg8 = new Thread(new CriarJogador("Gustavo", "Guto"));
	private Thread seg9 = new Thread(new CriarJogador("Luciana", "Lu"));
	private Thread seg10 = new Thread(new CriarJogador("Marcela", "Ma"));
	private Thread seg11 = new Thread(new CriarJogador("Marcia", "Mar"));
	private Thread seg12 = new Thread(new CriarJogador("Marcio", ""));
	private Thread seg13 = new Thread(new CriarJogador("Marcos", "Mac"));
	private Thread seg14 = new Thread(new CriarJogador("Maria", "Mam�"));
	private Thread seg15 = new Thread(new CriarJogador("Marlene", ""));
	private Thread seg16 = new Thread(new CriarJogador("Pablo", ""));
	private Thread seg17 = new Thread(new CriarJogador("Paulo", ""));
	private Thread seg18 = new Thread(new CriarJogador("Pedro", "PP"));
	private Thread seg19 = new Thread(new CriarJogador("Ricardo", "Ric"));
	private Thread seg20 = new Thread(new CriarJogador("Roberto", "Bet�o"));
	private Thread seg21 = new Thread(new CriarJogador("Rodrigo", "Tog"));
	private Thread seg22 = new Thread(new CriarJogador("Rosa", ""));
	private Thread seg23 = new Thread(new CriarJogador("Sabrina", ""));
	private Thread seg24 = new Thread(new CriarJogador("S�mia", "S�"));
	private Thread seg25 = new Thread(new CriarJogador("Sandro", "Lel�u"));
	private Thread seg26 = new Thread(new CriarJogador("Saulo", ""));
	private Thread seg27 = new Thread(new CriarJogador("Silvio", "Bartira"));
	private Thread seg28 = new Thread(new CriarJogador("Simone", "Si"));
	private Thread seg29 = new Thread(new CriarJogador("Tania", ""));
	private Thread seg30 = new Thread(new CriarJogador("Vera", "Verinha"));
	private Thread seg31 = new Thread(new CriarJogador("Vin�cius", "Vi"));

	//m�todo main (antes de rodar, escolha a quantidade de segmentos a serem inicializados: 2, 10 ou 31)
	public static void main(String[] args) {
		prepararArquivo();
		//new TestaAdmJogadorFile2().iniciar2Segmentos();
		//new TestaAdmJogadorFile2().iniciar10Segmentos();
		new TestaAdmJogadorFile2().iniciar31Segmentos();
	}
	
	//inicia 2 segmentos
	public void iniciar2Segmentos() {
		CriarJogador.setQtdSegIniciados(2);
		seg1.start(); seg2.start();
	}
	//inicia 10 segmentos
	public void iniciar10Segmentos() {
		CriarJogador.setQtdSegIniciados(10);
		seg1.start(); seg2.start(); seg3.start(); seg4.start(); seg5.start(); seg6.start(); seg7.start(); seg8.start(); seg9.start(); seg10.start();
	}
	//inicia 31 segmentos
	public void iniciar31Segmentos() {
		CriarJogador.setQtdSegIniciados(31);
		seg1.start(); seg2.start(); seg3.start(); seg4.start(); seg5.start(); seg6.start(); seg7.start(); seg8.start(); seg9.start(); seg10.start();
		seg11.start(); seg12.start(); seg13.start(); seg14.start(); seg15.start(); seg16.start(); seg17.start(); seg18.start(); seg19.start(); seg20.start();
		seg21.start(); seg22.start(); seg23.start(); seg24.start(); seg25.start(); seg26.start(); seg27.start(); seg28.start(); seg29.start(); seg30.start();
		seg31.start();
	}	
	
	//m�todo utilit�rio - exclui e recria o arquivo de produ��o
	private static void prepararArquivo() {
		//apaga arquivo
		fileJog.delete();
		//recria arquivo
		try {
			fileJog.createNewFile();
		} catch (Exception ex) { System.out.println(ex.getMessage()); }
	}
	//m�todo utilit�rio - imprime arquivo
	public static void imprimirArquivo() {
		ArrayList<Object> lista = null;
		
		try {
			lista = AdmJogadorFile.getListObjDoArq(fileJog);
		} catch (Exception ex) { System.out.println(ex.getMessage()); }
		
		System.out.println("\t �ltimo id utilizado: " + lista.get(0));
		for (int cont = 1; cont < lista.size(); cont++) {
			//converte de Object para Jogador
			Jogador jog = (Jogador) lista.get(cont);
			System.out.println("\t "+ jog.getId() + " " + jog.getNome() + " - " + jog.getApelido());
		}
	} 
} //fim da classe TestaAdmJogadorFile2



//classe externa - tarefa de segmento - cria um(a) novo(a) Jogador(a)
class CriarJogador implements Runnable {
	private static int qtdSegIniciados;
	private static int qtdSegConcluidos;	
	private String nome;
	private String apelido;
	
	//construtor
	public CriarJogador(String nome, String apelido) {
		this.nome = nome;
		this.apelido = apelido;
	}
	
	//m�todos de captura e configura��o
	public static void setQtdSegIniciados(int qtd) {
		qtdSegIniciados = qtd;
	}
	
	@Override
	//m�todo de implementa��o obrigat�ria da interface Runnable
	public void run() {
		try {
			Jogador.getInstance(nome, apelido);
		} catch (Exception ex) { System.out.println("ex.getMessage"); }
		
		qtdSegConcluidos++;
		if (qtdSegIniciados == qtdSegConcluidos) {
			TestaAdmJogadorFile2.imprimirArquivo();
		}
	}
} //fim da classe externa CriarJogador