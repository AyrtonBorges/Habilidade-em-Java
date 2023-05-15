package Biblioteca;

import java.util.List;

public class Emprestimo {
	private String isbn;
	private String cpf;
	private String status = "atrasados";
	private int prazo = 0;
	private boolean renovado;
	public Emprestimo(String isbn, String cpf) throws Exception
	{
		if(Livro.verificadorISBN(isbn) && Usuario.verificarCPF(cpf))
		{
			isbn = (isbn.replace(".", "")).replace("-", "");
			cpf = (cpf.replace(".", "")).replace("-", "");
			this.isbn = isbn;
			this.cpf = cpf;
			this.status = "ativo";
			this.prazo = 7;
			this.renovado = false;
		}else throw new Exception("Erro ao cadastrar, ISBN ou CPF está errado!");
	}
	public String getISBN()
	{
		return this.isbn;
	}
	public String getCPF()
	{
		return this.cpf;
	}
	public String getStatus()
	{
		return this.status;
	}
	public int getPrazo()
	{
		return this.prazo;
	}
	public boolean renovarPrazo()
	{
		if(!this.renovado)
		{
			this.renovado = true;
			this.prazo = 7;
			this.status = "ativo";
			return true;
		}else 
		{
			System.out.println("Já foi renovado, não pode renovar novamente!");
			return false;
		}
	}
	public void calcularPrazo()
	{
		if(status != "concluído")
		{
			if(this.prazo > 0)
			{
				this.prazo--;
				if(this.prazo == 0)
				{
					this.status = "atrasado";
				}
			}else 
			{
				System.out.println("Está atrasado a entrega do livro do livro "+this.isbn+" para CPF "+this.cpf+"!");
			}
		}
	}
	public void setAtrasado()
	{
		this.status = "atrasado";
		this.prazo = 0;
	}
	public void setConcluido()
	{
		this.status = "concluído";
		this.prazo = 0;
	}
	public static void getEmprestimos(String tipo, List<Emprestimo> emp)
	{
		for(int i = 0; i < emp.size(); i++)
		{
			if(emp.get(i).getStatus() == tipo)
			{
				System.out.println("ISBN: "+emp.get(i).getISBN());
				System.out.println("CPF: "+emp.get(i).getCPF());
				System.out.println("Status: "+emp.get(i).getStatus());
				System.out.println("Prazo: "+emp.get(i).getPrazo());
			}
		}
	}
}
