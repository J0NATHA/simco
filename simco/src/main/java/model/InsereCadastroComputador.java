package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Computador;
import entity.Software;

/**
 * Servlet implementation class InsereCadastroComputador
 */
@WebServlet("/InsereCadastroComputador")
public class InsereCadastroComputador extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsereCadastroComputador()
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
		String idlaboratorio = request.getParameter("laboratorio");
		String nome = request.getParameter("nome");
		
		ArrayList<Software> selectedSoftwares = getSelectedSoftwares(request);

		String pag; 
		String msg = "ERRO: LABORATÓRIO NÃO EXISTE";
		
		if(idlaboratorio != null)
		{
			if(selectedSoftwares == null)
			{ msg = insert(new Computador(Integer.parseInt(idlaboratorio), nome)); }
			
			else
			{ msg = insertWithSoftware(new Computador(Integer.parseInt(idlaboratorio), nome), selectedSoftwares); }
		}
		
		

		pag = "<html><head><title>Cadastro de Computador</title>"
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

	private String insert(Computador computador)
	{
		if(computador.insert(computador))
		{ return "COMPUTADOR CADASTRADO COM SUCESSO"; }

		return "ERRO: NÃO FOI POSSÍVEL CADASTRAR O COMPUTADOR";
	}
	
	private String insertWithSoftware(Computador computador, ArrayList<Software> softwares)
	{
		if(computador.insert(computador, softwares))
		{ return "COMPUTADOR CADASTRADO COM SUCESSO"; }

		return "ERRO: NÃO FOI POSSÍVEL CADASTRAR O COMPUTADOR";
	}

	private ArrayList<Software> getSelectedSoftwares(HttpServletRequest request) 
	{
		String label = "software";
		ArrayList<Software> selectedSoftwares = new ArrayList<Software>();
		
		for(var software : Software.getAllSoftware())
		{
			String requestBox = request.getParameter(label + software.getIdsoftware());
			
			if(requestBox != null)
			{ selectedSoftwares.add(software); }
		}
		
		return selectedSoftwares;
	}
}
