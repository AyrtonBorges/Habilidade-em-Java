package Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner tecladoEntrada = new Scanner(System.in);
		Quiz quiz = new Quiz("Shitpost");
		try {
			List<Answer> answers = new ArrayList<>();
			String pergunta = "Em algum tempo desconhecido existiu um meme de um animal chamado Juan, qual tipo de animal seria ele?";
			answers.add(new Answer("Cachorro"));
			answers.add(new Answer("Cavalo"));
			answers.add(new Answer("Limão"));
			answers.add(new Answer("Pomba Africana"));
			int resposta = 1;
			quiz.addQuestion(new Question(pergunta,answers, resposta));
			answers = new ArrayList<>();
			pergunta = "Sei lá o que ?";
			answers.add(new Answer("Cachorro"));
			answers.add(new Answer("Cavalo"));
			answers.add(new Answer("Limão"));
			answers.add(new Answer("Pomba Africana"));
			resposta = 2;
			quiz.addQuestion(new Question(pergunta,answers, resposta));
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		int posicaoQuestao = 0;
		System.out.println("Quiz de "+quiz.getTitle()+"!");
		System.out.println("Responda as perguntas:");
		Question tempQuestion = quiz.getQuestion(posicaoQuestao);
		System.out.print(tempQuestion.toString());
		System.out.print("Escolha a pergunta: ");
		try {
			if(tempQuestion.checkRightAnswer(tecladoEntrada.nextInt()))
			{
				System.out.println("Acertou mizerável!");
				quiz.score();
			}
			else
			{
				System.out.println("Errou!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Seu score é "+quiz.getScore());
		posicaoQuestao = 1;
		System.out.println("Quiz de "+quiz.getTitle()+"!");
		System.out.println("Responda as perguntas:");
		tempQuestion = quiz.getQuestion(posicaoQuestao);
		System.out.print(tempQuestion.toString());
		System.out.print("Escolha a pergunta: ");
		try {
			if(tempQuestion.checkRightAnswer(tecladoEntrada.nextInt()))
			{
				System.out.println("Acertou mizerável!");
				quiz.score();
			}
			else
			{
				System.out.println("Errou!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Seu score é "+quiz.getScore());
	}
}
