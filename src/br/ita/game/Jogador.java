/* A classe Jogador representa o usu�rio que disputa a partida.
  
   Observa��o importante: Ao contr�rio dos demais objetos do jogo, um objeto Jogador s� deve existir se estiver salvo 
   em disco (arquivo "jog.ser"). Portanto, para impedir a exist�ncia de um objeto Jogador n�o salvo em disco circulando 
   pelo sistema, o construtor da classe Jogador � privado, e apenas o m�todo getInstance pode criar um novo objeto Jogador. */
 
package br.ita.game;

import java.io.Serializable;

import br.ita.game.enu.PartidaStatus;

public class Jogador implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id = 0;  /* ao ser instanciado, todo objeto Jogador recebe um id tempor�rio igual a zero; 
	ao ser serializado no arquivo pela primeira vez, recebe um id definitivo diferente de zero */
	private String nome = null;
	private String apelido = null;
	private int partidas = 0;
	private int vitorias = 0; //partidas que o Jogador(a) acertou todas as palavras
	private int pontos = 0;
	
	//construtor (privado pelas raz�es j� descritas acima)
	private Jogador(String nome, String apelido) {
		this.nome = nome.trim();
		this.apelido = apelido.trim();
	}

	//retorna uma inst�ncia de Jogador
	public static Jogador getInstance(String nome, String apelido) throws Exception {
		if (!nomeValido(nome)) {
			throw new Exception("M�todo Jogador.getInstance: Par�metro nome inv�lido!");
		}
		
		Jogador jogador = null;
		
		if (apelidoValido(apelido)) {
			jogador = new Jogador(nome, apelido);
		} else {
			jogador = new Jogador(nome, "");
		}
		
		jogador.salvar(); 

		return jogador;
	} //fim do m�todo getInstance
	
	//nomeValido: nome n�o pode ser null, vazio, nem uma sequ�ncia de caracteres em branco
	public static boolean nomeValido(String nome) {
		//se nome � inv�lido (null, vazio ou uma sequ�ncia de caracteres em branco)
		if (nome == null || nome.trim().length() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	//apelidoValido: apelido n�o pode ser null, nem uma sequ�ncia de caracteres em branco
	public static boolean apelidoValido(String apelido) {
		//se apelido � inv�lido (null ou uma sequ�ncia de caracteres em branco)
		if (apelido == null || (apelido.length() > 0 && apelido.trim().length() == 0)) {
			return false;
		} else {
			return true;
		}
	}

	//serializa ojeto Jogador em disco
	private void salvar() throws Exception {
		AdmJogadorFile.salvar(this);
	}	
	
	//define o id
	public void setId(int id) {
		//se jogador n�o tem id definitivo
		if (this.id == 0) { 
			//define id
			this.id = id;
		}
	}
	
	//retorna  1 se o nome do jogador foi trocado no arquivo
	//retorna -1 se o nome do jogador n�o foi trocado no arquivo (novoNome � inv�lido ou igual ao nome atual)	
	public int setNome(String novoNome) throws Exception {
		int retorno = -1;	
		
		//para ser atualizado novoNome precisa ser v�lido e n�o pode ser igual ao nome atual
		if (nomeValido(novoNome) && !(novoNome.trim().contentEquals(this.nome))) {
			this.nome = novoNome.trim();
			salvar();
			retorno = 1;
		}
		return retorno;
	}
	
	//retorna  1 se o apelido do jogador foi trocado no arquivo 
	//retorna -1 se o apelido do jogador n�o foi trocado no arquivo (novo apelido � inv�lido ou igual ao apelido atual)
	public int setApelido(String novoApelido) throws Exception {
		int retorno = -1;
		
		//para ser atualizado novoApelido precisa ser v�lido e n�o pode ser igual ao apelido atual
		if (apelidoValido(novoApelido) && !(novoApelido.trim().contentEquals(this.apelido))) {
			this.apelido = novoApelido.trim();
			salvar();
			retorno = 1;
		}
		return retorno;
	}
	
	//retorna id
	public int getId() {
		return id;
	}
	
	//retorna nome
	public String getNome() {
		return nome;
	}
	
	//retorna apelido
	public String getApelido() {
		return apelido;
	}
	
	//incrementa score
	public void incScore(PartidaStatus partidaStatus, int ptsMarcados) throws Exception {
		//incrementa n�mero de partidas disputadas
		partidas++;
		//incrementa n�mero de pontos marcados
		pontos += ptsMarcados;
		//incrementa n�mero de v�t�rias (partidas que o Jogador(a) acertou todas as palavras)
		if (partidaStatus == PartidaStatus.VITORIA) {
			vitorias++;
		}
		salvar();
	}
	
	//retorna a quantidade de partidas disputadas
	public int getPartidas() {
		return partidas;
	}
	
	//retorna a quantidade de partidas vencidas - partidas em que o Jogador(a) acertou todas as palavras
	public int getVitorias() {
		return vitorias;
	}
	
	//retorna a quantidade de pontos marcados
	public int getPontos() {
		return pontos;
	}
	
	// m�todos hashCode() e equals() sobrescritos para que  
	//	objetos com o mesmo id sejam considerados iguais
	@Override
	public int hashCode() {
		return id;
	}
	@Override
	public boolean equals(Object obj) {
		//converte a refer�ncia de Object para Jogador
		Jogador jogador = (Jogador) obj;
		return this.getId() == jogador.getId();
	}
}
