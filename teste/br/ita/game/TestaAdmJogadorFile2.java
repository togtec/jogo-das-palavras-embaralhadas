/*** 
  	Sobre esta classe: 
  		A classe TestaAdmJogadorFile2 testa o método AdmJogadorFile.crud recebendo chamadas de múltiplos segmentos. Entretando, 
 		é importante notar que esta não é uma classe de testes JUnit. É uma classe java comum! Portanto, para se ter sucesso
 		nos testes, é importante seguir o roteiro! 
 	
 	Observação importante: 
 		Tentei realizar os testes abaixo em uma classe de testes JUnit. Foram dezenas de testes com resultados imprevisíveis, 
 		inconclusivos, ilógicos e malucos - uma tremenda perda de tempo. Após cerca de uma centena de testes, concluí que o JUnit 
 		realmente não foi feito para se testar múltiplos segmentos, e desenvolvi essa classe comum para tal. 
 	 	
 	Funcionamento:
 	 	Todo objeto Jogador, ao ser instanciado, é automaticamente serializado em disco: arquivo "jog.ser". 
 		O que esta classe faz é instanciar 31 objetos Jogador, em 31 segmentos diferentes. Isso gera problemas de concorrência
 		pois os 31 segmentos salvam sua instância de Jogador no mesmo arquivo.
 		
 	Configurando esta classe antes de testar:
 		No método main é possível configurar esta classe para iniciar, 2, 10 ou 31 segmentos ao mesmo tempo. Para isso, basta
 		escolher a opção desejada e comentar as demais. 
 		
 	Resultados esperados:
 		É importante registrar que após o último segmento ser concluído (não importando quantos foram iniciados) o arquivo "jog.ser" é impresso 
 		automaticamente na console. Se no momento do teste o método main estava configurado para iniciar 2 segmentos, ao final do teste, 
 		deve haver 2 objetos Jogador serializados no arquivo. Se foram 10 segmentos, 10 objetos Jogador, e assim por diante.   
 	
 	 Roteiro:
 		1- comente a versão sincronizada da assinatura do método AdmJogadorFile.crud, de modo que o método utilize sua assinatura não sincronizada.
 		2- configure o método main (logo abaixo) para iniciar apenas 2 segmentos
 		3- rode esta classe como aplicativo java  		
 	 	
 	Resultados obtidos:
 		-com a assinatura não sincronizada do método AdmJogadorFile.crud
 			perda de informação decorrente de problemas de concorrência com 2, 10 e 31 segmentos
 			
 		-com a assinatura sincronizda do método AdmJogadorFile.crud 
 			nenhuma perda de informação! (testes realizados com 2, 10 e 31 segmentos) ***/

package br.ita.game;

import java.io.File;
import java.util.ArrayList;

public class TestaAdmJogadorFile2 {
	//arquivo de produção
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");	
	//cria os múltiplos segmentos
	private Thread seg1 = new Thread(new CriarJogador("Adelino", "Professor"));
	private Thread seg2 = new Thread(new CriarJogador("André", "Robles"));
	private Thread seg3 = new Thread(new CriarJogador("Andrea", "Flor"));
	private Thread seg4 = new Thread(new CriarJogador("Carolina", "Carol"));
	private Thread seg5 = new Thread(new CriarJogador("Daniel", "Vidão"));
	private Thread seg6 = new Thread(new CriarJogador("Evelyn", "Eve"));
	private Thread seg7 = new Thread(new CriarJogador("Flávia", "Fla"));
	private Thread seg8 = new Thread(new CriarJogador("Gustavo", "Guto"));
	private Thread seg9 = new Thread(new CriarJogador("Luciana", "Lu"));
	private Thread seg10 = new Thread(new CriarJogador("Marcela", "Ma"));
	private Thread seg11 = new Thread(new CriarJogador("Marcia", "Mar"));
	private Thread seg12 = new Thread(new CriarJogador("Marcio", ""));
	private Thread seg13 = new Thread(new CriarJogador("Marcos", "Mac"));
	private Thread seg14 = new Thread(new CriarJogador("Maria", "Mamá"));
	private Thread seg15 = new Thread(new CriarJogador("Marlene", ""));
	private Thread seg16 = new Thread(new CriarJogador("Pablo", ""));
	private Thread seg17 = new Thread(new CriarJogador("Paulo", ""));
	private Thread seg18 = new Thread(new CriarJogador("Pedro", "PP"));
	private Thread seg19 = new Thread(new CriarJogador("Ricardo", "Ric"));
	private Thread seg20 = new Thread(new CriarJogador("Roberto", "Betão"));
	private Thread seg21 = new Thread(new CriarJogador("Rodrigo", "Tog"));
	private Thread seg22 = new Thread(new CriarJogador("Rosa", ""));
	private Thread seg23 = new Thread(new CriarJogador("Sabrina", ""));
	private Thread seg24 = new Thread(new CriarJogador("Sâmia", "Sâ"));
	private Thread seg25 = new Thread(new CriarJogador("Sandro", "Leléu"));
	private Thread seg26 = new Thread(new CriarJogador("Saulo", ""));
	private Thread seg27 = new Thread(new CriarJogador("Silvio", "Bartira"));
	private Thread seg28 = new Thread(new CriarJogador("Simone", "Si"));
	private Thread seg29 = new Thread(new CriarJogador("Tania", ""));
	private Thread seg30 = new Thread(new CriarJogador("Vera", "Verinha"));
	private Thread seg31 = new Thread(new CriarJogador("Vinícius", "Vi"));

	//método main (antes de rodar, escolha a quantidade de segmentos a serem inicializados: 2, 10 ou 31)
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
	
	//método utilitário - exclui e recria o arquivo de produção
	private static void prepararArquivo() {
		//apaga arquivo
		fileJog.delete();
		//recria arquivo
		try {
			fileJog.createNewFile();
		} catch (Exception ex) { System.out.println(ex.getMessage()); }
	}
	//método utilitário - imprime arquivo
	public static void imprimirArquivo() {
		ArrayList<Object> lista = null;
		
		try {
			lista = AdmJogadorFile.getListObjDoArq(fileJog);
		} catch (Exception ex) { System.out.println(ex.getMessage()); }
		
		System.out.println("\t último id utilizado: " + lista.get(0));
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
	
	//métodos de captura e configuração
	public static void setQtdSegIniciados(int qtd) {
		qtdSegIniciados = qtd;
	}
	
	@Override
	//método de implementação obrigatória da interface Runnable
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