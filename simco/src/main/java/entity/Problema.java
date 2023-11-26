package entity;

import java.sql.Date;
import java.util.ArrayList;

import dao.ProblemaDAO;

public class Problema
{
	private int idproblema, idcomputador, idusuario;
	private String tipo, descricao;
	private Date data;

	public Problema()
	{
		this.idproblema = -1;
		this.idcomputador = -1;
		this.tipo = "";
		this.descricao = "";
		this.data = null;
	}

	public Problema(int idproblema, int idcomputador, String tipo, String descricao, Date data, int idusuario)
	{
		this.idproblema = idproblema;
		this.idcomputador = idcomputador;
		this.idusuario = idusuario;
		this.tipo = tipo;
		this.descricao = descricao;
		this.data = data;
	}

	public Problema(int idcomputador, String tipo, String descricao)
	{
		this.idproblema = -1;
		this.idcomputador = idcomputador;
		this.idusuario = -1;
		this.tipo = tipo;
		this.descricao = descricao;
		this.data = null;
	}

	public Problema(int idcomputador, String tipo, String descricao, int idusuario)
	{
		this.idproblema = -1;
		this.idcomputador = idcomputador;
		this.idusuario = idusuario;
		this.tipo = tipo;
		this.descricao = descricao;
		this.data = null;
	}

	public int getIdproblema()
	{
		return idproblema;
	}

	public void setIdproblema(int idproblema)
	{
		this.idproblema = idproblema;
	}

	public int getIdcomputador()
	{
		return idcomputador;
	}

	public void setIdcomputador(int idcomputador)
	{
		this.idcomputador = idcomputador;
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public Date getData()
	{
		return data;
	}

	public void setData(Date data)
	{
		this.data = data;
	}

	public int getIdusuario()
	{
		return idusuario;
	}

	public void setIdusuario(int idusuario)
	{
		this.idusuario = idusuario;
	}

	public boolean insert(Problema problema)
	{
		try
		{ return ProblemaDAO.insert(problema); }

		catch (Exception e)
		{ e.printStackTrace(); }

		return false;
	}
	
	public static ArrayList<Problema> getAllProblemas()
	{
		try
		{ return ProblemaDAO.getAllProblemas(); }

		catch (Exception e)
		{ e.printStackTrace(); }

		return null;
	}

	public static ArrayList<Problema> getProblemaByTipo(String tipo)
	{
		try
		{ return ProblemaDAO.getProblemaByTipo(tipo); }

		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static ArrayList<Problema> getProblemasByUsuario(int idusuario)
	{
		try
		{ return ProblemaDAO.getProblemasByUsuario(idusuario); }

		catch (Exception e)
		{ e.printStackTrace(); }

		return null;
	}

	public static ArrayList<Problema> getProblemasByComputador(int idcomputador)
	{
		try
		{ return ProblemaDAO.getProblemasByComputador(idcomputador); }

		catch (Exception e)
		{ e.printStackTrace(); }

		return null;
	}

	public static ArrayList<Problema> getProblemasByLaboratorio(int idlaboratorio)
	{
		try
		{ return ProblemaDAO.getProblemasByLaboratorio(idlaboratorio); }

		catch (Exception e)
		{ e.printStackTrace(); }

		return null;
	}

	public static ArrayList<Problema> getProblemasBetweenDates(String minDate, String maxDate)
	{
		try
		{ return ProblemaDAO.getProblemasBetweenDates(minDate, maxDate); }

		catch (Exception e)
		{ e.printStackTrace(); }

		return null;
	}
}
