package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import br.ita.game.Jogador;
import br.ita.game.emb.Embaralhador;
import br.ita.game.emb.FabricaEmbaralhador;
import br.ita.game.enu.BarStatusRot;
import br.ita.game.enu.GrauDificuldade;
import br.ita.game.enu.NivelExperiencia;
import br.ita.game.mec.FabricaMecanicaDoJogo;
import br.ita.game.mec.MecanicaDoJogo;

@SuppressWarnings("unused")
class TestaTelaPartidaGeral {
	
	@Test
	void setVisible() {
		TelaPartidaGeral tpg = new TelaPartidaGeral(null, null);
		tpg.setVisible(false);
		tpg.exibirTela();		
	} //resultado do teste: (esperado)
			//com setVisible(false) tela não é exibida
	
	/*** testa exibir tela com e sem jogador ***/
	@Test
	//sem jogador
	void exibirTela1() {
		TelaPartidaGeral tpg = new TelaPartidaGeral(null, null);
		tpg.exibirTela();
	} //resultado do teste: (esperado)
			//tela é exibida, barra de partida e barra de status não
	@Test
	//obs: jogador com nome, mas sem apelido
	void exibirTela2() throws Exception {
		Jogador jog = Jogador.getInstance("Rodrigo", "");
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, null);
		tpg.exibirTela();
	} //resultado do teste: (esperado)
			//nome é exibido: Jogador(a): Rodrigo
	@Test
	//obs: jogador com nome e apelido
	void exibirTela3() throws Exception {
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, null);
		tpg.exibirTela();
	} //resultado do teste: (esperado)
			//apelido é exibido: Jogador(a): Tog
	
	
	
	/*** testa exibir tela com jogador e mecânica ***/
	@Test
	//NivelExperiencia.INICIANTE
	//GrauDificuldade.FACIL
	void exibirTela4() throws Exception {
		//cria o jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria a mecânica
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, emb);
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, mec);
		tpg.exibirTela();
	} //resultado do teste: (esperado)
    		//Jogador(a): Tog
    		//Nível: Iniciante     Tipo Embaralhador: Sílabas     Multiplicador: 1x
    		//Dificuldade: Fácil (palpites sem limite)
	@Test
	//NivelExperiencia.INTERMEDIARIO
	//GrauDificuldade.NORMAL
	void exibirTela5() throws Exception {
		//cria o jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria a mecânica
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INTERMEDIARIO);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.NORMAL, emb);
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, mec);
		tpg.exibirTela();		
	} //resutado do teste: (esperado)
			//Jogador(a): Tog
    		//Nível: Intermediário     Tipo Embaralhador: Letras Padronizado     Multiplicador: 2x
    		//Dificuldade: Normal (palpites por palavra)
	
	@Test
	//NivelExperiencia.AVANCADO
	//GrauDificuldade.DIFICIL
	void exibirTela6() throws Exception {
		//cria o jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria a mecânica
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.AVANCADO);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.DIFICIL, emb);
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, mec);
		tpg.exibirTela();			
	} //resultado do teste: (esperado)
			//Jogador(a): Tog
    		//Nível: Avançado     Tipo Embaralhador: Letras Aleatório     Multiplicador: 3x
    		//Dificuldade: Difícil (palpites por partida)
	@Test
	//testa mecânica sem jogador
	void exibirTela7() {
		//cria a mecânica
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.AVANCADO);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.DIFICIL, emb);
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(null, mec);
		tpg.exibirTela();	
	} //resultado do teste: (esperado)
			//Nível: Avançado     Tipo Embaralhador: Letras Aleatório     Multiplicador: 3x
    		//Dificuldade: Difícil (palpites por partida)
		
	
	/*** testa barra de status ***/
	@Test
	//teste 1: barra de status vazia
	void setBarraStatus1() {
		//cria a barra de status
		LinkedHashMap<BarStatusRot, String> barraStatus = new LinkedHashMap<BarStatusRot, String>();
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(null, null);
		//define barra de status
		tpg.setBarraStatus(barraStatus);
		//exibe tela
		tpg.exibirTela();		
	} //resultado do teste: (esperado)
			//barra de status não é impressa	
	@Test
	//teste 2: barra de status com 1 elemento
	void setBarraStatus2() {
		//cria a barra de status
		LinkedHashMap<BarStatusRot, String> barraStatus = new LinkedHashMap<BarStatusRot, String>();
			barraStatus.put(BarStatusRot.PALPITES_UTILIZADOS, "0");
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(null, null);
		//define barra de status
		tpg.setBarraStatus(barraStatus);
		//exibe tela
		tpg.exibirTela();
	} //resultado do teste: (esperado)
			//barra de status é impressa: Palpites utilizados: 0
	@Test
	//teste 3: barra de status com 4 elementos (mas um deles null)
	void setBarraStatus4() throws Exception {
		//cria o jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria a mecânica
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, emb);		
		//cria a barra de status
		LinkedHashMap<BarStatusRot, String> barraStatus = new LinkedHashMap<BarStatusRot, String>();
			barraStatus.put(BarStatusRot.PALPITES_UTILIZADOS, "0");
			barraStatus.put(BarStatusRot.PALPITES_RESTANTES, null);
			barraStatus.put(BarStatusRot.PONTOS, "0");
			barraStatus.put(BarStatusRot.TEMPO, "5:00");
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, mec);
		//define barra de status
		tpg.setBarraStatus(barraStatus);
		//exibe tela
		tpg.exibirTela();					
	}  //resultado do teste: (esperado)
			//Jogador(a): Tog
    		//Nível: Iniciante     Tipo Embaralhador: Sílabas     Multiplicador: 1x
    		//Dificuldade: Fácil (palpites sem limite)
    		//-----------------------------------------------------------------
    		//Palpites utilizados: 0     Pontos marcados: 0     Tempo restante: 5:00
    		//-----------------------------------------------------------------
				
	@Test
	void limpaBarraStatus() throws Exception {
		//cria o jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria a mecânica
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.AVANCADO);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.DIFICIL, emb);		
		//cria a barra de status
		LinkedHashMap<BarStatusRot, String> barraStatus = new LinkedHashMap<BarStatusRot, String>();
			barraStatus.put(BarStatusRot.PALPITES_UTILIZADOS, "0");
			barraStatus.put(BarStatusRot.PALPITES_RESTANTES, null);
			barraStatus.put(BarStatusRot.PONTOS, "0");
			barraStatus.put(BarStatusRot.TEMPO, "5:00");
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, mec);
		//define barra de status
		tpg.setBarraStatus(barraStatus);
		//limpa barra de stauts
		tpg.limpaBarraStatus();
		//exibe tela
		tpg.exibirTela();		
	} //resultado do teste: (esperado)
			//barra de status não é exibida
}
