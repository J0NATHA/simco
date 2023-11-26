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

/**
 * Servlet implementation class TodosHardwares
 */
@WebServlet("/TodosHardwares")
public class TodosHardwares extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodosHardwares()
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
		
		pag = "<html><head><title>Todos os Hardwares</title>"
		    + "<link rel='stylesheet' href='styles.css'/>"
			+ "</head>"
		
			+"<body>"
			+ "<div class='container'>"
			+ "		<h1 class='bordered-text'>"
			+ "			Todos os Hardwares";
		
		if(!laboratorioId.equals("todos"))
		{ pag += " - " + Laboratorio.getLaboratorioById(Integer.parseInt(laboratorioId)).getNome(); }
		
		pag += "</h1>"
			+ "</div>"
			+ "<div class='list'>"
			+  "	<a href='relatorios.html' style='background-color: #999999;' class='box-title'> "
			+  "		Voltar"
			+  "	</a>";
		
			ArrayList<Computador> computadores = getComputadores(laboratorioId);
			
			for(var computador : computadores)
			{
				Laboratorio laboratorio = Laboratorio.getLaboratorioById(computador.getIdlaboratorio());
				
				if(laboratorio == null)
				{ continue; }
				
				pag += "<p class='box-title'> Máquina: " + computador.getNome() + "<br/>"
						+ "Laboratório: " + laboratorio.getNome() + "</p>";
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
	
	private ArrayList<Computador> getComputadores(String laboratorioId)
	{
		if(laboratorioId.equals("todos"))
		{ return Computador.getAllComputadores(); }
		
		Laboratorio laboratorio = Laboratorio.getLaboratorioById(Integer.parseInt(laboratorioId));
		
		if(laboratorio == null)
		{ return null; }
		
		return Computador.getComputadoresInLaboratorio(laboratorio);	
	}
}
