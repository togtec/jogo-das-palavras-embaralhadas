package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ita.game.Jogador;
import br.ita.game.emb.Embaralhador;
import br.ita.game.emb.FabricaEmbaralhador;
import br.ita.game.enu.GrauDificuldade;
import br.ita.game.enu.NivelExperiencia;
import br.ita.game.mec.FabricaMecanicaDoJogo;
import br.ita.game.mec.MecanicaDoJogo;

@SuppressWarnings("unused")
class TestaTelaPartidaEmCurso {
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");
	
	private Jogador jog;
	
	private Embaralhador embIniciante;
	private Embaralhador embIntermediario;
	private Embaralhador embAvancado;
	
	@BeforeEach
	void prepararTeste() throws Exception {
		//prepara arquivo
		fileJog.delete();
		fileJog.createNewFile();
		
		//prepara jogador
		jog = Jogador.getInstance("Sabrina", "Sab");
		
		//prepara embaralhador
		embIniciante = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		embIntermediario = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INTERMEDIARIO);
		embAvancado = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.AVANCADO);
	}
	
	@Test
	//objetivo do teste: verificar se a tela é exibida
	void exibirTela() {
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
		new TelaPartidaEmCurso(jog, mec).exibirTela();
	} //resultado do testes: (esperado) - No geral, tela exibida com sucesso

	@Test
	//objetivo do teste: verificar exibição da barra de status
	//obs: barra de status varia de acordo com o grau de dificuldade da partida
	void exibirBarraStatus() {
		MecanicaDoJogo mec1 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
		new TelaPartidaEmCurso(jog, mec1).exibirTela();
			//resultado: Palpites utilizados: 0     Pontos marcados: 0     Tempo restante: 05:00
		
		MecanicaDoJogo mec2 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.NORMAL, embIniciante);
		new TelaPartidaEmCurso(jog, mec2).exibirTela();
			//resultado: Palpites restantes: 0     Pontos marcados: 0     Tempo restante: 05:00
		
		MecanicaDoJogo mec3 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.DIFICIL, embIniciante);		
		new TelaPartidaEmCurso(jog, mec3).exibirTela();
			//resultado: Palpites restantes: 0     Pontos marcados: 0     Tempo restante: 05:00
	} 
		
	@Test
	//objetivo do teste: verificar a exibição do conteúdo
	//obs: conteúdo varia de acordo com o embaralhador (com embaralhador avançado, conteúdo exibe dica)
	void exibirConteudo() {
		MecanicaDoJogo mec1 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
		new TelaPartidaEmCurso(jog, mec1).exibirTela();
			//resultado: não exibe dica
		
		MecanicaDoJogo mec2 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIntermediario);
		new TelaPartidaEmCurso(jog, mec2).exibirTela();
			//resultado: não exibe dica
		
		MecanicaDoJogo mec3 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embAvancado);
		new TelaPartidaEmCurso(jog, mec3).exibirTela();
			//resultado: exibe dica pois o embaralhador é avançado		
	}
	
	@Test
	//objetivo do teste: verificar se a dinâmica avança mediante acerto
	void next1() {
		MecanicaDoJogo mec1 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
		new TelaPartidaEmCurso(jog, mec1).exibirTela();
	}  //resultado: (esperado) - jogador avança apenas mediante acerto
	
	@Test
	//objetivo do teste: verificar se a dinâmica avança mediante acerto
	void next2() {
		MecanicaDoJogo mec1 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIntermediario);
		new TelaPartidaEmCurso(jog, mec1).exibirTela();
	} //resultado: (esperado) - jogador avança apenas mediante acerto
	
	@Test
	//objetivo do teste: verificar se a dinâmica avança mediante acerto
	void next3() {
		MecanicaDoJogo mec1 = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embAvancado);
		new TelaPartidaEmCurso(jog, mec1).exibirTela();		
	} //resultado: (esperado) - jogador avança mediante acerto
}
