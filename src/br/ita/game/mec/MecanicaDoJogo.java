package br.ita.game.mec;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;

import br.ita.game.emb.Embaralhador;
import br.ita.game.enu.BarStatusRot;
import br.ita.game.enu.NivelExperiencia;
import br.ita.game.enu.PalpiteStatus;
import br.ita.game.enu.PartidaStatus;
import br.ita.game.enu.TipoMecanica;
import br.ita.game.tela.TelaConfiguracoes;
import br.ita.game.tela.TelaNav;
import br.ita.game.BancoDePalavras;
import br.ita.game.Palavra;

public abstract class MecanicaDoJogo {
	private Embaralhador emb;
	private TipoMecanica tipoMecanica;
	private Cronometro timer;
	private boolean emCurso;	
	
	private ArrayList<Palavra> listaPalavras;
	private int ordemDaPalavraAtual;
	private String palavraAtualEmbaralhada;
	
	private StringBuilder conteudo;
	private StringBuilder relProg; //relatório de progresso
	private int qtdAcertos;
	private int placar;
	
	
	
/*** construtor ***/
	MecanicaDoJogo(Embaralhador emb, TipoMecanica tipoMecanica) {
		this.emb = emb;
		this.tipoMecanica = tipoMecanica;
	}
	
	
	
/*** métodos que controlam a dinâmica ***/
	public void iniciar() { //método sobrescrito nas subclasses para iniciar Nº de Palpites Utilizados e Restantes
		emCurso = true;
		
		listaPalavras = BancoDePalavras.getListaPalavrasAleat(TelaConfiguracoes.getQtdPalavrasPorPartida());		
		ordemDaPalavraAtual = 1;
		palavraAtualEmbaralhada = emb.embaralharPalavra(listaPalavras.get(ordemDaPalavraAtual -1).getSilabas());
		
		conteudo = null;
		relProg = new StringBuilder();
			relProg.append("Relatório de Progresso:\n\n");
		qtdAcertos = 0;
		placar = 0;
		
		timer = new Cronometro(TelaConfiguracoes.getTempoMaxEmMinutos());
			Thread thread = new Thread(timer);
			thread.start();	
	}
	
	public boolean emCurso() {
		return emCurso;
	}
	
	protected boolean hasNext() {
		if (ordemDaPalavraAtual < listaPalavras.size())
			return true;
		else
			return false;
	}
	
	protected void next() {
		ordemDaPalavraAtual++;
		palavraAtualEmbaralhada = emb.embaralharPalavra(listaPalavras.get(ordemDaPalavraAtual -1).getSilabas());
	}
	
	public void concluir() {
		if (emCurso) { 
			emCurso = false;
		
			//equanto houver mais palavras
			while (hasNext()) {
				next(); //avança
				incRelProg(PalpiteStatus.ERRO); //atualiza relatório de progresso
			}
		
			endRelProg(); //encerra relatório de progresso
		}
	}
	
	
	
/*** métodos que controlam a estrutura ***/
	public LinkedHashMap<BarStatusRot, String> getBarraStatus() { //sobrescrito nas subclasses
		LinkedHashMap<BarStatusRot, String> barraStatus = new LinkedHashMap<BarStatusRot, String>();
			barraStatus.put(BarStatusRot.PALPITES_UTILIZADOS, null);
			barraStatus.put(BarStatusRot.PALPITES_RESTANTES, null);
			barraStatus.put(BarStatusRot.PONTOS, Integer.toString(placar));
			barraStatus.put(BarStatusRot.TEMPO, timer.getTempoRestante());
		return barraStatus;
	}
	
	public StringBuilder getConteudo() {
		conteudo = new StringBuilder();
		conteudo.append(getDescricaoPontuacao());
			
		//se o nível de experiência for AVANCADO, adiciona dica 
		if(getNivelExp().equals(NivelExperiencia.AVANCADO.toString())) {
			conteudo.append("\n");
			conteudo.append(listaPalavras.get(ordemDaPalavraAtual - 1).getDica() + "\n");			
		}
			
		//adiciona a palavra atual embaralhada
		conteudo.append(TelaNav.margemEsquerda + palavraAtualEmbaralhada);

		return conteudo;
	}
	
	protected void incRelProg(PalpiteStatus pst) { //incrementa relatório de progresso (método sobrecarregado)		 
		if(pst == PalpiteStatus.ACERTO) { 
			//prepara progresso (ordem + palavra)
			String progresso = "Palavra " + ordemDaPalavraAtual + ": " + listaPalavras.get(ordemDaPalavraAtual - 1).getSilabas().replace(" ", "");
			
			//prepara a descrição da pontuação 
			String[] aux = getDescricaoPontuacao().toString().split("valendo ");
			//obs: aux[1] possui a descrição da pontuação + a quebra de linha
			String descPont = alignDescPontRelProg(getValorDoAcerto(), aux[1]);
						
			//registra progresso + descrição da pontuação (obs: descrição já possui quebra de linha)
			incRelProg(progresso, descPont);
		} else {
		//registra progresso + quebra de linha
			String progresso = "Palavra " + ordemDaPalavraAtual + ":" + "\n";
			incRelProg(progresso, null);
		}
	}
	private void incRelProg(String progresso, String descPont) {
		//adicina progresso 
		relProg.append(progresso);	
		
		if (descPont != null) {
			//calcula o tamanho do progresso sem a quebra de linha
			int tamanho = progresso.replace("\n", "").length();
			//adiciona um espaço em branco
			relProg.append(" ");
			
			//adiciona o pontilhado
			for (int cont = tamanho; cont < 37; cont++) {
				relProg.append(".");
			}
			
			//adicona espaço em branco + valor
			relProg.append(" ");
			relProg.append(descPont);
		}		
	}	 
	private String alignDescPontRelProg(int pont, String descPont) { //alinha a descrição da pontuação no relatório de progresso
		//se pontuação possui apenas 1 dígito
		if (pont < 10) {
			//acrescenta 2 espaços em branco para regular o alinhamento
			return "  " + descPont;
			
		//senão, se pontuação possui apenas 2 dígitos
		} else if (pont < 100) {
			//acrescenta 1 espaço em branco para regular o alinhamento
			return  " " + descPont;
		} else {
			return descPont;
		}
	}
	private void endRelProg() { //encerra relatório de progresso
		if (getPartidaStatus() == PartidaStatus.VITORIA) {
			
			/*** registra bônus por vitória ***/
			//prepara progresso
			String progresso1 = "\n" + "Bônus por vitória:";
			//prepara a descrição da pontuação
			String aux1 = Integer.toString(tipoMecanica.getBonusVitoria()) + " pontos";
			String descPont1 = alignDescPontRelProg(tipoMecanica.getBonusVitoria(), aux1);
			//registra bônus por vitória
			incPlacar(tipoMecanica.getBonusVitoria());
			incRelProg(progresso1, descPont1);
			
			
			/*** registra bônus por tempo restante ***/
			//calcula valor
			int valor = timer.getQtdBonusTempoRestante() * tipoMecanica.getBonusTempoRest();
			//prepara progresso
			String progresso2 = "\n" + "Bônus por tempo retante:";
			//prepara a descrição da pontuação
			String aux2 = valor + " pontos: " + tipoMecanica.getBonusTempoRest() + " ponto(s) à cada 10 segundos de tempo não utilizado";
			String descPont2 = alignDescPontRelProg(valor, aux2);
			//registra bônus por tempo restante
			incPlacar(valor);
			incRelProg(progresso2, descPont2);			
		} 
		
		//registra tempo restante
		incRelProg("\n" + "Tempo restante: " + timer.getTempoRestante(), null);
		
		//registra placar final
		String progresso3 = "\n\n" + "Pontuação final da partida:";
		String aux3 = Integer.toString(placar) + " pontos";
		String descPont3 = alignDescPontRelProg(placar, aux3);		
		incRelProg(progresso3, descPont3);
		
		//registra cumprimento
		if (getPartidaStatus() == PartidaStatus.VITORIA) { 
			incRelProg("\n" + "Parabéns! Você venceu! Acertou todas as palavras!", null);
		}
	}
	public StringBuilder getRelProg() { //retorna relatório de progresso
		return relProg;
	}
	
	
	
/*** métodos que controlam a rodada ***/
	protected int getValorDaPalavraAtual() {
		return listaPalavras.get(ordemDaPalavraAtual - 1).getValorPalavra();
	}
	
	//sobrescrito nas subclasses para se adicionar o valor do acerto, eventuais bônus, e a quebra de linha
	//obs: quebra de linha não pode ser posta agora pois o "bônus por palpite não utilizado" é adicionado ao final 
	protected StringBuilder getDescricaoPontuacao() {
		StringBuilder descPont = new StringBuilder();
		descPont.append("Palavra " + ordemDaPalavraAtual + " de " + listaPalavras.size()); //Palavra 1 de 5
		descPont.append(" - valendo  pontos: ");                                           //- valendo pontos:
		descPont.append("(" + getValorDaPalavraAtual() + " x " + getMult() + ")");         //(4 x 1)
		return descPont;
	}	
	
	protected void incQtdAcertos() {
		qtdAcertos++;
	}
	
	protected void incPlacar(int pontos) {
		placar += pontos;
	}	
	
	protected PalpiteStatus getPalpiteStatus(String palpite) {
		return listaPalavras.get(ordemDaPalavraAtual - 1).getPalpiteStatus(palpite);
	}
	
	
	
/*** métodos de status da partida ***/
	public PartidaStatus getPartidaStatus() {
		//se jogador acertou todas as palavras
		if (qtdAcertos == listaPalavras.size()) { 
			return PartidaStatus.VITORIA;
		} else {
			return PartidaStatus.DERROTA;
		}
	}
	
	public int getPlacar() {
		return placar;
	}
	
	
	
/*** métodos de captura do Embaralhador e do tipoMecanica ***/
	public String getNivelExp() {
		return emb.getNivelExp();
	}
	
	public String getTipoEmb() {
		return emb.getTipoEmb();
	}
	
	public int getMult() {
		return emb.getMult();
	}
	
	public String getGrauDif() {
		return tipoMecanica.getGrauDif();
	}
	
	public String getTipoMecanica() {
		return tipoMecanica.getTipoMecanica();
	}
	
	
	
/*** métodos abstratos ***/	
	protected abstract int getValorDoAcerto();
	public abstract void processaPalpite(String palpite);
	
	
	
	
	
/*** classe interna (tarefa de segmento)
		-calcula o tempo restante
		-encerra a partida por time-out
		-calcula a quantidade de bonus por tempo restante */
	public class Cronometro implements Runnable {
		private int tempoRestante;
		private Calendar calendar;	
		private SimpleDateFormat formatador;	
	
		//método construtor
		public Cronometro(int qtdMinutos) {
			if (qtdMinutos <= 0)
				qtdMinutos = 1;
			else if (qtdMinutos > 60)
				qtdMinutos = 60;
		
			//prepara calendar
			tempoRestante = qtdMinutos * 60 * 1000; 
			calendar = Calendar.getInstance();
			calendar.setTimeInMillis(tempoRestante);
			//prepara SimpleDateFormat
			formatador = new SimpleDateFormat("mm:ss");						
		}
	
		public void run() {
			try {				
				while (emCurso()) {	
					//dorme por 1 segundo
					Thread.sleep(1000); 
					//decrementa tempo restante
					tempoRestante -=  1000; 
					//atualiza cronometro
					calendar.setTimeInMillis(tempoRestante); 
				
					if (tempoRestante <= 0) {
						//palavra em exibição é registrada como erro
						incRelProg(PalpiteStatus.ERRO);
						//avisa ao usuário que o tempo acabou
						System.out.print("Tempo encerrado! Pressione qualquer tecla para prosseguir> ");
						//conclui a mecânica
						concluir();
					}
				} //fim do while			
			} catch(InterruptedException ex) {
				System.out.println(ex.getMessage());
			}		
		} //fim do métudo run
	
		private String getTempoRestante() {
			return formatador.format(calendar.getTime()); 
		}
	
		private int getQtdBonusTempoRestante() {
			//uma unidade à cada 10 segundos
			return (int) tempoRestante / 1000 / 10; 
		}
	} //fim da classe interna Cronometro	
}
