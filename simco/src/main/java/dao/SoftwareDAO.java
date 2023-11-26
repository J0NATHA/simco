package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Laboratorio;
import entity.Software;

public class SoftwareDAO
{
	public static ArrayList<Software> getAllSoftware() throws Exception
	{
		try
		{
			String sql = "SELECT * FROM simco.software;";
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);
			
			ArrayList<Software> software = new ArrayList<Software>();
			
			while(resultSet.next())
			{
				software.add(
					new Software(
						resultSet.getInt(1),
						resultSet.getString(2)
					)
				);
			}
			
			return software;
		}
		catch(Exception e)
		{ e.printStackTrace(); }
		
		finally
		{ Conexao.fechaConexao(); }
		
		return null;
	}
	
	public static ArrayList<Software> getSoftwareInLaboratorio(Laboratorio laboratorio) throws Exception
	{
		try
		{
			String sql  = "SELECT * FROM simco.software WHERE idsoftware in "
						+ "(SELECT DISTINCT idsoftware FROM simco.computador "
					    + "JOIN simco.compsoft USING (idcomputador) "
					    + "WHERE idlaboratorio = " + laboratorio.getIdlaboratorio() + ")";
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);
			
			ArrayList<Software> software = new ArrayList<Software>();
			
			while(resultSet.next())
			{
				software.add(
					new Software(
						resultSet.getInt(1),
						resultSet.getString(2)
					)
				);
				
				
			}
			
			return software;
		}
		catch(Exception e)
		{ e.printStackTrace(); }
		
		finally
		{ Conexao.fechaConexao(); }
		
		return null;
	}
}
