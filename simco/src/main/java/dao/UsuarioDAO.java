package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Usuario;

public class UsuarioDAO
{
	public static boolean insert(Usuario usuario) throws Exception
	{
		try
		{
			String sql = "INSERT INTO simco.usuario(nome, email) values ('"
					+ usuario.getNome() + "', '" + usuario.getEmail() + "');";

			int n = Conexao.getStatement().executeUpdate(sql);

			return n == 1;
		}
		catch (Exception e)
		{ e.printStackTrace(); }

		finally
		{ Conexao.fechaConexao(); }

		return false;
	}
	
	public static ArrayList<Usuario> getAllUsuarios() throws Exception
	{
		try
		{
			String sql = "SELECT * FROM simco.usuario";
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);
			
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			
			while(resultSet.next())
			{
				usuarios.add(
					new Usuario(
						resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3)
					)
				);
			}
			
			return usuarios;
 		}
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		finally
		{ Conexao.fechaConexao(); }
		
		return null;
	}
	
	public static Usuario getUsuarioById(int id) throws Exception
	{
		try
		{
			String sql = "SELECT * FROM simco.usuario "
					   + "WHERE idusuario = " + id;
			
			ResultSet resultSet = Conexao.getStatement().executeQuery(sql);
			
			if(resultSet.next())
			{
				return new Usuario(
					resultSet.getInt(1),
					resultSet.getString(2),
					resultSet.getString(3)
				);
			}
 		}
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		finally
		{ Conexao.fechaConexao(); }
		
		return null;
	}
}
