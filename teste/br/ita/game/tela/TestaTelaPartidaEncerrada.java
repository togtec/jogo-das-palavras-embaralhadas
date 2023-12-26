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
class TestaTelaPartidaEncerrada {
	private static final File fileJog = new File("src/br/ita/game/files/jog.ser");

	@BeforeEach
	void prepararTeste() throws Exception {
		//prepara arquivo
		fileJog.delete();
		fileJog.createNewFile();
	}
	
	@Test
	void exibirTela() throws Exception {
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		Embaralhador embIniciante = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, embIniciante);
		
		mec.iniciar();
		new TelaPartidaEncerrada(jog, mec).exibirTela();
		mec.concluir();
	} //resultado do teste: (esperado) - Relatório de progresso é exibido, mas no começo da dinâmica, ainda está vazio:
    		//Jogador(a): Tog
    		//Nível: Iniciante     Tipo Embaralhador: Sílabas     Multiplicador: 1x
    		//Dificuldade: Fácil (palpites sem limite)
    		//-----------------------------------------------------------------
    		//Relatório de Progresso:

}
