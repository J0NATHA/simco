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
import entity.Usuario;

/**
 * Servlet implementation class ProblemaEspecifico
 */
@WebServlet("/ProblemaEspecifico")
public class ProblemaEspecifico extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProblemaEspecifico()
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
		
					+ "<div class='container2' style='text-align: end; flex-direction: column; height: 35vh'>"
					+ " 	<form action='RelatorioProblemaEspecifico' method='post' style='text-align: end;'>"
					+ " 		<input type='hidden' name='tipo' value='" + tipo + "'/>";
				
		if(!tipo.equals("datas"))
		{
			pag += "<label for='filtro' class='bordered-text'>"
			// 			Primeira letra maiúscula, concatena o resto
				+   	tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + ": "
				+  "</label>";
		}
		
		pag += getOptionsFor(tipo)
			+ " 		<input type='submit' value='PESQUISAR' class='bordered-text' style='width: 94%;'/>"
			+ "		</form>"
			+ "		<a href='relatoriosProblemas.html' style='background-color: #999999; width: 85%;' class='box-title'> "
			+ "			Voltar" 
			+ "		</a>"
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

	private String getOptionsFor(String tipo)
	{
		switch (tipo)
		{
			case "usuario":
			{
				return getUsuarioSelect();
			}
	
			case "computador":
			{
				return getComputadorSelect();
			}
	
			case "laboratorio":
			{
				return getLaboratorioSelect();
			}
	
			case "datas":
			{
				return getDataFields();
			}
	
			default:
			{
				return "ERRO";
			}
		}
	}

	private String getUsuarioSelect()
	{
		ArrayList<Usuario> usuarios = Usuario.getAllUsuarios();

		if (usuarios == null)
		{ return "<option>Usuários não encontrados!</option>"; }

		String select = "<select name='filtro' id='filtro' style='width: 68%; text-align: center;'>";

		for (var usuario : usuarios)
		{
			select += "<option value='" + usuario.getIdusuario() + "'>" + usuario.getNome() + "</option>";
		}
		
		select += "</select>";
		
		return select;
	}

	private String getComputadorSelect()
	{
		ArrayList<Computador> computadores = Computador.getAllComputadores();

		if (computadores == null)
		{ return "<option>Computadores não encontrados!</option>"; }
		
		// Select computador
		String select = "<select name='filtro' id='filtro' style='width: 60%; text-align: center;'>";

		Laboratorio laboratorio, laboratorioAnterior = null;
		// Options computador
		for (var computador : computadores)
		{
			laboratorio = Laboratorio.getLaboratorioById(computador.getIdlaboratorio());
			
			if(laboratorio == null)
			{ continue; }
			
			if (!laboratorio.compareById(laboratorioAnterior))
			{
				select += "<optgroup label='" + laboratorio.getNome() + "'>";
			}

			select += "<option value='" + computador.getIdcomputador() + "'>" + computador.getNome() + "</option>";

			if (!laboratorio.compareById(laboratorioAnterior))
			{
				select += "</optgroup>";
			}
			
			laboratorioAnterior = laboratorio;
		}

		select += "</select>";
		
		return select;
	}
	
	private String getLaboratorioSelect()
	{
		ArrayList<Laboratorio> laboratorios = Laboratorio.getAllLaboratorios();

		if (laboratorios == null)
		{ return "<option>Laboratórios não encontrados!</option>"; }

		String select = "<select name='filtro' id='filtro' style='width: 60%; text-align: center;'>";

		for (var laboratorio : laboratorios)
		{
			select += "<option value='" + laboratorio.getIdlaboratorio() + "'>" + laboratorio.getNome() + "</option>";
		}

		select += "</select>";
		
		return select;
	}
	
	private String getDataFields()
	{
		return "<label for='dataInicial' class='bordered-text'>Data mínima: </label>"
			 + "<input type='date' name='dataInicial' id='dataInicial' required/>"
			 + "<br/>"
			 + "<label for='dataFinal' class='bordered-text'>Data máxima: </label>"
			 + "<input type='date' name='dataFinal' id='dataFinal' required/>";
	}
}
