package br.ita.game.tela;

import br.ita.game.Jogador;
import br.ita.game.emb.Embaralhador;
import br.ita.game.enu.BarPartRot;
import br.ita.game.enu.GrauDificuldade;
import br.ita.game.mec.FabricaMecanicaDoJogo;

public class TelaSelecionarGrauDificuldade extends TelaPartida {
	private Embaralhador emb;
	
	//construtor
	public TelaSelecionarGrauDificuldade(Jogador jogador, Embaralhador emb) {
		super("Selecionar Grau de Dificuldade da Partida", jogador, null); //null -> mecânica
		this.emb = emb;
		
		addMenu("a", "ajuda");
		
		addConteudo("Por favor selecione o grau de dificuldade da partida:");
		addConteudo("");
		addConteudo("1- Grau Fácil");
		addConteudo("2- Grau Normal");
		addConteudo("3- Grau Difícil");
	}

	@Override
	//método de implementação obrigatória (abstrato na superclasse)
	protected void processaCmd(String cmd) {
		if (cmd.equalsIgnoreCase("1")) {
			selGrauDif(GrauDificuldade.FACIL);
			
		} else if (cmd.equalsIgnoreCase("2")) {
			selGrauDif(GrauDificuldade.NORMAL);
			
		} else if (cmd.equalsIgnoreCase("3")) {
			selGrauDif(GrauDificuldade.DIFICIL);
			
		} else if (cmd.equalsIgnoreCase("a")) {
			new TelaAjudaSelecionarGrauDificuldade().exibirTela();
		}
	}
	
	//método impBarraPartida precisa ser sobrescrito para imprimir os dados do embaralhador
	@Override
	protected void impBarraPartida() {
		//linha 1
		//se o apelido não for vazio
		if( !(jogador.getApelido().contentEquals(""))) {
			//imprime apelido
			System.out.println(TelaNav.margemEsquerda + BarPartRot.JOGADOR.toString() + ": " + jogador.getApelido()); //imprime margem esquerda
		} else {
			//senão, imprime nome
			System.out.println(TelaNav.margemEsquerda + BarPartRot.JOGADOR.toString() + ": " + jogador.getApelido()); //imprime margem esquerda
		}
		
		//linha 2
		System.out.print(TelaNav.margemEsquerda + BarPartRot.NIVEL_EXPERIENCIA.toString() + ": " + emb.getNivelExp());
		System.out.print(TelaNav.margemEsquerda + BarPartRot.TIPO_EMBARALHADOR.toString() + ": " + emb.getTipoEmb());
		System.out.print(TelaNav.margemEsquerda + BarPartRot.MULTIPLICADOR.toString() + ": " + Integer.toString(emb.getMult()) + "x");
		System.out.println(); //imprime quebra de linha
		
		//linha divisória
		System.out.println(getLinhaDivisoria(TelaNav.margemEsquerda, TelaNav.separadorInterno)); //imprime linha divisória
		incQtdLinhasImp(3);
	}
	
	private void selGrauDif(GrauDificuldade grauDif) {
		new TelaIniciarPartida(jogador, FabricaMecanicaDoJogo.getMecanicaDoJogo(grauDif, emb)).exibirTela();
	}	
}
