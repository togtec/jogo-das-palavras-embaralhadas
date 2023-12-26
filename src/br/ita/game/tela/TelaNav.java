/* A classe TelaNav (tela de navegação) cria as estruturas básicas existentes em todas as telas do jogo: 
 * 		barra de título
 *		barra de subtítulo
 *		barra de menu
 *		conteúdo
 *		prompt */

package br.ita.game.tela;

import java.util.LinkedHashMap;
import java.util.Scanner;

public abstract class TelaNav {
	private boolean visible = true;
	private String returnKey = "v";
	
	public static final int larguraJanela = 141;
	public static final int alturaJanela = 38;
	public static final String separadorExterno = "*";
	public static final String separadorInterno = "-";
	public static final String margemEsquerda = "     "; //5 espaços em branco
	public static final String titulo = "JOGO DAS PALAVRAS EMBARALHADAS";
	
	private String subtitulo;   //iniciado no método construtor
	private LinkedHashMap<String, String> barraMenu;   /* iniciado no método construtor;
	ao contrário do HashMap, LinkedHashMap matém a ordem de inserção */
	private StringBuilder conteudo = new StringBuilder();
	private String promptCmd = "comando> ";
	private int qtdLinhasImp = 0;   //quantidade de linhas impressas
	
	private static final Scanner entrada = new Scanner(System.in);

	
	
	//construtor
	public TelaNav(String subtitulo) {
		//define subtitulo
		this.subtitulo = subtitulo;		
		//define barra de menu
		barraMenu = new LinkedHashMap<String, String>();
		addMenu("v", "voltar");
	}

	//retorna tecla de retorno
	protected String getReturnKey() {
		return returnKey;
	}
	
	//retorna linha divisória
	protected static String getLinhaDivisoria(String margem, String separador) {
		StringBuilder aux = new StringBuilder();
		
		int contador;
		if (margem == null) {
			contador = 0;
		} else {
			contador = margem.length();
			aux.append(margem);
		}
		
		for(; contador < larguraJanela; contador++) {
			aux.append(separador);
		}
		//retorna linha divisória
		return aux.toString();
	}	
	
	//retorna o conteúdo de uma string de modo que fique centralizado em relação à largura da janela 
	protected static String getCentralizado(String s) {
		StringBuilder aux = new StringBuilder();
		
		int posicaoInicial = (larguraJanela - s.length()) / 2;
		for (int cont = 0; cont < posicaoInicial; cont++) {
			aux.append(" ");
		}
		aux.append(s);
		
		return aux.toString();
	}	
	
	protected void setVisible(boolean b) {
		visible = b;
	}
	
	protected void setReturnKey(String s) {
		returnKey = s;
	}
	
	//define o prompt de comando
	protected void setPromptCmd(String prompt) {
		promptCmd = prompt;
	}
	
	//remove todos os menus da barra de menu
	protected void limpaBarraMenu() {
		barraMenu.clear();
	}	
	//adiciona menu à barra de menu
	protected void addMenu(String chave, String valor) {
		barraMenu.put(chave, valor);
	}
	
	//apaga o conteúdo do StringBuilder
	protected void limpaConteudo() {
		conteudo.delete(0, conteudo.length());
	}
	//define o conteúdo do StringBuilder
	protected void setConteudo(StringBuilder conteudo) {
		this.conteudo = conteudo;
	}
	//adiciona conteúdo ao StringBuilder
	protected void addConteudo(String conteudo) {
		this.conteudo.append(conteudo);
		this.conteudo.append("\n");
	}
	
	//incrementa quantidade de linhas impressas
	protected void incQtdLinhasImp(int qtd) {
		qtdLinhasImp += qtd;
	}
	
	//imprime cabeçalho
	protected void impCabecalho() {
		//imprime 50 quebras de linha para rolar a tela
		for (int cont = 0; cont < 50; cont++) {
			System.out.println();
		}
		
		//zera quantidade de linhas impressas
		qtdLinhasImp = 0;
		
		//imprime barra de título
		System.out.println(getLinhaDivisoria(null, separadorExterno));
		System.out.println(getCentralizado(titulo));
		System.out.println(getLinhaDivisoria(null, separadorExterno));
		incQtdLinhasImp(3);
		
		//imprime barra de subtítulo
		System.out.println(getCentralizado(subtitulo));
		System.out.println(getLinhaDivisoria(null, separadorExterno));
		incQtdLinhasImp(2);
		
		//imprime barra de menu
		if(barraMenu.size() > 0) {
			for (String chave : barraMenu.keySet()) {
				System.out.print(margemEsquerda);
				System.out.print(chave);
				System.out.print("- ");
				System.out.print(barraMenu.get(chave));
			}
			//imprime quebra de linha
			System.out.println();
			//imprime linha divisória
			System.out.println(getLinhaDivisoria(null, separadorInterno));
			incQtdLinhasImp(2);
		}
	}
	
	//imprime conteúdo
	protected void impConteudo() {
		if (conteudo != null && conteudo.length() > 0) {
			//separa cada linha a ser impressa
			String[] aux = conteudo.toString().split("\n");
			
			for (int cont = 0; cont < aux.length; cont++) {
				System.out.print(margemEsquerda);
				System.out.print(aux[cont]);
				System.out.println(); //quebra de linha
				incQtdLinhasImp(1);
			}
		}
	} //fim do método impConteudo
	
	//imprime prompt de comando
	protected void impPromptCmd() {
		//calcula a quantidade de quebras de linha
		int ql = alturaJanela - qtdLinhasImp;
		for (int cont = 0; cont < ql; cont++) {
			//imprime quebra de linha
			System.out.println();
		}
		//imprime o prompt
		System.out.print(margemEsquerda + promptCmd);
	}

	//lê entrada do usuário
	protected String leEntrada() {
		impCabecalho();
		impConteudo();
		impPromptCmd();
		String comando = entrada.nextLine();
		return comando;
	}
	
	//exibe a tela (loop)
	public void exibirTela() {
		boolean continua = true;
		while (continua) {
			if (!visible) {
				continua = false;
			} else {
				String comando = leEntrada();
			
				if (comando.equalsIgnoreCase(returnKey)) {
					continua = false;
				} else {
					processaCmd(comando);
				}
			}
		}
	} //fim do método exibirTela
	
	//método abastrato: será implementado nas subclasses
	protected abstract void processaCmd(String cmd);
}
