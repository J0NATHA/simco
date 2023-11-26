package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Problema;

public class ProblemaDAO
{
	public static boolean insert(Problema problema) throws Exception
	{
		try
		{
			var currentDate = new java.util.Date();
					
			String sql = "INSERT INTO simco.problema(idcomputador, tipo, descricao, data, idusuario)"
					+    "VALUES(" + problema.getIdcomputador() 
					+    ", '" + problema.getTipo() 
					+    "', '" + problema.getDescricao() 
					+    "', '" + new java.sql.Date(currentDate.getTime())
					+    "', " + problema.getIdusuario() + ")";
			
			int n = Conexao.getStatement().executeUpdate(sql);

			return n == 1;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return false;
	}
	
	public static ArrayList<Problema> getAllProblemas() throws Exception
	{
		try
		{	
			String sql = "SELECT * FROM simco.problema;";
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);
			
			ArrayList<Problema> problemas = new ArrayList<Problema>();
			
			while(resultSet.next())
			{
				problemas.add(
					new Problema(
						resultSet.getInt(1),
						resultSet.getInt(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getDate(5),
						resultSet.getInt(6)
					)
				);
			}
			
			return problemas;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
	
	public static ArrayList<Problema> getProblemaByTipo(String tipo) throws Exception
	{
		try
		{	
			String sql = "SELECT * FROM simco.problema "
					   + "WHERE tipo = '" + tipo + "';";
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);
			
			ArrayList<Problema> problemas = new ArrayList<Problema>();
			
			while(resultSet.next())
			{
				problemas.add(
					new Problema(
						resultSet.getInt(1),
						resultSet.getInt(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getDate(5),
						resultSet.getInt(6)
					)
				);
			}
			
			return problemas;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
	
	public static ArrayList<Problema> getProblemasByUsuario(int idusuario) throws Exception
	{
		try
		{	
			String sql = "SELECT * FROM simco.problema "
					   + "WHERE idusuario = " + idusuario;
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);
			
			ArrayList<Problema> problemas = new ArrayList<Problema>();
			
			while(resultSet.next())
			{
				problemas.add(
					new Problema(
						resultSet.getInt(1),
						resultSet.getInt(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getDate(5),
						resultSet.getInt(6)
					)
				);
			}
			
			return problemas;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
	
	public static ArrayList<Problema> getProblemasByComputador(int idcomputador) throws Exception
	{
		try
		{	
			String sql = "SELECT * FROM simco.problema "
					   + "WHERE idcomputador = " + idcomputador;
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);
			
			ArrayList<Problema> problemas = new ArrayList<Problema>();
			
			while(resultSet.next())
			{
				problemas.add(
					new Problema(
						resultSet.getInt(1),
						resultSet.getInt(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getDate(5),
						resultSet.getInt(6)
					)
				);
			}
			
			return problemas;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
	
	public static ArrayList<Problema> getProblemasByLaboratorio(int idlaboratorio) throws Exception
	{
		try
		{	
			String sql = "SELECT idproblema, idcomputador, tipo, descricao, data, idusuario FROM "
					   + "	(SELECT * FROM simco.problema JOIN simco.computador USING(idcomputador)) AS COMPPROB "
					   + "WHERE idlaboratorio = " + idlaboratorio;
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);
			
			ArrayList<Problema> problemas = new ArrayList<Problema>();
			
			while(resultSet.next())
			{
				problemas.add(
					new Problema(
						resultSet.getInt(1),
						resultSet.getInt(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getDate(5),
						resultSet.getInt(6)
					)
				);
			}
			
			return problemas;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
	
	public static ArrayList<Problema> getProblemasBetweenDates(String minDate, String maxDate) throws Exception
	{
		try
		{	
			String sql  = "SELECT * FROM simco.problema "
						+ "WHERE problema.data between '" + minDate + "' and '" + maxDate + "';";
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);
			
			ArrayList<Problema> problemas = new ArrayList<Problema>();
			
			while(resultSet.next())
			{
				problemas.add(
					new Problema(
						resultSet.getInt(1),
						resultSet.getInt(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getDate(5),
						resultSet.getInt(6)
					)
				);
			}
			
			return problemas;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
}
