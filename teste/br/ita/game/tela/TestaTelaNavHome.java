package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class TestaTelaNavHome {

	@Test
	//a classe TelaNavHome instancia e exibe: 
		 //TelaNavCursos
		 //TelaNavSobreEmpresa
	//ao fechar TelaNavCursos ou TelaNavSobreEmpresa o usu�rio dever� retornar para TelaNavHome.
	void testaNavegacaoEntreTelas() {
		 new TelaNavHome().exibirTela();
	} //resultado do teste: Navega��o testada com sucesso!
}
