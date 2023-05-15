package Quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static ArrayList<Quiz> conjuntoDeQuiz = new ArrayList<Quiz>();
	public static void main(String[] args) {
		Scanner tecladoEntrada = new Scanner(System.in);
		conjuntoDeQuiz.add(new Quiz("Qual é a Capital do Brasil?", Arrays.asList(new String[] {"Brasilia","Rio de Janeiro","São Paulo"}), "Brasilia"));
		conjuntoDeQuiz.get(0).mudarOrdem();
		conjuntoDeQuiz.add(new Quiz("Qual estado tem um PIB alto?",Arrays.asList(new String[] {"Brasilia","São Paulo","Ceará","Minas Gerais"}),"São Paulo"));
		conjuntoDeQuiz.get(1).mudarOrdem();
		conjuntoDeQuiz.add(new Quiz("Malhar dá que beneficios?", Arrays.asList(new String[] {"Dor de coluna","Câncer","Melhora na memória e na saúde"}), "Melhora na memória e na saúde"));
		conjuntoDeQuiz.get(2).mudarOrdem();
		
		conjuntoDeQuiz.get(0).efetuarQuestionario();
		if(conjuntoDeQuiz.get(0).verificarResposta(tecladoEntrada.nextInt()))
			System.out.println("Você acertou!");
		else
			System.out.println("Você errou!");
	}

}
