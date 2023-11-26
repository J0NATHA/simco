package model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Usuario;

/**
 * Servlet implementation class InsereCadastro
 */
@WebServlet("/InsereCadastroUsuario")
public class InsereCadastroUsuario extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsereCadastroUsuario()
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
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String pag, msg;

		Usuario usuario = new Usuario(nome, email);

		msg = insert(usuario);

		pag = "<html><head><title>Cadastro de Usuario</title>"
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

	private String insert(Usuario usuario)
	{
		if(usuario.getNome().isBlank() || usuario.getEmail().isBlank())
		{ return "ERRO: NÃO FOI POSSÍVEL CADASTRAR USUÁRIO"; }

		if(usuario.insert(usuario))
		{ return "USUARIO CADASTRADO COM SUCESSO"; }

		return "ERRO: NÃO FOI POSSÍVEL CADASTRAR USUÁRIO";
	}

}
