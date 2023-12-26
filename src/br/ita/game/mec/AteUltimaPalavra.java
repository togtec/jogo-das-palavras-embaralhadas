package br.ita.game.mec;

import java.util.LinkedHashMap;

import br.ita.game.emb.Embaralhador;
import br.ita.game.enu.BarStatusRot;
import br.ita.game.enu.PalpiteStatus;
import br.ita.game.enu.TipoMecanica;

public class AteUltimaPalavra extends MecanicaDoJogo {
	private int qtdPalpitesUtilizados;
	
	//construtor
	AteUltimaPalavra(Embaralhador emb) {
		super(emb, TipoMecanica.PALPITS_SEM_LIMITE);
	}
	
	//m�todo sobrescrito para inicializar qtdPalpitesUtilizados
	public void iniciar() {
		super.iniciar();
		qtdPalpitesUtilizados = 0;
	}
	
	@Override //m�todo sobrescrito para adicionar a chave: BarStatusRot.PALPITES_UTILIZADOS 
	public LinkedHashMap<BarStatusRot, String> getBarraStatus() {
		LinkedHashMap<BarStatusRot, String> barraStatus = super.getBarraStatus();
		barraStatus.put(BarStatusRot.PALPITES_UTILIZADOS, Integer.toString(qtdPalpitesUtilizados));
		return barraStatus;
	}	

	@Override //m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	protected int getValorDoAcerto() {
		return getValorDaPalavraAtual() * getMult();
	}
	
	@Override //sobrescrito para se adicionar o valor do acerto e a quebra de linha
	protected StringBuilder getDescricaoPontuacao() {
		StringBuilder descPont = super.getDescricaoPontuacao();
		int posicaoDoCursor = super.getDescricaoPontuacao().lastIndexOf("valendo") + 8; //posi��o do cursor na string no momento da inser��o
		descPont.insert(posicaoDoCursor, getValorDoAcerto());
		descPont.append("\n");
		return descPont;
	}	
	
	@Override //m�todo de implementa��o obrigat�ria (abstrato na superclasse)
	public void processaPalpite(String palpite) {
		//se mec�nica em curso
		if (emCurso()) {
			//incrementa palpites utilizados
			qtdPalpitesUtilizados++;
			
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
				} else { //sen�o, conclui				
					concluir();
				}						
			} 
		} 
	} //fim do m�todo processaPalpite
}