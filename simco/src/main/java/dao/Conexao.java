package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao
{
	private static Connection getConexao() throws Exception
	{
		try
		{
			// Definir os parâmetros de conexão:
			String url = "jdbc:mysql://localhost:3307/simco?useSSL=false&allowPublicKeyRetrieval=true&useTimezone=true&serverTimezone=America/Sao_Paulo";
			String user = "root";
			String password = "1234";
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Criar uma conexão com o banco de dados
			return DriverManager.getConnection(url, user, password);
		}
		
		catch (ClassNotFoundException e)
		{ throw new Exception("CNFE: " + e.getMessage()); }
		catch (SQLException e)
		{ throw new Exception("CNFE: " + e.getMessage()); }
	}

	public static Statement getStatement() throws Exception
	{
		try
		{ return getConexao().createStatement(); }
		
		catch (SQLException e)
		{ throw new Exception("CNFE: " + e.getMessage()); }
	}

	public static void fechaConexao() throws Exception
	{
		try
		{ getConexao().close(); }
		
		catch (SQLException e)
		{ throw new Exception("CNFE: " + e.getMessage()); }
	}
}
