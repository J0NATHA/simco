package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Laboratorio;

/**
 * Servlet implementation class SelecionaLaboratorio
 */
@WebServlet(
{ "/RelatorioHardware", "/RelatorioSoftware" })
public class SelecionaLaboratorio extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelecionaLaboratorio()
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
		String requestPath = request.getServletPath();
		String tipoRelatorio = getTipoRelatorio(requestPath);
		
		String pag;
		
		pag = "<html><head><title>Seleção de laboratório</title>"
			    + "<link rel='stylesheet' href='styles.css'/>"
				+ "</head>"
			
				+"<body>"
				+ "<div class='container'>"
				+ "		<h1 class='bordered-text'>"
				+ "			Seleção de laboratório"
				+ "		</h1>"
				+ "</div>"
				
				+ "<div class='container2' style='text-align: end; flex-direction: column; height: 35vh'>"
				+ "		<form action='Todos" + tipoRelatorio + "s' method='post'>"
				+ " 		<label for='laboratorio' class='bordered-text'> Escolha o laboratório: </label>"
				+ " 		<select name='laboratorio' id='laboratorio'>"
				+ "   	 		<option value='todos'>TODOS</option>";
				
		ArrayList<Laboratorio> laboratorios = Laboratorio.getAllLaboratorios();
		
		for(var laboratorio : laboratorios)
		{
			pag += "<option value='" + laboratorio.getIdlaboratorio() + "'>" + laboratorio.getNome() + "</option>";
		}
		
		pag += "</select> "
			+  "<input type='submit' value='PESQUISAR' class='bordered-text' style='width:94%;'/>"
			+  "</form> "
			+  "<a href=\"relatorios.html\" style=\"background-color: #999999; width:85%;\" class='box-title'> \r\n"
			+ "		Voltar\r\n"
			+ "	</a>"
			+  "</div> </body> </html>";
		
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

	private String getTipoRelatorio(String path)
	{
		switch(path)
		{
			case "/RelatorioHardware":
			{
				return "Hardware";
			}
			
			case "/RelatorioSoftware":
			{
				return "Software";
			}
			
			default:
			{
				return "ERRO";
			}
		}
	}
	
}
