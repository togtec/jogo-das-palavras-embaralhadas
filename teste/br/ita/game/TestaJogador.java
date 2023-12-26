package br.ita.game;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ita.game.enu.PartidaStatus;

class TestaJogador {
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");
	
	/*** método utilitário (não é método de teste) ***/
	//imprime jogador
	void imprimir(Jogador jogador) {
		System.out.println(jogador.getId() + " " + jogador.getNome() + " " + "(" + jogador.getApelido() + ")");	
	}
	//imprime arquivo
	void impListJogDoArq() throws Exception {
		String[] listaJogadores = AdmJogadorFile.getListaJogadores();
		for (String jogador : listaJogadores) {
			System.out.println("\t " + jogador);
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
	//nome não pode ser null, nem vazio, nem uma sequência de caracteres em branco
	void nomeValido() {
		assertFalse(Jogador.nomeValido(null));
		assertFalse(Jogador.nomeValido(""));
		assertFalse(Jogador.nomeValido(" "));
		assertFalse(Jogador.nomeValido("  "));
		assertFalse(Jogador.nomeValido("   "));
		assertTrue(Jogador.nomeValido("Maria"));
		assertTrue(Jogador.nomeValido("   Maria"));
		assertTrue(Jogador.nomeValido("Maria   "));
		assertTrue(Jogador.nomeValido("  Maria   "));
	}
	
	@Test
	//apelido não pode ser null, nem uma sequência de caracteres em branco
	void apelidoValido() {
		assertFalse(Jogador.apelidoValido(null));
		assertTrue(Jogador.apelidoValido("")); //obs: vazio ("") é válido para apelido
		assertFalse(Jogador.apelidoValido(" "));
		assertFalse(Jogador.apelidoValido("  "));
		assertFalse(Jogador.apelidoValido("   "));
		assertTrue(Jogador.apelidoValido("pi     "));
		assertTrue(Jogador.apelidoValido("     ta"));
		assertTrue(Jogador.apelidoValido("     tata          "));
	}
	
	@Test
	//neste primeiro teste o método getInstance lança uma exceção por não conseguir serializar o objeto Jogador no arquivo
	void getInstance1() {
		//apaga arquivo
		fileJog.delete();
		//cria jogador
		Exception ex = assertThrows(Exception.class, () -> Jogador.getInstance("Rodrigo", "Tog"));
		System.out.println(ex.getMessage());
	}
	@Test
	//neste segundo teste o método getInstance lança uma exceção por nome inválido
	void getInstance2() {
		Exception ex1 = assertThrows(Exception.class, () -> Jogador.getInstance(null, "Tog"));
		Exception ex2 = assertThrows(Exception.class, () -> Jogador.getInstance("", "Tog"));
		Exception ex3 = assertThrows(Exception.class, () -> Jogador.getInstance(" ", "Tog"));
		Exception ex4 = assertThrows(Exception.class, () -> Jogador.getInstance("  ", "Tog"));
		Exception ex5 = assertThrows(Exception.class, () -> Jogador.getInstance("   ", "Tog"));
		System.out.println(ex1); System.out.println(ex2); System.out.println(ex3); System.out.println(ex4); System.out.println(ex5);
		try {
			impListJogDoArq();
		} catch (Exception ex) { }
	}	
	@Test
	//neste terceiro teste o método getInstance retorna uma instância de Jogador (nomes válidos)
	void getInstance3() throws Exception {
		assertNotNull(Jogador.getInstance("Rodrigo", "Tog"));
		assertNotNull(Jogador.getInstance("    Maria", "mama"));
		assertNotNull(Jogador.getInstance("Pedro    ", "pp"));
		assertNotNull(Jogador.getInstance("   Santiago    ", "santo"));
		impListJogDoArq();		
	}
	@Test
	//neste quarto teste criamos jogadores com apelidos inválidos
	void getInstance4() throws Exception {
		assertNotNull(Jogador.getInstance("Rodrigo", null));
		assertNotNull(Jogador.getInstance("Maria", "")); //obs: vazio ("") é válido para apelido
		assertNotNull(Jogador.getInstance("Pedro", " "));
		assertNotNull(Jogador.getInstance("Santiago", "  "));
		assertNotNull(Jogador.getInstance("Sabrina", "   "));
		assertNotNull(Jogador.getInstance("Eleonor", "le     "));
		assertNotNull(Jogador.getInstance("Thiago", "     Ti"));
		assertNotNull(Jogador.getInstance("Marcos", "     Mac     "));
		impListJogDoArq();
	}
	
	@Test
	void setId() throws Exception {
		//cria Jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		assertEquals(1, jog.getId());
		
		//tenta troca de id
		jog.setId(100);
		//troca de id recusada - jogador mantém id pois já tinha id definitivo (id diferente de 0)
		assertEquals(1, jog.getId());
	}
	
	@Test
	//retorna  1 se o nome do jogador foi trocado no arquivo
	//retorna -1 se o nome do jogador não foi trocado no arquivo (novoNome é inválido ou igual ao nome atual)	
	void setNome() throws Exception {
		//cria Jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");

		//trocas inválidas
		assertEquals(-1, jog.setNome("Rodrigo")); //recusa a troca pois nome já é Rodrigo
		
		assertEquals(-1, jog.setNome(null));
		assertEquals("Rodrigo", jog.getNome());
		
		assertEquals(-1, jog.setNome(""));
		assertEquals("Rodrigo", jog.getNome());
		
		assertEquals(-1, jog.setNome(" "));
		assertEquals("Rodrigo", jog.getNome());
		
		assertEquals(-1, jog.setNome("  "));
		assertEquals("Rodrigo", jog.getNome());
		
		assertEquals(-1, jog.setNome("   "));
		assertEquals("Rodrigo", jog.getNome());
			
		//trocas válidas
		assertEquals(1, jog.setNome("pedro americo"));
		assertEquals("pedro americo", jog.getNome());
		
		assertEquals(1, jog.setNome("pedro américo")); //foi acrescentado o acento
		assertEquals("pedro américo", jog.getNome());
		
		assertEquals(1, jog.setNome("Pedro Américo")); //acrescentado iniciais maiúsculas
		assertEquals("Pedro Américo", jog.getNome());		
		
		assertEquals(1, jog.setNome("Pedro")); //sobrenome retirado
		assertEquals("Pedro", jog.getNome());
				
		assertEquals(1, jog.setNome("   Maria")); //com espaço em branco à esquerda
		assertEquals("Maria", jog.getNome());
		
		assertEquals(1, jog.setNome("Mauricio   ")); //com espaço em branco à direita
		assertEquals("Mauricio", jog.getNome());
		
		assertEquals(1, jog.setNome("   Cintia   ")); //com espaço em branco em ambos os lados
		assertEquals("Cintia", jog.getNome());
		
		//troca válida com arquivo indisponível
		fileJog.delete();
		Exception ex = assertThrows(Exception.class, () -> jog.setNome("Vania"));
		System.out.println(ex);
	}
	
	@Test
	//retorna  1 se o apelido do jogador foi trocado no arquivo 
	//retorna -1 se o apelido do jogador não foi trocado no arquivo (novo apelido é inválido ou igual ao apelido atual)
	void setApelido() throws Exception {
		//cria Jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		
		//trocas inválidas
		assertEquals(-1, jog.setApelido("Tog")); //recusa a troca pois apelido já é Tog
		
		assertEquals(-1, jog.setApelido(null));
		assertEquals("Tog", jog.getApelido());
			
		assertEquals(-1, jog.setApelido(" "));
		assertEquals("Tog", jog.getApelido());
		
		assertEquals(-1, jog.setApelido("  "));
		assertEquals("Tog", jog.getApelido());
		
		assertEquals(-1, jog.setApelido("   "));
		assertEquals("Tog", jog.getApelido());
			
		//trocas válidas
		assertEquals(1, jog.setApelido("")); //vazio é válido para apelido
		assertEquals("", jog.getApelido());
		
		assertEquals(1, jog.setApelido("rod tog"));
		assertEquals("rod tog", jog.getApelido());
		
		assertEquals(1, jog.setApelido("rod tóg")); //acrescenta acento
		assertEquals("rod tóg", jog.getApelido());
		
		assertEquals(1, jog.setApelido("tóg")); //retira primeira parte
		assertEquals("tóg", jog.getApelido());
		
		assertEquals(1, jog.setApelido("Tóg")); //acrescenta inicial em maiúscula
		assertEquals("Tóg", jog.getApelido());	
		
		assertEquals(1, jog.setApelido("   ro")); //com espaço em branco à esquerda
		assertEquals("ro", jog.getApelido());
		
		assertEquals(1, jog.setApelido("mo   ")); //com espaço em branco à direita
		assertEquals("mo", jog.getApelido());
		
		assertEquals(1, jog.setApelido("   prof   ")); //com espaço em branco em ambos os lados
		assertEquals("prof", jog.getApelido());
		
		//tenta troca válida com arquivo indisponível
		fileJog.delete();
		Exception ex = assertThrows(Exception.class, () -> jog.setApelido("tg"));
		System.out.println(ex);
	}
	
	@Test
	void getId() throws Exception {
		//cria Jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");		
		assertEquals(1, jog.getId());
	}
	
	@Test() 
	void getNome() throws Exception {
		//cria Jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");		
		assertEquals("Rodrigo", jog.getNome());
	}
	
	@Test
	void getApelido() throws Exception {
		//cria Jogador
		Jogador jog = Jogador.getInstance("Maria", "mamá");
		assertNotEquals("mama", jog.getApelido());
		assertEquals("mamá", jog.getApelido());
	}
	
	@Test
	//testa métodos: 
	//	Jogador.incScore: incScore(PartidaStatus partidaStatus, int ptsMarcados)
	//	AdmJogadorFile.getListaJogadores, 
	//  AdmJogadorFile.getRankingJogadores
	void incScore() throws Exception {
		//cria Jogadores
		Jogador jog1 = Jogador.getInstance("Rodrigo", "Tog");
			assertEquals(0, jog1.getPontos());
			assertEquals(0, jog1.getPartidas());
			assertEquals(0, jog1.getVitorias());
		
		Jogador jog2 = Jogador.getInstance("Maria", "má");
		//18 pts marcados, 10 partidas disputadas, 3 vitórias
			jog2.incScore(PartidaStatus.VITORIA, 6);
			jog2.incScore(PartidaStatus.VITORIA, 6);
			jog2.incScore(PartidaStatus.VITORIA, 2);
			jog2.incScore(PartidaStatus.DERROTA, 0);
			jog2.incScore(PartidaStatus.DERROTA, 0);
			jog2.incScore(PartidaStatus.DERROTA, 0);
			jog2.incScore(PartidaStatus.DERROTA, 0);
			jog2.incScore(PartidaStatus.DERROTA, 1);
			jog2.incScore(PartidaStatus.DERROTA, 2);
			jog2.incScore(PartidaStatus.DERROTA, 1);
			assertEquals(18, jog2.getPontos());
			assertEquals(10, jog2.getPartidas());
			assertEquals(3, jog2.getVitorias());
		
		Jogador jog3 = Jogador.getInstance("Cintia", "Cin");
		//25 pts marcados, 5 partidas disputadas, 3 vitórias
			jog3.incScore(PartidaStatus.VITORIA, 6);
			jog3.incScore(PartidaStatus.VITORIA, 6);
			jog3.incScore(PartidaStatus.VITORIA, 6);
			jog3.incScore(PartidaStatus.DERROTA, 4);
			jog3.incScore(PartidaStatus.DERROTA, 3);
			assertEquals(25, jog3.getPontos());
			assertEquals(5, jog3.getPartidas());
			assertEquals(3, jog3.getVitorias());

		Jogador jog4 = Jogador.getInstance("Pedro", "pp");
		//29 pts marcados, 7 partidas disputadas, 4 vitórias
			jog4.incScore(PartidaStatus.VITORIA, 5);
			jog4.incScore(PartidaStatus.VITORIA, 5);
			jog4.incScore(PartidaStatus.VITORIA, 5);
			jog4.incScore(PartidaStatus.VITORIA, 5);
			jog4.incScore(PartidaStatus.DERROTA, 3);
			jog4.incScore(PartidaStatus.DERROTA, 3);
			jog4.incScore(PartidaStatus.DERROTA, 3);
			assertEquals(29, jog4.getPontos());
			assertEquals(7, jog4.getPartidas());
			assertEquals(4, jog4.getVitorias());		
		
		//testa método AdmJogadorFile.getListaJogadores
		String[] listaJogadores = AdmJogadorFile.getListaJogadores();
		for (String jogador : listaJogadores) {
			System.out.println("\t" + jogador);
		}
		
		System.out.println();
		
		//testa método AdmJogadorFile.getRankingJogadores
		String[] rankingJogadores = AdmJogadorFile.getRankingJogadores(null);
		for (String jogador : rankingJogadores) {
			System.out.println("\t" + jogador);
		}	
	}
	
	@Test
	void hashCode_e_equals() throws Exception {
		//cria jogadores
		Jogador rodrigo = Jogador.getInstance("Rodrigo", "Tog");
			assertEquals(1, rodrigo.getId());
		Jogador maria = Jogador.getInstance("Maria", "má");
			assertEquals(2, maria.getId());
			
		//recria arquivo
		fileJog.delete(); //apaga arquivo	
		try {             //recria arquivo
			fileJog.createNewFile();
		} catch(IOException ex) { System.out.println(ex.getMessage()); }		
		
		//cria jogador
		Jogador cintia = Jogador.getInstance("Cintia", "cin");
			assertEquals(1, cintia.getId());
		
		//jogadores com o mesmo id possuem o mesmo número de hash
		assertEquals(rodrigo.hashCode(), cintia.hashCode());
		//jogadores com ids diferentes possuem números de hash diferentes
		assertNotEquals(rodrigo.hashCode(), maria.hashCode());
		
		//jogadores com o mesmo id são considerados iguais
		assertTrue(rodrigo.equals(cintia));
		//jogadores com ids diferentes são considerados diferentes
		assertFalse(rodrigo.equals(maria));
	}
}
