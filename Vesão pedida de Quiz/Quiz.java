package Quiz;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
	private String title;
	private List<Question> questions = new ArrayList<>();
	private int score;
	private int quantidadePerguntas = 0;
	
	public Quiz(String title)
	{
		this.title = title;
		score = 0;
	}
	public String getTitle()
	{
		return this.title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public List<Question> getQuestions()
	{
		return this.questions;
	}
	public Question getQuestion(int position)
	{
		return questions.get(position);
	}
	public void addQuestion(Question question) throws Exception
	{
		if(this.quantidadePerguntas < 10)
			this.questions.add(question);
		else throw new Exception("Excedeu a quantidade de perguntas");
		quantidadePerguntas++;
	}
	public void score()
	{
		this.score++;
	}
	public int getScore()
	{
		return this.score;
	}
}
