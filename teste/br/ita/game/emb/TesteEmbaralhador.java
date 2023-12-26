/* Observa��o: Embaralhador � uma classe abstrata que n�o pode ser instanciada para ser testada!
 * Como possui m�todos est�ticos, � poss�vel test�-los desde que se troque a visibiliade de private para protected  */

package br.ita.game.emb;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class TesteEmbaralhador {

	/* para realizar este teste � necess�rio trocar a visibilidade do m�todo simetrica para protected 
	@Test
	void simetrica() {
		String[] matriz1 = {"a"};
		assertTrue(Embaralhador.simetrica(matriz1));
		
		String[] matriz2 = {"b", "b"};
		assertTrue(Embaralhador.simetrica(matriz2));
		
		String[] matriz3 = {"c", "c", "c"};
		assertTrue(Embaralhador.simetrica(matriz3));
		
		String[] matriz4 = {"ba"};
		assertTrue(Embaralhador.simetrica(matriz4));
		
		String[] matriz5 = {"ca", "ca"};
		assertTrue(Embaralhador.simetrica(matriz5));
		
		String[] matriz6 = {"ce", "ce", "ce"};
		assertTrue(Embaralhador.simetrica(matriz6));
	
		String[] matriz7 = {"b", "a"};
		assertFalse(Embaralhador.simetrica(matriz7));
				
		String[] matriz8 = {"ba", "bu"};
		assertFalse(Embaralhador.simetrica(matriz8));
		
		String[] matriz9 = {"ba", "ba", "�u"};
		assertFalse(Embaralhador.simetrica(matriz9));	
	} */
	
	/* para realizar este teste � necess�rio trocar a visibilidade do m�todo addEspacamento para protected
	@Test
	void addEspacamento() {
		String[] matriz1 = {"a"};
		assertEquals("a", Embaralhador.addEspacamento(matriz1));
		
		String[] matriz2 = {"b", "b"};
		assertEquals("b b", Embaralhador.addEspacamento(matriz2));
		
		String[] matriz3 = {"c", "c", "c"};
		assertEquals("c c c", Embaralhador.addEspacamento(matriz3));
		
		String[] matriz4 = {"ba"};
		assertEquals("ba", Embaralhador.addEspacamento(matriz4));
		
		String[] matriz5 = {"ca", "ca"};
		assertEquals("ca ca", Embaralhador.addEspacamento(matriz5));
		
		String[] matriz6 = {"ce", "ce", "ce"};
		assertEquals("ce ce ce", Embaralhador.addEspacamento(matriz6));
	} */
	
	/* para realizar este teste � necess�rio trocar a visibilidade do m�todo saoDiferentes para protected
	@Test
	void saoDiferentes() {
		assertFalse(Embaralhador.saoDiferentes("a", "a"));
		assertFalse(Embaralhador.saoDiferentes("p a t o", "pato"));
		assertFalse(Embaralhador.saoDiferentes("pato", "p a t o"));
		assertFalse(Embaralhador.saoDiferentes("P A T O", "pato"));
		assertFalse(Embaralhador.saoDiferentes("p a t o", "PATO"));
		
		assertTrue(Embaralhador.saoDiferentes("a", "b"));
		assertTrue(Embaralhador.saoDiferentes("jac", "jap"));
	} */
	
	/* para realizar este teste � necess�rio trocar a visibilidade do m�todo embInvert para protected	
	@Test
	void testeEmbaralhamentoInvertido() {
		//System.out.println("\n******************** testeEmbaralhamentoInvertido() ********************");
		
		String[] matriz1 = {"p"};
		assertEquals("p", Embaralhador.embInvert(matriz1));
		
		String[] matriz2 = {"p", null};
		assertEquals("p", Embaralhador.embInvert(matriz2));
		
		String[] matriz3 = {"p", "a"};
		assertEquals("a p", Embaralhador.embInvert(matriz3));
		
		String[] matriz4 = {"p", "a", "t"};
		assertEquals("erro: m�todo embInvert s� para at� dois elementos!", Embaralhador.embInvert(matriz4));
		
		String[] matriz5 = {"ca"};
		assertEquals("ca", Embaralhador.embInvert(matriz5));
		
		String[] matriz6 = {"ca", null};
		assertEquals("ca", Embaralhador.embInvert(matriz6));
		
		String[] matriz7 = {"ca", "sa"};
		assertEquals("sa ca", Embaralhador.embInvert(matriz7));
		
		String[] matriz8 = {"bi", "to", "la"};
		assertEquals("erro: m�todo embInvert s� para at� dois elementos!", Embaralhador.embInvert(matriz8));
	} */
	
	/* para realizar este teste � necess�rio trocar a visibilidade do m�todo embAleat para protected
	@Test
	void testeEmbaralhamentoAleatorio() {
		//System.out.println("\n******************** embaralhamentoAleatorio() ********************");
		
		String[] matriz1 = {"a"};
		assertEquals("erro: m�todo embAleat s� para mais de dois elementos!", Embaralhador.embAleat(matriz1));
				
		String[] matriz2 = {"pai"};
		assertEquals("erro: m�todo embAleat s� para mais de dois elementos!", Embaralhador.embAleat(matriz2));
		
		String[] matriz3 = {"par", "is"};
		assertEquals("erro: m�todo embAleat s� para mais de dois elementos!", Embaralhador.embAleat(matriz3));
		
		String[] matriz4 = {"c", "c", "e"};
		assertNotEquals("c c e", Embaralhador.embAleat(matriz4));
				
		String[] matriz5 = {"bi", "go", "de"};
		assertNotEquals("bi go de", Embaralhador.embAleat(matriz5));
				
		String[] matriz6 = {"ba", "lei", "a"};
		assertNotEquals("ba lei a", Embaralhador.embAleat(matriz6));
				
		String[] matriz7 = {"ca", "be", "lo"};
		assertNotEquals("ca be lo", Embaralhador.embAleat(matriz7));
		
		String[] matriz8 = {"pa", "ra", "le", "le", "p�", "pe", "do"};
		assertNotEquals("pa ra le le p� pe do", Embaralhador.embAleat(matriz8));
	} */
	
	/* para realizar este teste � necess�rio transformar o m�todo embaralharMatriz em um m�todo est�tico  	
	@Test
	void embaralharMatriz() {
		//System.out.println("\n******************** testeEmbaralhar() ********************");
		
		String[] matriz1 = {"a"};
		assertEquals("a", Embaralhador.embaralharMatriz(matriz1));
		
		String[] matriz2 = {"a", "a"};
		assertEquals("a a", Embaralhador.embaralharMatriz(matriz2));
		
		String[] matriz3 = {"c", "c", "c"};
		assertEquals("c c c", Embaralhador.embaralharMatriz(matriz3));
		
		String[] matriz4 = {"D", "D", "D", "D"};
		assertEquals("D D D D", Embaralhador.embaralharMatriz(matriz4));
		
		String[] matriz5 = {"pa"};
		assertEquals("pa", Embaralhador.embaralharMatriz(matriz5));
		
		String[] matriz6 = {"pa", "pa"};
		assertEquals("pa pa", Embaralhador.embaralharMatriz(matriz6));
		
		String[] matriz7 = {"Te", "Te", "Te"};
		assertEquals("Te Te Te", Embaralhador.embaralharMatriz(matriz7));
		
		String[] matriz8 = {"ta", "ta", "ta", "ta"};
		assertEquals("ta ta ta ta", Embaralhador.embaralharMatriz(matriz8));
		
		String[] matriz9 = {"par", "is"};
		assertEquals("is par", Embaralhador.embaralharMatriz(matriz9));
		
		String[] matriz10 = {"sa", "ci"};
		assertEquals("ci sa", Embaralhador.embaralharMatriz(matriz10));
		
		String[] matriz11 = {"bi", "go", "de"};
		assertNotEquals("bi go de", Embaralhador.embaralharMatriz(matriz11));
				
		String[] matriz12 = {"ba", "lei", "a"};
		assertNotEquals("ba lei a", Embaralhador.embaralharMatriz(matriz12));
				
		String[] matriz13 = {"a", "ba", "ca", "xi"};
		assertNotEquals("a ba ca xi", Embaralhador.embaralharMatriz(matriz13));
		
		String[] matriz14 = {"pa", "ra", "le", "le", "p�", "pe", "do"};
		assertNotEquals("pa ra le le p� pe do", Embaralhador.embaralharMatriz(matriz14));
	} */
}
