package Quiz;

import java.util.List;

public class Question {
	private String description;
	private List<Answer> answers;
	private int rightAnswer;
	private final int quantidadeAnswer = 4;
	
	public Question(String description, List<Answer> answers, int rightAnswer) throws Exception
	{
		if(answers.size() < quantidadeAnswer) 
			throw new Exception("São pouca quantidade de respostas!");
		else if(answers.size() > quantidadeAnswer)
			throw new Exception("Passou da quantidade de respostas!");
		this.description = description;
		this.answers = answers;
		this.rightAnswer = rightAnswer;
	}
	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public List<Answer> getAnswers()
	{
		return this.answers;
	}
	public Answer getAnswer(int position)
	{
		return this.answers.get(position);
	}
	public void setRightAnswer(int number)
	{
		this.rightAnswer = number;
	}
	public boolean checkRightAnswer(int number) throws Exception
	{
		number--;
		if(number < 0 || number > quantidadeAnswer)
			throw new Exception("Posição está acima ou abaixo da quantidade de respostas!");
		return this.answers.get(number).equals(this.answers.get(rightAnswer));
	}
	public String toString()
	{
		String tempRetorno = this.description+"\n";
		for(Answer tempAnswer: answers)
			tempRetorno += "["+(answers.indexOf(tempAnswer)+1)+"]"+tempAnswer.getDescription()+"\n";
		return tempRetorno;
	}
}
