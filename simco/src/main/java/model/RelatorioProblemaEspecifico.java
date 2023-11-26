package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Computador;
import entity.Laboratorio;
import entity.Problema;
import entity.Usuario;

/**
 * Servlet implementation class RelatorioProblemaEspecifico
 */
@WebServlet("/RelatorioProblemaEspecifico")
public class RelatorioProblemaEspecifico extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RelatorioProblemaEspecifico()
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
		String tipo = request.getParameter("tipo");
		
		String pag  = "<html><head><title>Relatórios específicos de problemas</title>"
					+ "<link rel='stylesheet' href='styles.css'/>" 
					+ "</head>"
		
					+ "<body>" 
					+ "<div class='container'>" 
					+ "		<h1 class='bordered-text'>"
					+ "			Relatório de problemas por " + tipo 
					+ " 	</h1>" 
					+ "</div>"
		
					+ "<div class='list'>"
					+ "		<a href='relatoriosProblemas.html' style='background-color: #999999;' class='box-title'>"
					+ "			Voltar"
					+ "		</a>"
					+ getListItems(request, tipo);
		
		pag += "<a href='relatoriosProblemas.html' style='background-color: #999999;' class='box-title'> "
			 + "	Voltar"
			 + "</a>"
			 + "</div> </body> </html>";
		
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
	
	private String getListItems(HttpServletRequest request, String tipo)
	{
		Integer filtro = -1;
		
		
		if(!tipo.equals("datas"))
		{ 
			filtro = Integer.valueOf(request.getParameter("filtro"));
			
			if(filtro == null)
			{ return ""; }
		}
		
		switch(tipo)
		{
			case "usuario":
			{
				return problemasByUsuario(filtro.intValue());
			}
	
			case "computador":
			{
				return problemasByComputador(filtro.intValue());
			}
	
			case "laboratorio":
			{
				return problemasByLaboratorio(filtro.intValue());
			}
	
			case "datas":
			{
				ArrayList<String> datas = new ArrayList<String>(
					Arrays.asList(
						request.getParameter("dataInicial"),
						request.getParameter("dataFinal")
					)
				);
				
				return problemasBetweenDates(datas);
			}
			
			default:
			{
				return "ERRO DE TIPO";
			}
		}
	}
	
	private String problemasByUsuario(int idusuario)
	{
		ArrayList<Problema> problemas = Problema.getProblemasByUsuario(idusuario);
		
		return "<p class='box-title' style='word-wrap: break-word;'>"
			 + 		"<strong>"
			 + 			"Filtrado por usuário (" + Usuario.getUsuarioById(idusuario).getNome() + ")"
			 + 		"</strong>"
			 + "</p>"
			 + ProblemaGeral.problemasList(problemas);
	}
	
	private String problemasByComputador(int idcomputador)
	{
		ArrayList<Problema> problemas = Problema.getProblemasByComputador(idcomputador);
		
		return "<p class='box-title' style='word-wrap: break-word;'>"
			 + 		"<strong>"
    		 + 			"Filtrado por computador (" + Computador.getComputadorById(idcomputador).getNome() + ")"
			 + 		"</strong>"
    		 + "</p>"
			 + ProblemaGeral.problemasList(problemas);
	}
	
	private String problemasByLaboratorio(int idlaboratorio)
	{
		ArrayList<Problema> problemas = Problema.getProblemasByLaboratorio(idlaboratorio);
		
		return "<p class='box-title' style='word-wrap: break-word;'>"
			 + 		"<strong>"
			 + 			"Filtrado por laboratório (" + Laboratorio.getLaboratorioById(idlaboratorio).getNome() + ")"
	 		 + 		"</strong>"
			 + "</p>"
			 + ProblemaGeral.problemasList(problemas);
	}
	
	private String problemasBetweenDates(ArrayList<String> dates)
	{
		String minDate = dates.get(0);
		String maxDate = dates.get(1);
		
		ArrayList<Problema> problemas = Problema.getProblemasBetweenDates(minDate, maxDate);
		
		minDate = ProblemaGeral.dateFormat(minDate);
		maxDate = ProblemaGeral.dateFormat(maxDate);
		
		return "<p class='box-title' style='word-wrap: break-word;'>"
		 + 		"<strong>"
		 + 			"Filtrado por data (Entre " + minDate + " e " + maxDate + ")"
		 + 		"</strong>"
		 + "</p>"
		 + ProblemaGeral.problemasList(problemas);
	}
}
