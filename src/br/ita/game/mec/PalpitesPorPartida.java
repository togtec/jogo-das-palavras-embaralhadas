package br.ita.game.mec;

import java.util.LinkedHashMap;

import br.ita.game.emb.Embaralhador;
import br.ita.game.enu.BarStatusRot;
import br.ita.game.enu.PalpiteStatus;
import br.ita.game.enu.TipoMecanica;
import br.ita.game.tela.TelaConfiguracoes;

public class PalpitesPorPartida extends MecanicaDoJogo {
	private int qtdPalpitesRestantes;
	
	//construtor
	PalpitesPorPartida(Embaralhador emb) {
		super(emb, TipoMecanica.PALPITS_POR_PARTIDA);
	}
	
	//método sobrescrito para inicializar qtdPalpitesRestantes
	public void iniciar() {
		super.iniciar();
		qtdPalpitesRestantes = TelaConfiguracoes.getQtdPalavrasPorPartida() * 2;
	}	
	
	@Override //método sobrescrito para adicionar a chave: BarStatusRot.PALPITES_RESTANTES 
	public LinkedHashMap<BarStatusRot, String> getBarraStatus() {
		LinkedHashMap<BarStatusRot, String> barraStatus = super.getBarraStatus();
		barraStatus.put(BarStatusRot.PALPITES_RESTANTES, Integer.toString(qtdPalpitesRestantes));
		return barraStatus;
	}
	
	@Override //método de implementação obrigatória (abstrato na superclasse)
	protected int getValorDoAcerto() {
		return (getValorDaPalavraAtual() * getMult()) + qtdPalpitesRestantes - 1;
	}
	
	@Override //sobrescrito para se adicionar o valor do acerto, bônus por palpite não utilizado, e a quebra de linha
	protected StringBuilder getDescricaoPontuacao() {
		StringBuilder descPont = super.getDescricaoPontuacao();
		int posicaoDoCursor = super.getDescricaoPontuacao().lastIndexOf("valendo") + 8; //posição do cursor na string no momento da inserção
		descPont.insert(posicaoDoCursor, getValorDoAcerto());
		descPont.append(" + " + (qtdPalpitesRestantes - 1));
		descPont.append("\n");
		return descPont;
	}
	
	@Override //método de implementação obrigatória (abstrato na superclasse)
	public void processaPalpite(String palpite) {
		//se mecânica em curso
		if (emCurso()) {
			//se Jogador acertou o palpite
			if (getPalpiteStatus(palpite) == PalpiteStatus.ACERTO) {
				//atualiza progresso
				incRelProg(PalpiteStatus.ACERTO);
				
				//atualiza placar
				incPlacar(getValorDoAcerto());
				//atualiza acertos
				incQtdAcertos();

				//decrementa número de palpites (usuário utilizou um palpite para poder acertar)
				qtdPalpitesRestantes--;
				
				if (qtdPalpitesRestantes > 0) {
					//se existe outra palavra, avança
					if (hasNext()) {
						next();	
					} else { //senão, conclui				
						concluir();
					}					
				} else {
					concluir();
				}
				
			//senão, jogador(a) errou
			} else {
				//decrementa número de palpites
				qtdPalpitesRestantes--;
				
				//se os palpites da partida acabaram
				if (qtdPalpitesRestantes == 0) {
					//atualiza progresso
					incRelProg(PalpiteStatus.ERRO);
					concluir();		
				}
			}
		}  //fim "se mecânica em curso"
	} //fim do método processaPalpite
}