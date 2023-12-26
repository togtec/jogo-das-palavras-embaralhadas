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
			//com setVisible(false) tela n�o � exibida
	
	/*** testa exibir tela com e sem jogador ***/
	@Test
	//sem jogador
	void exibirTela1() {
		TelaPartidaGeral tpg = new TelaPartidaGeral(null, null);
		tpg.exibirTela();
	} //resultado do teste: (esperado)
			//tela � exibida, barra de partida e barra de status n�o
	@Test
	//obs: jogador com nome, mas sem apelido
	void exibirTela2() throws Exception {
		Jogador jog = Jogador.getInstance("Rodrigo", "");
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, null);
		tpg.exibirTela();
	} //resultado do teste: (esperado)
			//nome � exibido: Jogador(a): Rodrigo
	@Test
	//obs: jogador com nome e apelido
	void exibirTela3() throws Exception {
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, null);
		tpg.exibirTela();
	} //resultado do teste: (esperado)
			//apelido � exibido: Jogador(a): Tog
	
	
	
	/*** testa exibir tela com jogador e mec�nica ***/
	@Test
	//NivelExperiencia.INICIANTE
	//GrauDificuldade.FACIL
	void exibirTela4() throws Exception {
		//cria o jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria a mec�nica
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, emb);
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, mec);
		tpg.exibirTela();
	} //resultado do teste: (esperado)
    		//Jogador(a): Tog
    		//N�vel: Iniciante     Tipo Embaralhador: S�labas     Multiplicador: 1x
    		//Dificuldade: F�cil (palpites sem limite)
	@Test
	//NivelExperiencia.INTERMEDIARIO
	//GrauDificuldade.NORMAL
	void exibirTela5() throws Exception {
		//cria o jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria a mec�nica
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INTERMEDIARIO);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.NORMAL, emb);
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, mec);
		tpg.exibirTela();		
	} //resutado do teste: (esperado)
			//Jogador(a): Tog
    		//N�vel: Intermedi�rio     Tipo Embaralhador: Letras Padronizado     Multiplicador: 2x
    		//Dificuldade: Normal (palpites por palavra)
	
	@Test
	//NivelExperiencia.AVANCADO
	//GrauDificuldade.DIFICIL
	void exibirTela6() throws Exception {
		//cria o jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria a mec�nica
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.AVANCADO);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.DIFICIL, emb);
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(jog, mec);
		tpg.exibirTela();			
	} //resultado do teste: (esperado)
			//Jogador(a): Tog
    		//N�vel: Avan�ado     Tipo Embaralhador: Letras Aleat�rio     Multiplicador: 3x
    		//Dificuldade: Dif�cil (palpites por partida)
	@Test
	//testa mec�nica sem jogador
	void exibirTela7() {
		//cria a mec�nica
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.AVANCADO);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.DIFICIL, emb);
		//cria tela
		TelaPartidaGeral tpg = new TelaPartidaGeral(null, mec);
		tpg.exibirTela();	
	} //resultado do teste: (esperado)
			//N�vel: Avan�ado     Tipo Embaralhador: Letras Aleat�rio     Multiplicador: 3x
    		//Dificuldade: Dif�cil (palpites por partida)
		
	
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
			//barra de status n�o � impressa	
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
			//barra de status � impressa: Palpites utilizados: 0
	@Test
	//teste 3: barra de status com 4 elementos (mas um deles null)
	void setBarraStatus4() throws Exception {
		//cria o jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria a mec�nica
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
    		//N�vel: Iniciante     Tipo Embaralhador: S�labas     Multiplicador: 1x
    		//Dificuldade: F�cil (palpites sem limite)
    		//-----------------------------------------------------------------
    		//Palpites utilizados: 0     Pontos marcados: 0     Tempo restante: 5:00
    		//-----------------------------------------------------------------
				
	@Test
	void limpaBarraStatus() throws Exception {
		//cria o jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria a mec�nica
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
			//barra de status n�o � exibida
}
