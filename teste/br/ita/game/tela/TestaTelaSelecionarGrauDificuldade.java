package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ita.game.Jogador;
import br.ita.game.emb.Embaralhador;
import br.ita.game.emb.FabricaEmbaralhador;
import br.ita.game.enu.NivelExperiencia;

@SuppressWarnings("unused")
class TestaTelaSelecionarGrauDificuldade {

	@Test
	void testaNavegacao() throws Exception {
		//cria jogador
		Jogador jog = Jogador.getInstance("Rodrigo", "Tog");
		//cria o embaralhador
		Embaralhador emb = FabricaEmbaralhador.getEmbaralhador(NivelExperiencia.INICIANTE);
		
		new TelaSelecionarGrauDificuldade(jog, emb).exibirTela();
	} //resultado do teste: (esperado)
			//op��o 1: -> chama TelaIniciarPartida
			//op��o 2: -> chama TelaIniciarPartida
			//op��o 3: -> chama TelaIniciarPartida
			//op��o a: -> chama TelaAjudaSelecionarGrauDificuldade

}
