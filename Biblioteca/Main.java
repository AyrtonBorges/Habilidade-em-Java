package Biblioteca;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;

public class Main{
	static List<Usuario> usuarios = new ArrayList<>();
	static List<Livro> livros = new ArrayList<>();
	static List<Emprestimo> emprestimos = new ArrayList<>();
	static Scanner tecladoEntrada = new Scanner(System.in);
	static LocalDate dataInicial = LocalDate.now();
	public static void main(String[] args) throws Exception {
		usuarios.add(new Usuario("Ayrton", "15590546702"));
		usuarios.add(new Usuario("Anderson", "12432533712"));
		usuarios.add(new Usuario("Davi", "172.035.217-88"));
		usuarios.add(new Usuario("João Pedro", "19544869794"));
		livros.add(new Livro("Manual DevOps", "8550802697", "Alta Books", "Informática"));
		livros.add(new Livro("Engenharia de Software moderna", "978-6500019506", "Independente", "Informática"));
		livros.add(new Livro("Código Limpo", "978-8576082675", "Alta Books", "Informática"));
		livros.add(new Livro("Python Para Data Science", "6555203374", "Alta Books", "Informática"));
		emprestimos.add(new Emprestimo("8550802697", "15590546702")); 
		emprestimos.get(0).setConcluido();
		emprestimos.add(new Emprestimo("978-6500019506", "12432533712"));
		emprestimos.add(new Emprestimo("6555203374", "19544869794"));
		emprestimos.get(2).setAtrasado();
		
		do 
		{
			int escolha = 0;
			System.out.print("Escolha uma opção:\n1 - Criar Usuário;\n2 - Criar Livro;\n3 - Criar emprestimo;\n4 - Consultar Usuario;\n5 - Consultar Livro\n6 - Consultar Emprestimo\n7 - Renovar Prazo de Emprestimo\n8 - Sair\nFaça a sua escolha: ");
			escolha = tecladoEntrada.nextInt();
			switch(escolha)
			{
				case 1:
					adicionarUsuario();
					break;
				case 2:
					adicionarLivro();
					break;
				case 3:
					adicionarEmprestimo();
					break;
				case 4:
					consultarUsuario("",0);
					break;
				case 5:
					consultarLivro("",0);
					break;
				case 6:
					consultarEmprestimo();
					break;
				case 7:
					renovarPrazo();
					break;
				case 8:
					System.out.println("Até um outro dia!");
					return;
				default:
					System.out.println("Não entendi sua escolha!");
			}
			System.out.println("Pressione enter para continuar...");
			tecladoEntrada.nextLine();
			diminuidorPrazo(0);
		}while(true);
	}
	
	/*
	 * Funções para manter a biblioteca
	 */
	public static void adicionarUsuario()
	{
		Usuario tempUsuario;
		String nome;
		String CPF;
		System.out.print("Insira o nome do usuário: ");
		tecladoEntrada.nextLine();
		nome = tecladoEntrada.nextLine();
		System.out.print("Insira o CPF do usuário: ");
		CPF = tecladoEntrada.nextLine();
		boolean verificacao;
		try
		{
			verificacao = Usuario.verificadorPessoa(CPF, usuarios);
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		if(!verificacao)
		{
			try
			{
				tempUsuario = new Usuario(nome,CPF);
				usuarios.add(tempUsuario);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}else
		{
			System.out.println("Já existe essa pessoa!");
		}
	}
	public static void adicionarLivro()
	{
		Livro tempLivro;
		String titulo;
		String isbn;
		String nomeEditora;
		String categoria;
		System.out.print("Insira o titulo do Livro: ");
		tecladoEntrada.nextLine();
		titulo = tecladoEntrada.nextLine();
		System.out.print("Insira o ISBN do Livro: " );
		isbn = tecladoEntrada.nextLine();
		System.out.print("Insira o nome da editora do Livro: ");
		nomeEditora = tecladoEntrada.nextLine();
		System.out.print("Insira a categoria do Livro: ");
		categoria = tecladoEntrada.nextLine();
		boolean verificacao;
		try
		{
			verificacao = Livro.verificadorLivro(isbn, livros);
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		if(!verificacao)
		{
			try
			{
				tempLivro = new Livro(titulo,isbn,nomeEditora,categoria);
				livros.add(tempLivro);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}else
		{
			System.out.println("Já existe esse livro!");
		}
	}
	public static void adicionarEmprestimo()
	{
		Emprestimo tempEmprestimo;
		String isbn;
		String cpf;
		System.out.print("Insira o ISBN do Livro: " );
		tecladoEntrada.nextLine();
		isbn = tecladoEntrada.nextLine();
		System.out.print("Insira o CPF do usuário: ");
		cpf = tecladoEntrada.nextLine();
		isbn = (isbn.replace(".","")).replace("-", "");
		cpf = (cpf.replace(".","")).replace("-", "");
		if(emprestimos.size() > 0)
		{
			for(int i = 0; i < emprestimos.size(); i++)
			{
				if(emprestimos.get(i).getStatus() != "concluído")
				{
					if(cpf.equals(emprestimos.get(i).getCPF()))
					{
						System.out.println("O emprestimo não pode ser efetuado, o Usuário possui emprestimo pendente!");
						return;
					}else if(isbn.equals(emprestimos.get(i).getISBN()))
					{
						System.out.println("O emprestimo não pode ser efetuado, o Usuário possui emprestimo pendente!");
						return;
					}
				}
			}
		}
		try
		{
			for(int i = 0; i < emprestimos.size(); i++)
			{
				
			}
			tempEmprestimo = new Emprestimo(isbn,cpf);
			emprestimos.add(tempEmprestimo);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	public static void consultarUsuario(String entrada,int escolha)
	{
		if(entrada == "")
		{
			System.out.print("Deseja consultar o usuario por qual meio? 1 - Nome, 2 - CPF? ");
			escolha = tecladoEntrada.nextInt();
		}
		
		boolean encontrado = false;
		if(escolha == 1)
		{
			String nome;
			System.out.print("Insira o nome do usuário: ");
			tecladoEntrada.nextLine();
			nome = tecladoEntrada.nextLine();
			for(int i = 0; i < usuarios.size(); i++)
			{
				if(nome.equals(usuarios.get(i).getNome()))
				{
					System.out.println("Nome: "+usuarios.get(i).getNome());
					System.out.println("CPF: "+usuarios.get(i).getCPF());
					encontrado = true;
				}
			}
		}else if(escolha == 2)
		{
			String CPF;
			if(entrada == "")
			{
				System.out.print("Insira o CPF do usuário: " );
				tecladoEntrada.nextLine();
				CPF = tecladoEntrada.nextLine();
			}else
			{
				CPF = entrada;
			}
			
			if(Usuario.verificarCPF(CPF))
			{
				CPF = (CPF.replace(".", "")).replace("-", "");
				for(int i = 0; i < usuarios.size(); i++)
				{
					if(CPF.equals(usuarios.get(i).getCPF()))
					{
						System.out.println("Nome: "+usuarios.get(i).getNome());
						System.out.println("CPF: "+usuarios.get(i).getCPF());
						encontrado = true;
					}
				}
			}
		}else
		{
			System.out.println("Não entendi sua escolha!");
		}
		if(!encontrado)
			System.out.println("Não foi encontrado o usuario!");
	}
	public static void consultarLivro(String entrada, int escolha)
	{
		if(entrada == "")
		{
			System.out.print("Deseja consultar o usuario por qual meio? 1 - Título, 2 - ISBN, 3 - Nome da editora, 4 - Categoria? ");
			escolha = tecladoEntrada.nextInt();
		}
		boolean encontrado = false;
		switch(escolha)
		{
			case 1:
				String titulo;
				System.out.print("Insira o titulo do livro: ");
				tecladoEntrada.nextLine();
				titulo = tecladoEntrada.nextLine();
				for(int i = 0; i < livros.size(); i++)
				{
					if(titulo.equals(livros.get(i).getTitulo()))
					{
						System.out.println("Nome: "+livros.get(i).getTitulo());
						System.out.println("CPF: "+livros.get(i).getISBN());
						System.out.println("CPF: "+livros.get(i).getNomeEditora());
						System.out.println("CPF: "+livros.get(i).getCategoria());
						encontrado = true;
					}
				}
				break;
			case 2:
				String ISBN;
				if(entrada == "")
				{
					System.out.print("Insira o ISBN do Livro: " );
					tecladoEntrada.nextLine();
					ISBN = tecladoEntrada.nextLine();
				}else
				{
					ISBN = entrada;
				}
				if(Livro.verificadorISBN(ISBN))
				{
					ISBN = (ISBN.replace(".", "")).replace("-", "");
					for(int i = 0; i < livros.size(); i++)
					{
						if(ISBN.equals(livros.get(i).getISBN()))
						{
							System.out.println("Nome: "+livros.get(i).getTitulo());
							System.out.println("CPF: "+livros.get(i).getISBN());
							System.out.println("CPF: "+livros.get(i).getNomeEditora());
							System.out.println("CPF: "+livros.get(i).getCategoria());
							encontrado = true;
						}
					}
				}
				break;
			case 3:
				String nomeEditora;
				System.out.print("Insira o nome da editora do livro: ");
				tecladoEntrada.nextLine();
				nomeEditora = tecladoEntrada.nextLine();
				for(int i = 0; i < livros.size(); i++)
				{
					if(nomeEditora.equals(livros.get(i).getNomeEditora()))
					{
						System.out.println("Nome: "+livros.get(i).getTitulo());
						System.out.println("CPF: "+livros.get(i).getISBN());
						System.out.println("CPF: "+livros.get(i).getNomeEditora());
						System.out.println("CPF: "+livros.get(i).getCategoria());
						encontrado = true;
					}
				}
				break;
			case 4:
				String categoria;
				System.out.print("Insira a categoria do livro: ");
				tecladoEntrada.nextLine();
				categoria = tecladoEntrada.nextLine();
				for(int i = 0; i < livros.size(); i++)
				{
					if(categoria.equals(livros.get(i).getCategoria()))
					{
						System.out.println("Nome: "+livros.get(i).getTitulo());
						System.out.println("CPF: "+livros.get(i).getISBN());
						System.out.println("CPF: "+livros.get(i).getNomeEditora());
						System.out.println("CPF: "+livros.get(i).getCategoria());
						encontrado = true;
					}
				}
				break;
			default:
				System.out.println("Não entendi sua escolha!");
		}
		if(!encontrado)
			System.out.println("Não foi encontrado o livro!");
	}
	public static void consultarEmprestimo()
	{
		System.out.print("Qual meio de consulta de empréstimo que gostaria de fazer?\n1 - ativo;\n2 - concluído;\n3 - atrasado;\n4 - ISBN e CPF;\nEscolha sua opção: ");
		int escolha = tecladoEntrada.nextInt();
		switch(escolha)
		{
			case 1:
				Emprestimo.getEmprestimos("ativo", emprestimos);
				break;
			case 2:
				Emprestimo.getEmprestimos("concluído", emprestimos);
				break;
			case 3:
				Emprestimo.getEmprestimos("atrasado", emprestimos);
			case 4:
				System.out.print("Insira o ISBN: ");
				tecladoEntrada.nextLine();
				String isbn = tecladoEntrada.nextLine();
				System.out.print("Insira o CPF: ");
				String cpf = tecladoEntrada.nextLine();
				isbn = (isbn.replace(".", "")).replace("-", "");
				cpf = (cpf.replace(".", "")).replace("-", "");
				for(Emprestimo tempEmprestimo : emprestimos)
				{
					if(isbn.equals(tempEmprestimo.getISBN()) && cpf.equals(tempEmprestimo.getCPF()))
					{
						System.out.println("Usuário emprestado: ");
						consultarUsuario(cpf, 2);
						System.out.println("O livro emprestado: ");
						consultarLivro(isbn, 2);
						System.out.println("Informações necessárias: ");
						System.out.println("Status: " + tempEmprestimo.getStatus());
						System.out.println("Prazo: " + tempEmprestimo.getPrazo());
					}
				}
				break;
			default:
				System.out.println("Não Entendi sua escolha!");
		}
	}
	public static void diminuidorPrazo(int escolha)
	{
		if(escolha == 0)
		{
			LocalDate dataFinal = LocalDate.now();
			Period periodo = Period.between(dataInicial, dataFinal);
			int diferencaDias = periodo.getDays() + (periodo.getMonths()*30);
			for(int i = 0; i < diferencaDias; i++)
			{
				for(Emprestimo tempEmprestimo : emprestimos)
				{
					tempEmprestimo.calcularPrazo();
				}
			}
		}else
		{
			for(Emprestimo tempEmprestimo : emprestimos)
			{
				tempEmprestimo.calcularPrazo();
			}
		}
		
	}
	public static void renovarPrazo() 
	{
		System.out.print("Insira o ISBN: ");
		tecladoEntrada.nextLine();
		String isbn = tecladoEntrada.nextLine();
		System.out.print("Insira o CPF: ");
		String cpf = tecladoEntrada.nextLine();
		for(Emprestimo tempEmprestimo : emprestimos)
		{
			if(isbn.equals(tempEmprestimo.getISBN()) && cpf.equals(tempEmprestimo.getCPF()))
			{
				if(tempEmprestimo.renovarPrazo())
					System.out.println("Renovado com sucesso!");
				return;
			}
		}
		System.out.println("Não foi encontrado o emprestimo!");
	}
}
