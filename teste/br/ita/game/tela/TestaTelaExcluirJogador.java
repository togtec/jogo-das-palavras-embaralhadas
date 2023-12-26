package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ita.game.AdmJogadorFile;
import br.ita.game.Jogador;

@SuppressWarnings("unused")
class TestaTelaExcluirJogador {
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
	void excluirJogador() throws Exception {
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		Jogador.getInstance("Maria", "mama");
		new TelaExcluirJogador(jog).exibirTela();
		
		impListJogDoArq();
	} //resultado do teste: (esperado)
			//1- jogador é excluído
			//2- barra de jogador deixa de ser exibida

	@Test
	//exclui jogador que não pertence ao arquivo
	void excluirJogador2() throws Exception {
		//cria jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		Jogador.getInstance("Maria", "mama");
		
		//apaga jogador rodrigo
		new TelaExcluirJogador(jog).exibirTela();
		
		//apaga rodrigo de novo - que já não pertence mais ao arquivo
		new TelaExcluirJogador(jog).exibirTela();
		
		impListJogDoArq();
	} //resultado do teste: (esperado) - nenhum erro detectado
			//usuário recebe mensagem dizendo que jogador foi excluído	
	
	@Test
	//exclui jogador sem o arquivo
	void excluirJogadorSemArquivo() throws Exception {
		//cria jogadores
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		Jogador.getInstance("Maria", "mama");

		//apaga arquivo
		fileJog.delete(); 		
		
		new TelaExcluirJogador(jog).exibirTela();
		
	} //resultado do teste: (esperado)
			//mensagem de erro é exibida na tela para o usuário
			//barra de jogador não é exibida
}
