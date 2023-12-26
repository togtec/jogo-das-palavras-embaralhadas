package br.ita.game.mec;

import java.util.LinkedHashMap;

import br.ita.game.emb.Embaralhador;
import br.ita.game.enu.BarStatusRot;
import br.ita.game.enu.PalpiteStatus;
import br.ita.game.enu.TipoMecanica;

public class PalpitesPorPalavra extends MecanicaDoJogo {
	private int qtdPalpitesRestantes;
	
	//construtor
	PalpitesPorPalavra(Embaralhador emb) {
		super(emb, TipoMecanica.PALPITS_POR_PALAVRA);
	}

	//m�todo sobrescrito para inicializar qtdPalpitesRestantes
	public void iniciar() {
		super.iniciar();
		qtdPalpitesRestantes = 3;
	}
	
	@Override //m�todo sobrescrito para adicionar a chave: BarStatusRot.PALPITES_RESTANTES 
	public LinkedHashMap<BarStatusRot, String> getBarraStatus() {
		LinkedHashMap<BarStatusRot, String> barraStatus = super.getBarraStatus();
		barraStatus.put(BarStatusRot.PALPITES_RESTANTES, Integer.toString(qtdPalpitesRestantes));
		return barraStatus;
	}
	
	@Override //m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected int getValorDoAcerto() {
		return (getValorDaPalavraAtual() * getMult()) + qtdPalpitesRestantes -1;
	}
	
	@Override //sobrescrito para se adicionar o valor do acerto, b�nus por palpite n�o utilizado, e a quebra de linha
	protected StringBuilder getDescricaoPontuacao() {
		StringBuilder descPont = super.getDescricaoPontuacao();
		int posicaoDoCursor = super.getDescricaoPontuacao().lastIndexOf("valendo") + 8; //posi��o do cursor na string no momento da inser��o
		descPont.insert(posicaoDoCursor, getValorDoAcerto());
		descPont.append(" + " + (qtdPalpitesRestantes - 1));
		descPont.append("\n");
		return descPont;
	}
	
	@Override //m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	public void processaPalpite(String palpite) {
		//se mec�nica em curso
		if (emCurso()) {
			//se Jogador acertou o palpite
			if (getPalpiteStatus(palpite) == PalpiteStatus.ACERTO) {
				//atualiza progresso
				incRelProg(PalpiteStatus.ACERTO);
				
				//atualiza placar
				incPlacar(getValorDoAcerto());
				//atualiza acertos
				incQtdAcertos();
				
				//se existe outra palavra, avan�a
				if (hasNext()) {
					next();	
					qtdPalpitesRestantes = 3;
				} else { //sen�o, conclui				
					concluir();
				}
				
			//sen�o, jogador(a) errou
			} else {
				//decrementa n�mero de palpites
				qtdPalpitesRestantes--;
				
				//se os palpites da palavra acabaram
				if (qtdPalpitesRestantes == 0) {
					//atualiza progresso
					incRelProg(PalpiteStatus.ERRO);
					
					//se existe outra palavra, avan�a
					if (hasNext()) {
						next();	
						qtdPalpitesRestantes = 3;
					} else { //sen�o, conclui				
						concluir();
					}
				}
			}
		} //fim "se mec�nica em curso"
	} //fim do m�todo processaPalpite	
}