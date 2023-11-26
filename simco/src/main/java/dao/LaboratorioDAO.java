package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Laboratorio;

public class LaboratorioDAO
{
	public static Laboratorio getLaboratorioById(int idlaboratorio) throws Exception
	{
		try
		{
			String sql = "SELECT * FROM simco.laboratorio WHERE idlaboratorio = " + idlaboratorio;
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);

			if (resultSet.next())
			{
				return new Laboratorio(
					resultSet.getInt(1),
					resultSet.getString(2)
			   );
			}
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
	
	public static ArrayList<Laboratorio> getAllLaboratorios() throws Exception
	{
		try
		{
			String sql = "SELECT * FROM simco.laboratorio;";
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);

			ArrayList<Laboratorio> laboratorios = new ArrayList<>();

			while(resultSet.next())
			{
				laboratorios.add(
					new Laboratorio(
						resultSet.getInt(1), 
						resultSet.getString(2)
					)
				);
			}

			return laboratorios;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return null;
	}
}
