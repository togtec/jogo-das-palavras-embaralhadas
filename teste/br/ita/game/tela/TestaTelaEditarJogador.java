package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ita.game.AdmJogadorFile;
import br.ita.game.Jogador;

@SuppressWarnings("unused")
class TestaTelaEditarJogador {
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");
	
	/*** método utilitário (não é método de teste) ***/
	//imprime arquivo
	void impListJogDoArq() throws Exception {
		String[] listaJogadores = AdmJogadorFile.getListaJogadores();
		for (String jogador : listaJogadores) {
			System.out.println(TelaNav.margemEsquerda + jogador);
		}
	}	
	
	@BeforeEach
	void preparaArquivo() {		
		fileJog.delete(); //apaga arquivo	
		try {             //recria arquivo
			fileJog.createNewFile();
		} catch(IOException ex) { System.out.println(ex.getMessage()); }	
	}	
	
	@Test
	//roteiro de testes:
		//1- testar teclas m (ambas - nome e apelido)
		//2- manter o nome, trocar o apelido
		//3- trocar o nome, manter o apelido
		//4- trocar o nome e voltar
		//5- tentar nomes e apelidos inválidos
		//6- voltar sem trocar nada
	void trocaNomeTrocaApelido1() throws Exception {
		Jogador jog = Jogador.getInstance("Rodrigo Tognetta", "Rodriguinho");
		new TelaEditarJogador(jog).exibirTela();		
		
		impListJogDoArq();
	} //resultado do teste: (esperado)
		//1 - ok
		//2 - ok
		//3 - ok
		//4 - ok - nome é salvo independente de atualizar o apelido
		//5 - ok - somente nomes e apelidos válidos são permitidos
		//6 - ok - nada é trocado
			
	@Test
	//roteiro: sem utilizar da tecla manter, inserir o mesmo nome, e o mesmo apelido
	void trocaNomeTrocaApelido2() throws Exception {
		Jogador jog = Jogador.getInstance("Luciana", "Lu");
		new TelaEditarJogador(jog).exibirTela();		
		
		impListJogDoArq();
	} //resultado do teste: (esperado) - nome e apelido são mantidos mas dinâmica avança
	
	@Test
	//testa sem arquivo
	void trocaNomeTrocaApelido3() throws Exception {
		Jogador jog = Jogador.getInstance("Luciana", "Lu");
		
		fileJog.delete(); //apaga arquivo
		
		new TelaEditarJogador(jog).exibirTela();		
			
	} //resultado do teste:
			//se o usuário utilizar a tecla manter, não perceberá que o arquivo está com problemas. Pois a tecla manter não 
			//salva de verdade. Apenas avança a dinâmica da classe
	
			//se o usuário digitar um novo nome ou um novo apelido, uma mensagem de erro é exibida na tela
			//nesse momento, a barra de jogador não é exibida na tela
}
