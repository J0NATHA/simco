package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Computador;
import entity.Problema;
import entity.Usuario;

/**
 * Servlet implementation class ProblemaGeral
 */
@WebServlet("/ProblemaGeral")
public class ProblemaGeral extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProblemaGeral()
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
		
		String pag;
		
		pag = "<html><head><title>Relatórios gerais de problema</title>"
		    + "	<link rel='stylesheet' href='styles.css'/>"
			+ "</head>"
		
			+ "<body>"
			+ "<div class='container'>"
			+ "		<h1 class='bordered-text'>"
			+ "			Todos os problemas";
				
		if(!tipo.equals("todos"))
		{ pag += " de " + tipo; }
				
		pag +="</h1>"
			+ "</div>"
			+ "<div class='list'>"
			+ "	<a href='relatorios.html' style='background-color: #999999;' class='box-title'> "
			+ "		Voltar"
			+ "	</a>"
			
			+ problemasList(getProblemas(tipo))
				
			+ "	<a href='relatorios.html' style='background-color: #999999;' class='box-title'> "
			+ "		Voltar"
			+ "	</a>"
			
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
	
	private ArrayList<Problema> getProblemas(String tipoRelatorio)
	{
		switch(tipoRelatorio)
		{
			case "todos":
			{
				return Problema.getAllProblemas();
			}
			
			case "hardware":
			case "software":
			{
				return Problema.getProblemaByTipo(tipoRelatorio);
			}
			
			default:
			{
				return null;
			}
		}
	}
	
	public static String problemasList(ArrayList<Problema> problemas)
	{
		if(problemas == null)
		{ return "<h1 class='bordered-text' style='text-align: center;'>Nenhum problema encontrado!</h1>"; }
		
		String value = "";
		
		for(var problema : problemas)
		{
			Usuario usuario = usuarioById(problema.getIdusuario());
			Computador computador = computadorById(problema.getIdcomputador());
			String data = problema.getData().toString();
			
			value += "<p class='box-title' style='word-wrap: break-word;'> Usuário: " + usuario.getNome() + "<br/>" 
			      + "Máquina: " + computador.getNome() + "<br/>"	
				  + "Tipo: " + problema.getTipo() + "<br/>"
			      + "Descrição: " + problema.getDescricao() + "<br/>"
			      + "Data: " + dateFormat(data) + "</p>";
		}
		
		return value;
	}
	
	public static String dateFormat(String date)
	{	
		return date = date.substring(8) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
	}
	
	private static Usuario usuarioById(int id)
	{
		Usuario usuario = Usuario.getUsuarioById(id);
		
		if(usuario == null)
		{ usuario = new Usuario("Não encontrado"); }
		
		return usuario;
	}
	
	private static Computador computadorById(int id)
	{
		Computador computador = Computador.getComputadorById(id);
		
		if(computador == null)
		{ computador = new Computador("Não encontrado"); }
		
		return computador;
	}
}
