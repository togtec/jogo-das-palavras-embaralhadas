package br.ita.game.tela;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class TestaTelaNavGeral {
	@Test
	void setVisible() {
		TelaNavGeral tg1 = new TelaNavGeral();
		tg1.setVisible(false);
		tg1.exibirTela();
	} //resultado do teste: (esperado)
			//com setVisible(false) tela n�o � exibida
	
	/*** testes que verificam o funcionamento do loop ***/
	@Test
	//objetivo: verificar se o loop � encerrado apenas ao se digitar "v" ou "V"
	void exibirTela1() {
		TelaNavGeral tg1 = new TelaNavGeral();
		tg1.exibirTela();
	}  //resultado do teste: "v" e "V" encerram o loop com sucesso
	@Test
	//objetivo: testar o encerramento do loop ("v" ou "V") com v�rias instancias da mesma tela
	void exibirTela2() {
		TelaNavGeral tg1 = new TelaNavGeral();
		tg1.addConteudo("Inst�ncia 1");
		
		TelaNavGeral tg2 = new TelaNavGeral();
		tg2.addConteudo("Inst�ncia 2");
		
		TelaNavGeral tg3 = new TelaNavGeral();
		tg3.addConteudo("Inst�ncia 3");	
		
		tg1.exibirTela();
		tg2.exibirTela();
		tg3.exibirTela();		
	}  //resultado do teste: "v" e "V" encerram o loop da tela em exibi��o, permitindo assim, a exibi��o da pr�xima tela
	@Test
	//objetivo: testar encerramento com outra tecla de retorno (s)
	void exibirTela3() {
		TelaNavGeral tg1 = new TelaNavGeral();
		tg1.limpaBarraMenu();
		tg1.addMenu("s", "sair");
		tg1.setReturnKey("s");
		tg1.exibirTela();
	}

	/*** testa barra de menu ***/
	@Test
	void limpaBarraMenu() {
		TelaNavGeral tg1 = new TelaNavGeral();
		//limpa barra de menu
		tg1.limpaBarraMenu();		
		//descreve a��es
		tg1.addConteudo("tg1.limpaBarraMenu();");
		//exibir tela
		tg1.exibirTela();
	} //resultado do teste: sem menu, barra de menu n�o � impressa
	@Test
	void addMenu1() {
		TelaNavGeral tg1 = new TelaNavGeral();
		//adiciona menus
		tg1.addMenu("a", "ajuda");
		tg1.addMenu("i", "imprimir");
		tg1.addMenu("c", "cadastrar");	
		//descreve a��es
		tg1.addConteudo("tg1.addMenu(\"a\", \"ajuda\");");
		tg1.addConteudo("tg1.addMenu(\"i\", \"imprimir\");");
		tg1.addConteudo("tg1.addMenu(\"c\", \"cadastrar\");");
		//exibir tela
		tg1.exibirTela();
	} //resultado do teste: novos menus foram inseridos; a ordem de inser��o foi mantida
	@Test
	void addMenu2() {
		TelaNavGeral tg1 = new TelaNavGeral();
		//limpa barra de menu
		tg1.limpaBarraMenu();
		//adiciona menus
		tg1.addMenu("o", "operador");
		tg1.addMenu("a", "ajuda");
		tg1.addMenu("u", "usu�rio");
		//descreve a��es
		tg1.addConteudo("tg1.limpaBarraMenu();");
		tg1.addConteudo("tg1.addMenu(\"o\", \"operador\");");
		tg1.addConteudo("tg1.addMenu(\"a\", \"ajuda\");");
		tg1.addConteudo("tg1.addMenu(\"u\", \"usu�rio\");");
		//exibir tela
		tg1.exibirTela();		
	} //resultado do teste: barra de menu foi limpa, novos menus foram inseridos; a ordem de inser��o foi mantida
	
	/*** testa conte�do ***/
	@Test
	void limpaConteudo() {
		TelaNavGeral tg1 = new TelaNavGeral();
		//adiciona conte�do
		tg1.addConteudo("Este conte�do ser� apagado e n�o poder� ser visto!");
		//apaga conte�do
		tg1.limpaConteudo();
		//exibe tela
		tg1.exibirTela();
	} //resultado do teste: conte�do foi apagado com sucesso
	
	@Test
	void setConteudo() {
		//cria o conte�do a ser definido
		StringBuilder stb = new StringBuilder();
		stb.append("Teste do m�todo setConteudo()" + "\n");
		stb.append("\t Este conte�do foi inserido na janela atrav�s do m�todo setConteudo()" + "\n");
		stb.append("\t Ao contr�rio do m�todo addConteudo(), o m�todo setConteudo() permite inserir varias linhas de uma �nica vez" + "\n");
		
		TelaNavGeral tg1 = new TelaNavGeral();
		//define conte�do
		tg1.setConteudo(stb);
		//exibe tela
		tg1.exibirTela();
	} //resultado do teste: conte�do inserido com sucesso
}
