/* A classe TelaPartida (subclasse de TelaNav) cria duas novas estruturas:
  		barra de partida
  		barra de status */

package br.ita.game.tela;

import java.util.LinkedHashMap;
import br.ita.game.Jogador;
import br.ita.game.enu.BarPartRot;
import br.ita.game.enu.BarStatusRot;
import br.ita.game.mec.MecanicaDoJogo;

public abstract class TelaPartida extends TelaNav {
	protected Jogador jogador = null;
	protected MecanicaDoJogo mec = null;
	//ao contrário de HashMap, LinkedHashMap matém a ordem de inserção
	private LinkedHashMap<BarStatusRot, String> barraStatus = null;
	
	//método construtor
	public TelaPartida(String subtitulo, Jogador jogador, MecanicaDoJogo mec) {
		super(subtitulo);
		//inicia variáveis de instância
		this.jogador = jogador;
		this.mec = mec;
	}

	//imprime barra de partida
	protected void impBarraPartida() {
		boolean jogadorFoiImpresso = false;
		boolean mecanicaFoiImpressa = false;

		//linha 1
		if (jogador != null) {
			//se o apelido não for vazio
			if( !(jogador.getApelido().contentEquals(""))) {
				//imprime apelido
				impItemBarra(BarPartRot.JOGADOR.toString(), jogador.getApelido());
			} else {
				//senão, imprime nome
				impItemBarra(BarPartRot.JOGADOR.toString(), jogador.getNome());
			}
			System.out.println(); //imprime quebra de linha
			incQtdLinhasImp(1);
			
			jogadorFoiImpresso = true;
		}
		
		//linha 2 e 3
		if (mec != null) {
			//linha 2
			impItemBarra(BarPartRot.NIVEL_EXPERIENCIA.toString(), mec.getNivelExp());
			impItemBarra(BarPartRot.TIPO_EMBARALHADOR.toString(), mec.getTipoEmb());
			impItemBarra(BarPartRot.MULTIPLICADOR.toString(), Integer.toString(mec.getMult()) + "x");
			System.out.println(); //imprime quebra de linha	
			incQtdLinhasImp(1);
		
			//linha 3
			impItemBarra(BarPartRot.DIFICULDADE.toString(), mec.getGrauDif() + " " + mec.getTipoMecanica());
			System.out.println(); //imprime quebra de linha
			incQtdLinhasImp(1);

			mecanicaFoiImpressa = true;
		}
		
		if (jogadorFoiImpresso || mecanicaFoiImpressa) {
			System.out.println(getLinhaDivisoria(TelaNav.margemEsquerda, TelaNav.separadorInterno)); //imprime linha divisória
			incQtdLinhasImp(1);
		}
	}	
	
	//limpa barra de status
	protected void limpaBarraStatus() {
		barraStatus = null;
	}	
	//define barra de status
	protected void setBarraStatus(LinkedHashMap<BarStatusRot, String> barraStatus) {
		this.barraStatus = barraStatus;
	}	
	//imprime barra de status
	protected void impBarraStatus() {
		if (barraStatus != null) {
			boolean barraImpressa = false;
			
			BarStatusRot[] keys = BarStatusRot.values();
			//percorre o array
			for (BarStatusRot key : keys) {
				if (barraStatus.get(key) != null) {
					impItemBarra(key.toString(), barraStatus.get(key));
					barraImpressa = true;
				}
			}
		
			if (barraImpressa) {
				System.out.println(); //imprime quebra de linha
				System.out.println(getLinhaDivisoria(TelaNav.margemEsquerda, TelaNav.separadorInterno)); //imprime linha divisória
				incQtdLinhasImp(2);
			}
		}
	} //fim do método impBarraStatus

	@Override
	//impCabecalho: método sobrescrito para adicionar impBarraPartida() e impBarraStatus()
	protected void impCabecalho() {
		super.impCabecalho();
		impBarraPartida();
		impBarraStatus();
	}	
	
	//método utilitário: imprime um par (rotulo, valor)
	private static void impItemBarra(String rotulo, String valor) {
		System.out.print(TelaNav.margemEsquerda); //imprime margem esquerda
		System.out.print(rotulo); //imprime chave
		System.out.print(": "); //imprime separador
		System.out.print(valor); //imprime valor		
	}
}
