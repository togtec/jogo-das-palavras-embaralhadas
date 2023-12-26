/* A TelaModificarJogador cria uma nova estrutura: Barra jogador */

package br.ita.game.tela;

import br.ita.game.Jogador;

public abstract class TelaModificarJogador extends TelaNav {
	protected Jogador jogador;
	
	//construtor
	public TelaModificarJogador(String subtitulo, Jogador jogador) {
		super(subtitulo);
		this.jogador = jogador;
	}
	
	//imprime barra jogador
	protected void impBarraJogador() {
		if (jogador != null) {
			System.out.println(TelaNav.margemEsquerda + "Nome: " + jogador.getNome());
			System.out.println(TelaNav.margemEsquerda + "Apelido: " + jogador.getApelido());
			System.out.println(getLinhaDivisoria(TelaNav.margemEsquerda, TelaNav.separadorInterno));
			incQtdLinhasImp(3);
		}
	}
	
	@Override
	//impCabecalho: método sobrescrito para adicionar impBarraJogador()
	protected void impCabecalho() {
		super.impCabecalho();
		impBarraJogador();
	}
}
