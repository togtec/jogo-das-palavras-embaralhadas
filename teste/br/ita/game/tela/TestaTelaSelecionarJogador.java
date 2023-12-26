package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ita.game.Jogador;

@SuppressWarnings("unused")
class TestaTelaSelecionarJogador {
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");
	
	@BeforeEach
	void preparaArquivo() {		
		fileJog.delete(); //apaga arquivo	
		try {             //recria arquivo
			fileJog.createNewFile();
		} catch(IOException ex) { System.out.println(ex.getMessage()); }	
	}	

	@Test
	void testaTelaSemArquivo() {
		fileJog.delete(); //apaga arquivo
		
		new TelaSelecionarJogador().exibirTela();
	} //resultado do teste: (esperado) - tela exibe mensagem de erro ao usu�rio

	@Test
	void testaComArquivoVazio() {
		new TelaSelecionarJogador().exibirTela();
	} //resultado do teste: (esperado) - tela exibe mensagem de aviso ao usu�rio:
    		//Nenhum(a) jogador(a) encontrado(a)!
    		//Favor retornar e criar um(a) novo(a) jogador(a)!
	
	@Test
	//o que deve ser testado:
		//1- verificar se os jogadores s�o exibidos na tela
		//2- escolher jogadores inv�lidos: digitar letras e ids inexistentes
		//3- escolher um jogador v�lido: nesse caso, tela TelaSelecionarNivelExperiencia deve ser exibida
	void testaComArquivoEjogadores() throws Exception {
		Jogador.getInstance("Rodrigo Tognetta", "Tog");
		Jogador.getInstance("Maria", "mama");
		Jogador.getInstance("Snake Sanders", "V�bora");
		Jogador.getInstance("Laura", "");
		
		new TelaSelecionarJogador().exibirTela();
	} //resutado do teste: (esperado) 
		//exibe lista de jogadores
		//ao se digitar o id do jogador escolhido, avan�a para a pr�xima tela: TelaSelecionarNivelExperiencia
	
	
}
