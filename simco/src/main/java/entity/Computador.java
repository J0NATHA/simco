package entity;

import java.util.ArrayList;

import dao.ComputadorDAO;

public class Computador
{
	private int idcomputador, idlaboratorio;
	private String nome;

	public Computador()
	{
		this.idcomputador = -1;
		this.idlaboratorio = -1;
		this.nome = "";
	}

	public Computador(int idcomputador)
	{
		this.idcomputador = idcomputador;
		this.idlaboratorio = -1;
		this.nome = "";
	}
	
	public Computador(String nome)
	{
		this.idcomputador = -1;
		this.idlaboratorio = -1;
		this.nome = nome;
	}

	public Computador(int idcomputador, int idlaboratorio)
	{
		this.idcomputador = idcomputador;
		this.idlaboratorio = idlaboratorio;
	}

	public Computador(int idlaboratorio, String nome)
	{
		this.idlaboratorio = idlaboratorio;
		this.nome = nome;
	}

	public Computador(int idcomputador, String nome, int idlaboratorio)
	{
		this.idcomputador = idcomputador;
		this.idlaboratorio = idlaboratorio;
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public int getIdlaboratorio()
	{
		return idlaboratorio;
	}

	public void setIdlaboratorio(int idlaboratorio)
	{
		this.idlaboratorio = idlaboratorio;
	}

	public int getIdcomputador()
	{
		return idcomputador;
	}

	public void setIdcomputador(int idcomputador)
	{
		this.idcomputador = idcomputador;
	}

	public boolean insert(Computador computador)
	{
		try
		{ return ComputadorDAO.insert(computador); }

		catch (Exception e)
		{ e.printStackTrace(); }

		return false;
	}
	
	public boolean insert(Computador computador, ArrayList<Software> softwares)
	{
		try
		{ return ComputadorDAO.insert(computador, softwares); }

		catch (Exception e)
		{ e.printStackTrace(); }

		return false;
	}
	
	public static ArrayList<Computador> getAllComputadores()
	{
		try
		{ return ComputadorDAO.getAllComputadores(); }

		catch(Exception e)
		{ e.printStackTrace(); }

		return null;
	}
	
	public static ArrayList<Computador> getComputadoresInLaboratorio(Laboratorio laboratorio)
	{
		try
		{ return ComputadorDAO.getComputadoresInLaboratorio(laboratorio); }
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		return null;
	}
	
	public static Computador getComputadorById(int id)
	{
		try
		{ return ComputadorDAO.getComputadorById(id); }
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		return null;
	}
	
	public static ArrayList<Computador> getAllComputadoresWithSoftware(int idsoftware)
	{
		try
		{ return ComputadorDAO.getAllComputadoresWithSoftware(idsoftware); }
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		return null;
	}
	
	public static ArrayList<Computador> getComputadoresBySoftwareInLaboratorio(int idsoftware, int idlaboratorio)
	{
		try
		{ return ComputadorDAO.getComputadoresBySoftwareInLaboratorio(idsoftware, idlaboratorio); }
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		return null;
	}
}
