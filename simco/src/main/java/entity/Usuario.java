package entity;

import java.util.ArrayList;

import dao.UsuarioDAO;

public class Usuario
{
	private int idusuario;
	private String nome, email;

	public Usuario()
	{
		this.idusuario = -1;
		this.nome = "";
		this.email = "";
	}
	
	public Usuario(String nome)
	{
		this.idusuario = -1;
		this.nome = nome;
		this.email = "";
	}

	public Usuario(String nome, String email)
	{
		this.idusuario = -1;
		this.nome = nome;
		this.email = email;
	}

	public Usuario(int idusuario, String nome, String email)
	{
		this.idusuario = idusuario;
		this.nome = nome;
		this.email = email;
	}

	public int getIdusuario()
	{
		return idusuario;
	}
	
	public void setIdusuario(int idusuario)
	{
		this.idusuario = idusuario;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public boolean insert(Usuario usuario)
	{
		try
		{ return UsuarioDAO.insert(usuario); }

		catch (Exception e)
		{ e.printStackTrace(); }

		return false;
	}
	
	public static ArrayList<Usuario> getAllUsuarios()
	{
		try
		{ return UsuarioDAO.getAllUsuarios(); }
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		return null;
	}
	
	public static Usuario getUsuarioById(int id)
	{
		try
		{ return UsuarioDAO.getUsuarioById(id); }
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		return null;
	}
}
