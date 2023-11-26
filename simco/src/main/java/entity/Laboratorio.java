package entity;

import java.util.ArrayList;

import dao.LaboratorioDAO;

public class Laboratorio
{
	private int idlaboratorio;
	private String nome;

	public Laboratorio()
	{
		this.idlaboratorio = -1;
		this.nome = "";
	}

	public Laboratorio(int idlaboratorio, String nome)
	{
		this.idlaboratorio = idlaboratorio;
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

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public static Laboratorio getLaboratorioById(int id)
	{
		try
		{ return LaboratorioDAO.getLaboratorioById(id); }
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		return null;
	}
	
	public static ArrayList<Laboratorio> getAllLaboratorios() 
	{
		try
		{ return LaboratorioDAO.getAllLaboratorios(); }
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		return null;
	}
	
	public boolean compareById(Laboratorio other)
	{
		if(other == null) 
		{ return false; }
		
		return this.getIdlaboratorio() == other.getIdlaboratorio();
	}
}
