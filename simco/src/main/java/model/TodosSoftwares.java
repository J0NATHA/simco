package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Computador;
import entity.Laboratorio;
import entity.Software;

/**
 * Servlet implementation class TodosSoftwares
 */
@WebServlet("/TodosSoftwares")
public class TodosSoftwares extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodosSoftwares()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String pag;
		String laboratorioId = request.getParameter("laboratorio");
		
		pag = "<html><head><title>Todos os Softwares</title>"
		    + "<link rel='stylesheet' href='styles.css'/>"
			+ "</head>"
		
			+"<body>"
			+ "<div class='container'>"
			+ "		<h1 class='bordered-text'>"
			+ "			Todos os Softwares";
			
			if(!laboratorioId.equals("todos"))
			{ pag += " - " + Laboratorio.getLaboratorioById(Integer.parseInt(laboratorioId)).getNome(); }
			
		pag += "</h1>"
			+  "</div>"
			+  "<div class='list'>"
			+  "	<a href='relatorios.html' style='background-color: #999999;' class='box-title'> "
			+  "		Voltar"
			+  "	</a>";
		
		ArrayList<Software> softwares = getSoftware(laboratorioId);
		
		for(var software : softwares)
		{
			var computadores = getComputadoresInLaboratorioWithSoftware(laboratorioId, software.getIdsoftware());
			
			pag += "<p class='box-title' style='word-wrap: break-word;'> Id: " + software.getIdsoftware() + "<br/>"
				+  "Nome: " + software.getNome() + "<br/>"
				+  "Computadores: " + computadoresNames(computadores) + "</p>";
		}
		
		pag +="	<a href='relatorios.html' style='background-color: #999999;' class='box-title'> "
			+ "		Voltar"
			+ "	</a>"
			+ "</div>"
			+ "</body> </html>";
		
		response.getWriter().print(pag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private ArrayList<Software> getSoftware(String laboratorioId)
	{
		if(laboratorioId.equals("todos"))
		{
			return Software.getAllSoftware();
		}
		
		Laboratorio laboratorio = Laboratorio.getLaboratorioById(Integer.parseInt(laboratorioId));
		return Software.getSoftwareInLaboratorio(laboratorio);
	}
	
	private String computadoresNames(ArrayList<Computador> computadores)
	{
		if(computadores == null)
		{ return "Não encontrado"; }
		
		String value = "";
		
		for(var computador : computadores)
		{
			value += computador.getNome() + ", ";
		}
		
		if(value.length() > 2)
		// Retorna a string sem a vírgula após o último item
		{ return value.substring(0, value.length() - 2); }
		
		return value;
	}
	
	private ArrayList<Computador> getComputadoresInLaboratorioWithSoftware(String laboratorioId, int idsoftware)
	{
		if(laboratorioId.equals("todos"))
		{ return Computador.getAllComputadoresWithSoftware(idsoftware); }
		
		return Computador.getComputadoresBySoftwareInLaboratorio(
				idsoftware, Integer.parseInt(laboratorioId));
	}
}
