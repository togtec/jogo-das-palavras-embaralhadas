package br.ita.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class TestaBancoDePalavras {

	@Test
	void testeRetornaQtdNegativaDePalavras() {
		ArrayList<Palavra> listaPalavras = BancoDePalavras.getListaPalavrasAleat(-1);		
		assertEquals(1, listaPalavras.size());
	}
	
	@Test
	void testeRetornaZeroPalavras() {
		ArrayList<Palavra> listaPalavras = BancoDePalavras.getListaPalavrasAleat(0);
		assertEquals(1, listaPalavras.size());
	}
	
	@Test
	void testeRetornaQtdPalavrasDentroLimite() {
		//no momento do teste o arquivo continha 20 palavras
		ArrayList<Palavra> listaPalavras = BancoDePalavras.getListaPalavrasAleat(5);
		assertEquals(5, listaPalavras.size());
	}
	
	@Test
	void testeRetornaMaisPalavrasDoQueTemNoArquivo() {
		//no momento do teste o arquivo continha 50 palavras
		ArrayList<Palavra> listaPalavras = BancoDePalavras.getListaPalavrasAleat(100);
		assertEquals(50, listaPalavras.size());
		
		//imprime para checar as informações
		int cont = 1;
		for (Palavra palavra : listaPalavras) {
			System.out.println("Palavra " + cont);
			System.out.println(palavra.getSilabas());
			System.out.println(palavra.getValorPalavra() + " pts");
			System.out.println(palavra.getDica());
			System.out.println();
			//incrementa contador
			cont++; 
		} //fecha for
	} //fecha método testeRetornaMaisPalavrasDoQueTemNoArquivo

}
