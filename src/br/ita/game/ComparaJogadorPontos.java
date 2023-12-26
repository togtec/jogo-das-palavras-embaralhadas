package br.ita.game;

import java.util.Comparator;

public class ComparaJogadorPontos implements Comparator<Jogador> {
	public int compare(Jogador jg1, Jogador jg2) {
		if (jg1.getPontos() > jg2.getPontos()) {
			return -1; //jg1 precede jg2
		} else if (jg1.getPontos() < jg2.getPontos()) {
			return 1; //jg1 sucede jg2
		} else {
			return 0;
		}
	}
}
