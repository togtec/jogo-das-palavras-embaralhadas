package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ita.game.Jogador;
import br.ita.game.emb.Embaralhador;
import br.ita.game.emb.FabricaEmbaralhador;
import br.ita.game.enu.GrauDificuldade;
import br.ita.game.enu.NivelExperiencia;
import br.ita.game.mec.FabricaMecanicaDoJogo;
import br.ita.game.mec.MecanicaDoJogo;

@SuppressWarnings("unused")
class TestaTelaIniciarPartida {

	@Test
	void navegacao() throws Exception {
		//cria o jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria a mecânica
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		MecanicaDoJogo mec = FabricaMecanicaDoJogo.getMecanicaDoJogo(GrauDificuldade.FACIL, emb);		
		
		new TelaIniciarPartida(jog, mec).exibirTela();
	} //resultado do teste: (esperado)
			//opção 1: -> chama TelaPartidaEmCurso
			//opção 2: -> chama TelaConfiguracoes

}
