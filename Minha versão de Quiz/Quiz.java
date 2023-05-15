package Quiz;

import java.util.Collections;
import java.util.List;

public class Quiz {
	private String pergunta = "";
	private List<String> opcoes;
	private String resposta;
	
	public Quiz(String pergunta, List<String> opcoes, String resposta) {
		this.pergunta = pergunta;
		this.opcoes = opcoes;
		this.resposta = resposta;
	}
	
	public void mudarOrdem() {
		if(pergunta != "")
			geradorDeOrdem();
		else
			System.out.println("Não possui nenhum valor!");
	}
	
	private void geradorDeOrdem() {
		Collections.shuffle(opcoes);
		System.out.println("Ordem alterada!");
	}
	
	public void efetuarQuestionario() {
		System.out.println(this.pergunta);
		printarListaDeOpcoes();
	}
	
	private void printarListaDeOpcoes() {
		for(String opcao : opcoes)
			System.out.println("["+(opcoes.indexOf(opcao)+1)+"] "+opcao);
	}
	
	public String getPergunta() {
		return this.pergunta;
	}
	
	public List<String> getOpcoes() {
		return this.opcoes;
	}
	
	public boolean verificarResposta(int i)
	{
		if(opcoes.get(i-1).equals(resposta))
			return true;
		return false;
	}
}
