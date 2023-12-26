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
			//com setVisible(false) tela não é exibida
	
	/*** testes que verificam o funcionamento do loop ***/
	@Test
	//objetivo: verificar se o loop é encerrado apenas ao se digitar "v" ou "V"
	void exibirTela1() {
		TelaNavGeral tg1 = new TelaNavGeral();
		tg1.exibirTela();
	}  //resultado do teste: "v" e "V" encerram o loop com sucesso
	@Test
	//objetivo: testar o encerramento do loop ("v" ou "V") com várias instancias da mesma tela
	void exibirTela2() {
		TelaNavGeral tg1 = new TelaNavGeral();
		tg1.addConteudo("Instância 1");
		
		TelaNavGeral tg2 = new TelaNavGeral();
		tg2.addConteudo("Instância 2");
		
		TelaNavGeral tg3 = new TelaNavGeral();
		tg3.addConteudo("Instância 3");	
		
		tg1.exibirTela();
		tg2.exibirTela();
		tg3.exibirTela();		
	}  //resultado do teste: "v" e "V" encerram o loop da tela em exibição, permitindo assim, a exibição da próxima tela
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
		//descreve ações
		tg1.addConteudo("tg1.limpaBarraMenu();");
		//exibir tela
		tg1.exibirTela();
	} //resultado do teste: sem menu, barra de menu não é impressa
	@Test
	void addMenu1() {
		TelaNavGeral tg1 = new TelaNavGeral();
		//adiciona menus
		tg1.addMenu("a", "ajuda");
		tg1.addMenu("i", "imprimir");
		tg1.addMenu("c", "cadastrar");	
		//descreve ações
		tg1.addConteudo("tg1.addMenu(\"a\", \"ajuda\");");
		tg1.addConteudo("tg1.addMenu(\"i\", \"imprimir\");");
		tg1.addConteudo("tg1.addMenu(\"c\", \"cadastrar\");");
		//exibir tela
		tg1.exibirTela();
	} //resultado do teste: novos menus foram inseridos; a ordem de inserção foi mantida
	@Test
	void addMenu2() {
		TelaNavGeral tg1 = new TelaNavGeral();
		//limpa barra de menu
		tg1.limpaBarraMenu();
		//adiciona menus
		tg1.addMenu("o", "operador");
		tg1.addMenu("a", "ajuda");
		tg1.addMenu("u", "usuário");
		//descreve ações
		tg1.addConteudo("tg1.limpaBarraMenu();");
		tg1.addConteudo("tg1.addMenu(\"o\", \"operador\");");
		tg1.addConteudo("tg1.addMenu(\"a\", \"ajuda\");");
		tg1.addConteudo("tg1.addMenu(\"u\", \"usuário\");");
		//exibir tela
		tg1.exibirTela();		
	} //resultado do teste: barra de menu foi limpa, novos menus foram inseridos; a ordem de inserção foi mantida
	
	/*** testa conteúdo ***/
	@Test
	void limpaConteudo() {
		TelaNavGeral tg1 = new TelaNavGeral();
		//adiciona conteúdo
		tg1.addConteudo("Este conteúdo será apagado e não poderá ser visto!");
		//apaga conteúdo
		tg1.limpaConteudo();
		//exibe tela
		tg1.exibirTela();
	} //resultado do teste: conteúdo foi apagado com sucesso
	
	@Test
	void setConteudo() {
		//cria o conteúdo a ser definido
		StringBuilder stb = new StringBuilder();
		stb.append("Teste do método setConteudo()" + "\n");
		stb.append("\t Este conteúdo foi inserido na janela através do método setConteudo()" + "\n");
		stb.append("\t Ao contrário do método addConteudo(), o método setConteudo() permite inserir varias linhas de uma única vez" + "\n");
		
		TelaNavGeral tg1 = new TelaNavGeral();
		//define conteúdo
		tg1.setConteudo(stb);
		//exibe tela
		tg1.exibirTela();
	} //resultado do teste: conteúdo inserido com sucesso
}
