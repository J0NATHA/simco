package model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Problema;

/**
 * Servlet implementation class InsereCadastroProblema
 */
@WebServlet("/InsereCadastroProblema")
public class InsereCadastroProblema extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsereCadastroProblema()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String idcomputador = request.getParameter("computador");
		String tipo = request.getParameter("tipoProblema");
		String descricao = request.getParameter("descricao");
		String idusuario = request.getParameter("usuario");
		
		String msg = "HOUVE UM ERRO AO REGISTRAR O PROBLEMA";
		String pag;
		
		if(idcomputador != null && idusuario != null)
		{ 
			msg = insert(new Problema(
							Integer.parseInt(idcomputador), 
							tipo, 
							descricao, 
							Integer.parseInt(idusuario))
						); 
		}
		
		
		pag = "<html><head><title>Cadastro de Problema</title>"
			+ "<link rel='stylesheet' href='styles.css'></head>"
			+ "<body> <script> alert('" + msg + "'); </script> "
			+ "		<div class='container'>"
			+ "			<h1 class='bordered-text'>"
			+ "				Cadastro concluído"
			+ " 		</h1>"
			+ "  	</div>"
			+ " 	<div class='container2'>"
			+ " 		<a href='index.html' style='font-size: 60px; width: 90%'>"
			+ " 			VOLTAR"
			+ "  		</a>"
			+ " 	</div>"
			+ "</body> </html>";

		response.getWriter().print(pag);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String insert(Problema problema)
	{
		if(problema.insert(problema))
		{
			return "PROBLEMA REGISTRADO COM SUCESSO";
		}
		
		return "HOUVE UM ERRO AO REGISTRAR O PROBLEMA";
	}
}
