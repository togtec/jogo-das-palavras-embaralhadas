package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import br.ita.game.AdmJogadorFile;

@SuppressWarnings("unused")
class TestaTelaCriarJogador {
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");
	
	@Test
	//o que deve ser testado:
		//nome não pode ser vazio, nem uma sequência de caracteres em branco
		//apelido não pode ser uma sequência de caracteres em branco (más pode ser vazio)
	void testaTelaSemArquivo() {
		fileJog.delete(); //apaga arquivo
		
		new TelaCriarJogador().exibirTela();
		//resultado do teste: (esperado)
			//TelaCriarJogador só avança para o método criarJogador() se o nome e o apelido forem válidos   
	}  		//pelo fato de o arquivo ter sido apagado, tela exibie mensagem de erro: 

	@Test
	//o que deve ser testado:
		//criar 1 Jogador: Rodrigo (Tog)
	void testaTelaComArquivo() throws Exception {
		fileJog.delete(); //apaga arquivo	
		try {             //recria arquivo
			fileJog.createNewFile();
		} catch(IOException ex) { System.out.println(ex.getMessage()); }
		
		new TelaCriarJogador().exibirTela();
		
		//exibe conteúdo do arquivo para verificar se jogador foi realmente criado
		String[] listaJogadores = AdmJogadorFile.getListaJogadores();
		System.out.println(TelaNav.margemEsquerda + listaJogadores[0]);
	} //resultado do teste: (esperado) - tela exibe mensagem: "Parabéns! Jogador(a) Rodrigo criado(a) com sucesso!"
}
