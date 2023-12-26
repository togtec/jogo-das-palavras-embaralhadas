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
	
	//método sobrescrito para inicializar qtdPalpitesUtilizados
	public void iniciar() {
		super.iniciar();
		qtdPalpitesUtilizados = 0;
	}
	
	@Override //método sobrescrito para adicionar a chave: BarStatusRot.PALPITES_UTILIZADOS 
	public LinkedHashMap<BarStatusRot, String> getBarraStatus() {
		LinkedHashMap<BarStatusRot, String> barraStatus = super.getBarraStatus();
		barraStatus.put(BarStatusRot.PALPITES_UTILIZADOS, Integer.toString(qtdPalpitesUtilizados));
		return barraStatus;
	}	

	@Override //método de implementação obrigatória (abstrato na superclasse)
	protected int getValorDoAcerto() {
		return getValorDaPalavraAtual() * getMult();
	}
	
	@Override //sobrescrito para se adicionar o valor do acerto e a quebra de linha
	protected StringBuilder getDescricaoPontuacao() {
		StringBuilder descPont = super.getDescricaoPontuacao();
		int posicaoDoCursor = super.getDescricaoPontuacao().lastIndexOf("valendo") + 8; //posição do cursor na string no momento da inserção
		descPont.insert(posicaoDoCursor, getValorDoAcerto());
		descPont.append("\n");
		return descPont;
	}	
	
	@Override //método de implementação obrigatória (abstrato na superclasse)
	public void processaPalpite(String palpite) {
		//se mecânica em curso
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
				
				//se existe outra palavra, avança
				if (hasNext()) {
					next();
				} else { //senão, conclui				
					concluir();
				}						
			} 
		} 
	} //fim do método processaPalpite
}