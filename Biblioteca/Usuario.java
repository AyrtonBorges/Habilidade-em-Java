package Biblioteca;

import java.util.List;

public class Usuario {
	private String nome;
	private String CPF;
	
	public Usuario(String nome, String CPF) throws Exception
	{
		if(verificarCPF(CPF))
		{
			this.CPF = (CPF.replace(".","")).replace("-", "");
			this.nome = nome;
		}else throw new Exception("CPF "+CPF+" inválido, conta não criada!");
	}
	public static boolean verificarCPF(String CPF) 
	{
		CPF = (CPF.replace(".","")).replace("-","");
		if(CPF.length() != 11)
		{
			System.out.println("Não é um CPF!");
			return false;
		}
		int etapasVerificacao = 0;
		int maiorMultiplica = 10;
		int posicao = 8; 
		for(int y = 0; y <= 1; y++)
		{
			int valor = 0;
			int multiplica = maiorMultiplica++;
			for(int i = 0; i <= posicao; i++)
			{
				int num = Character.getNumericValue(CPF.charAt(i));
				valor += num*multiplica--;
			}
			if((valor*10)%11 == Character.getNumericValue(CPF.charAt(posicao+1)))
			{
				etapasVerificacao++;
				
			}
			posicao++;
		}
		if(etapasVerificacao == 2)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	// Verificador Antigo!
	/*
	 * public static boolean verificarCPF(String CPF) { CPF =
	 * (CPF.replace(".","")).replace("-",""); if(CPF.length() != 11) {
	 * System.out.println("Não é um CPF!"); return false; } int etapasVerificacao =
	 * 0;
	 * 
	 * for(int y = 1; y >= 0; y--) { int valor = 0; int multiplica = 11-y; for(int i
	 * = 0; i < CPF.length(); i++) { int num =
	 * Character.getNumericValue(CPF.charAt(i)); if(multiplica < 2) multiplica = 0;
	 * if((i == 9 || i == 10) && (valor*10)%11 == num) { etapasVerificacao++; }
	 * valor += num*multiplica--; } } if(etapasVerificacao == 2) { return true;
	 * }else { return false; } }
	 */
	public static boolean verificadorPessoa(String CPF,List<Usuario> pessoas) throws Exception
	{
		if(verificarCPF(CPF))
		{
			CPF = (CPF.replace(".", "")).replace("-", "");
			for (Usuario element : pessoas) {
				if(CPF.equals(element.getCPF()))
					return true;
				else
					return false;
			}
			return false;
		}else throw new Exception("CPF está inválido!");
		
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public void setCPF(String CPF)
	{
		if(verificarCPF(CPF))
			this.CPF = CPF;
	}
	public String getNome()
	{
		return this.nome;
	}
	public String getCPF()
	{
		return this.CPF;
	}
}
