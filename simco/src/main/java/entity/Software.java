package entity;

import java.util.ArrayList;

import dao.SoftwareDAO;

public class Software
{
	private int idsoftware;
	private String nome;

	public Software()
	{
		this.idsoftware = -1;
		this.nome = "";
	}

	public Software(int idsoftware, String nome)
	{
		this.idsoftware = idsoftware;
		this.nome = nome;
	}

	public int getIdsoftware()
	{
		return idsoftware;
	}

	public void setIdsoftware(int idsoftware)
	{
		this.idsoftware = idsoftware;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public static ArrayList<Software> getAllSoftware()
	{
		try 
		{ return SoftwareDAO.getAllSoftware(); }
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		return null;
	}
	
	public static ArrayList<Software> getSoftwareInLaboratorio(Laboratorio laboratorio)
	{
		try
		{ return SoftwareDAO.getSoftwareInLaboratorio(laboratorio); }
		
		catch(Exception e)
		{ e.printStackTrace(); }
		
		return null;
	}
}
