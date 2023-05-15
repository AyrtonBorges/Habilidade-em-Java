package Biblioteca;

import java.util.List;

public class Livro {
	private String titulo;
	private String isbn;
	private String nomeEditora;
	private String categoria;
	
	public Livro(String titulo, String isbn, String nomeEditora, String categoria) throws Exception
	{
		if(verificadorISBN(isbn))
		{
			this.titulo = titulo;
			this.isbn = isbn;
			this.nomeEditora = nomeEditora;
			this.categoria = categoria;
		}else throw new Exception("Livro não criado,ISBN incorreto!");
		
	}
	public static boolean verificadorISBN(String isbn)
	{
		// Link de onde retirei a informação de verificação do ISBN13: https://homepages.dcc.ufmg.br/~rodolfo/aedsi-2-12/TP1.pdf
		// Link de onde retirei a informação de verificação do ISBN10: https://homepages.dcc.ufmg.br/~rodolfo/aedsi-2-10/regrasProgramas%20ISBN.html
		isbn = isbn.replace("-","");
		// Verifica se o tamanho da String é 13 ou 10
		if(!(isbn.length() == 13 || isbn.length() == 10))
		{
			System.out.println("Quantidade de números não é característico de um ISBN!");
			return false;
		}
		if(isbn.length() == 13)
		{
			int multiplicadorPonteiro = 1;
			int resultadoFinal = 0;
			for(int i = 0; i < isbn.length()-1; i++)
			{
				int num = Character.getNumericValue(isbn.charAt(i));
				resultadoFinal += num*multiplicadorPonteiro;
				if(multiplicadorPonteiro == 1)
					multiplicadorPonteiro = 3;
				else if(multiplicadorPonteiro == 3)
					multiplicadorPonteiro = 1;
			}
			int restoDivisao = resultadoFinal%10;
			if((10-restoDivisao) == Character.getNumericValue(isbn.charAt(isbn.length()-1)))
			{
				return true;
			}
			else
			{
				System.out.println("O ISBN-13 está errado!");
				return false; 
			}
		}else
		{
			int multiplicadorPonteiro = 10;
			int resultadoFinal = 0;
			for(int i = 0; i < 10; i++)
			{
				resultadoFinal += Character.getNumericValue(isbn.charAt(i)) * multiplicadorPonteiro--;
			}
			resultadoFinal %= 11;
			if(resultadoFinal == 0)
			{
				return true;
			}else if(resultadoFinal-11 == Character.getNumericValue(isbn.charAt(9)))
			{
				return true;
			}else
			{
				System.out.println("O ISBN-10 está errado!");
				return false;
			}
		}
	}
	public static boolean verificadorLivro(String isbn,List<Livro> livros) throws Exception
	{
		if(verificadorISBN(isbn))
		{
			isbn = (isbn.replace(".", "")).replace("-", "");
			for(int i = 0; i < livros.size();i++)
			{
				if(isbn.equals(livros.get(i).getISBN()))
					return true;
				else
					return false;
			}
			return false;
		}else throw new Exception("Livro está inválido!");
		
	}
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}
	public void setISBN(String isbn)
	{
		if(verificadorISBN(isbn))
			this.isbn = isbn;
	}
	public void setNomeEditora(String nomeEditora)
	{
		this.nomeEditora = nomeEditora;
	}
	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}
	public String getTitulo()
	{
		return this.titulo;
	}
	public String getISBN()
	{
		return this.isbn;
	}
	public String getNomeEditora()
	{
		return this.nomeEditora;
	}
	public String getCategoria()
	{
		return this.categoria;
	}
}
