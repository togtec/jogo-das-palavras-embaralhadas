package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ita.game.Jogador;

@SuppressWarnings("unused")
class TestaTelaExibirRanking {
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");

	@BeforeEach
	void prepararArquivo() {		
		fileJog.delete(); //apaga arquivo	
		try {             //recria arquivo
			fileJog.createNewFile();
		} catch(IOException ex) { System.out.println(ex.getMessage()); }	
	}	
		
	@Test
	void testaTelaSemArquivo() {
		fileJog.delete(); //apaga arquivo
		
		new TelaExibirRanking(null).exibirTela();
	} //resultado do teste: (esperado) - tela exibe mensagem de erro ao usuário

	@Test
	//teste sem identificar jogador
	void testaComArquivo1() throws Exception {
		Jogador.getInstance("Rodrigo", "Tog");
		Jogador.getInstance("Maria", "mama");
		Jogador.getInstance("Cintia", "cin");
		
		new TelaExibirRanking(null).exibirTela();
	} //resultado do teste: (esperado) - ranking exibido com sucesso!
	
	@Test
	//teste com jogador identificado
	void testaComArquivo2() throws Exception {
		Jogador.getInstance("Rodrigo", "Tog");
		Jogador maria = Jogador.getInstance("Maria", "mama");
		Jogador.getInstance("Cintia", "cin");
		
		new TelaExibirRanking(maria).exibirTela();
	} //resultado do teste: (esperado) - ranking exibido com sucesso!	
}
