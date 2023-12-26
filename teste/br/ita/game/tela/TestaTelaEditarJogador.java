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
	
	/*** m�todo utilit�rio (n�o � m�todo de teste) ***/
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
		//5- tentar nomes e apelidos inv�lidos
		//6- voltar sem trocar nada
	void trocaNomeTrocaApelido1() throws Exception {
		Jogador jog = Jogador.getInstance("Rodrigo Tognetta", "Rodriguinho");
		new TelaEditarJogador(jog).exibirTela();		
		
		impListJogDoArq();
	} //resultado do teste: (esperado)
		//1 - ok
		//2 - ok
		//3 - ok
		//4 - ok - nome � salvo independente de atualizar o apelido
		//5 - ok - somente nomes e apelidos v�lidos s�o permitidos
		//6 - ok - nada � trocado
			
	@Test
	//roteiro: sem utilizar da tecla manter, inserir o mesmo nome, e o mesmo apelido
	void trocaNomeTrocaApelido2() throws Exception {
		Jogador jog = Jogador.getInstance("Luciana", "Lu");
		new TelaEditarJogador(jog).exibirTela();		
		
		impListJogDoArq();
	} //resultado do teste: (esperado) - nome e apelido s�o mantidos mas din�mica avan�a
	
	@Test
	//testa sem arquivo
	void trocaNomeTrocaApelido3() throws Exception {
		Jogador jog = Jogador.getInstance("Luciana", "Lu");
		
		fileJog.delete(); //apaga arquivo
		
		new TelaEditarJogador(jog).exibirTela();		
			
	} //resultado do teste:
			//se o usu�rio utilizar a tecla manter, n�o perceber� que o arquivo est� com problemas. Pois a tecla manter n�o 
			//salva de verdade. Apenas avan�a a din�mica da classe
	
			//se o usu�rio digitar um novo nome ou um novo apelido, uma mensagem de erro � exibida na tela
			//nesse momento, a barra de jogador n�o � exibida na tela
}
