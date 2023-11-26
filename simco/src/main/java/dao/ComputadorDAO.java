package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Computador;
import entity.Laboratorio;
import entity.Software;

public class ComputadorDAO
{
	public static boolean insert(Computador computador) throws Exception
	{
		try
		{
			if(computador.getNome().isBlank())
			{ throw new Exception("Nome vazio"); }
			
			String sql = "INSERT INTO simco.computador(idlaboratorio, nome) "
					   + "VALUES (" + computador.getIdlaboratorio() + ", 'M-" + computador.getNome() + "');";

			int n = Conexao.getStatement().executeUpdate(sql);

			return n == 1;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return false;
	}
	
	public static boolean insert(Computador computador, ArrayList<Software> softwares) throws Exception
	{
		try
		{
			if(computador.getNome().isBlank())
			{ throw new Exception("Nome vazio"); }
			
			String sql = "INSERT INTO simco.computador(idlaboratorio, nome) "
					   + "VALUES (" + computador.getIdlaboratorio() + ", 'M-" + computador.getNome() + "');";

			int n = Conexao.getStatement().executeUpdate(sql);
			
			boolean softwareInserted = false;
			
			for(var software : softwares)
			{
				softwareInserted = CompSoftDAO.insert(software.getIdsoftware());
				
				if(!softwareInserted)
				{ break; }
			}

			return n == 1 && softwareInserted;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return false;
	}

	public static ArrayList<Computador> getAllComputadores() throws Exception
	{
		try
		{
			String sql = "SELECT * FROM simco.computador ORDER BY idlaboratorio, idcomputador, nome;";

			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);

			ArrayList<Computador> computadores = new ArrayList<Computador>();

			while(resultSet.next())
			{
				computadores.add(
					new Computador(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getInt(3)
					)
				);
			}

			return computadores;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
	
	
	
	public static ArrayList<Computador> getComputadoresInLaboratorio(Laboratorio laboratorio) throws Exception
	{
		try
		{
			String sql = "SELECT * FROM simco.computador "
					   + "WHERE idlaboratorio = " + laboratorio.getIdlaboratorio()
					   + " ORDER BY nome;";
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);

			ArrayList<Computador> computadores = new ArrayList<Computador>();

			while(resultSet.next())
			{
				computadores.add(
					new Computador(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getInt(3)
				));
			}

			return computadores;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
	
	public static ArrayList<Computador> getAllComputadoresWithSoftware(int idsoftware) throws Exception
	{
		try
		{
			String sql = "SELECT idcomputador, nome, idlaboratorio FROM"
					   + "(SELECT * FROM simco.compsoft JOIN simco.computador USING(idcomputador)) as COMPSOFT "
					   + "WHERE idsoftware = " + idsoftware;
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);
			
			ArrayList<Computador> computadores = new ArrayList<Computador>();
			
			while(resultSet.next())
			{
				computadores.add(
					new Computador(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getInt(3)
					)
				);
			}
			
			return computadores;
		}
		
		catch (Exception e)
		{ e.printStackTrace(); }
		
		finally
		{ Conexao.fechaConexao(); }
		
		return null;
	}
	
	public static ArrayList<Computador> getComputadoresBySoftwareInLaboratorio(int idsoftware, int idlaboratorio) throws Exception
	{
		try
		{
			String sql = "SELECT idcomputador, nome FROM " 
					   + "(SELECT * FROM simco.compsoft JOIN simco.computador USING(idcomputador)) as COMPSOFT " 
					   + "WHERE idsoftware = " + idsoftware + " and idlaboratorio = " + idlaboratorio;
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);

			ArrayList<Computador> computadores = new ArrayList<Computador>();

			while(resultSet.next())
			{
				computadores.add(
					new Computador(
						resultSet.getInt(1),
						resultSet.getString(2),
						idlaboratorio
					)
				);
			}

			return computadores;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
	
	public static Computador getComputadorById(int id) throws Exception
	{
		try
		{
			String sql = "SELECT * FROM simco.computador "
					   + "WHERE idcomputador = " + id;
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);

			if(resultSet.next())
			{
				return new Computador(
					resultSet.getInt(1),
					resultSet.getString(2),
					resultSet.getInt(3) 
				);
			}
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
}
