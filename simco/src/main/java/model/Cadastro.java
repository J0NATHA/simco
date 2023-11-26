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
import entity.Usuario;

/**
 * Servlet implementation class CadastroComputador
 */
@WebServlet({"/CadastroComputador", "/CadastroUsuario", "/CadastroProblema"})
public class Cadastro extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cadastro()
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
		String requestPath = request.getServletPath();
		String cadastroType = getNameByPath(requestPath);
		String pag;

		pag = "<html><head><title>Cadastro de " + cadastroType + "</title>";
		pag +="<link rel='stylesheet' href='styles.css'/>"
			+ "</head>";

		pag +="<body>"
			+ "<div class='container'>"
			+ "		<h1 class='bordered-text'>"
			+ "			Cadastro de " + cadastroType
			+ "		</h1>"
			+ "</div>"

			+ "<div class='container' style='text-align: end; height: 60vh; margin: 3vh 25vw 3vh; flex-direction: column'>"

			+ " 	<form action='InsereCadastro" + cadastroType + "' method='post' class='bordered-text'>"
			+ " 		<input type='hidden' name='tipo' value='" + cadastroType + "'/>"
			+ 			formFieldsOf(cadastroType)
			+ " 		<input type='submit' value='CADASTRAR' class='bordered-text' style='width: 94%;'/> <br/>"
			+ " 	</form>"
			+ "		<a href='cadastrar.html' class='box-title' style='background-color: #999999;"
			+ "			margin-left: 30vw; margin-right: 30vw; width: 40vw;'>"
			+ "			<p style='margin: 0;'>Voltar</p>"
			+ "		</a> "
			+ "</div>"

			+ "</body>"
			+ "</html>";

		response.getWriter().print(pag);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String getNameByPath(String servletPath)
	{
		switch (servletPath)
		{
			case "/CadastroComputador":
			{
				return "Computador";
			}

			case "/CadastroUsuario":
			{
				return "Usuario";
			}

			case "/CadastroProblema":
			{
				return "Problema";
			}

			default:
			{
				return "ERRO: NÃO ENCONTRADO";
			}
		}
	}

	private String formFieldsOf(String cadastroType)
	{
		switch(cadastroType)
		{
			case "Computador":
			{
				return computadorFormFields();
			}

			case "Usuario":
			{
				return usuarioFormFields();
			}

			case "Problema":
			{
				return problemaFormFields();
			}

			default:
			{
				return "ERRO: TIPO DE CADASTRO INDETERMINADO";
			}
		}
	}

	private String computadorFormFields()
	{
		String fields = "<label for='laboratorio'>Laboratório: </label>"
					  + "<select name='laboratorio' id='laboratorio' style='width: 61%;'>";

		ArrayList<Laboratorio> laboratorios;

		try
		{ laboratorios = Laboratorio.getAllLaboratorios(); }
		
		catch (Exception e)
		{ return "ERRO AO PESQUISAR LABORATÓRIOS";}

		for (var laboratorio : laboratorios)
		{
			fields += "<option value='" + laboratorio.getIdlaboratorio() + "'>" + laboratorio.getNome();
			fields += "</option>";
		}

		fields += "</select> <br/>"
			   +  "<label for='nome'>Nome: M-</label>"
			   +  "<input type='text' name='nome' id='nome' required/> <br/>";
		
		ArrayList<Software> softwares;
		
		try
		{ softwares = Software.getAllSoftware(); }
		
		catch(Exception e)
		{ return "ERRO AO PESQUISAR SOFTWARES"; }
		
		for(var software : softwares)
		{
			String label = "software" + software.getIdsoftware();
			
			fields += "<label for='" + label + "' style='font-size: 15px'>" + software.getNome() + "</label>" 
					+ "<input type='checkbox'"
					+ " name='" + label + "' id='" + label + "'"
					+ " value='" + software.getIdsoftware() + "'/><br/>";
		}
	
		return fields;
	}

	private String usuarioFormFields()
	{
		return "<label for='nome'>Nome: </label><input type='text' name='nome' id='nome' required/> <br/>"
			 + "<label for='email'>Email: </label><input type='text' name='email' id='email' required/> <br/>";
	}

	private String problemaFormFields()
	{
		String fields;
		
		ArrayList<Usuario> usuarios = Usuario.getAllUsuarios();
		
		// Select usuario
		fields = "<label for='usuario'>"
			   + "Usuário: </label>"
			   + "<select name='usuario' id='usuario' style='width: 18vw;'>";
		
		for(var usuario : usuarios)
		{
			fields += "<option value='" + usuario.getIdusuario() + "'>" + usuario.getNome()
				    + "</option>";
		}
		
		fields += "</select> <br/>";
		
		ArrayList<Computador> computadores = Computador.getAllComputadores();
		
		// Select computador
		fields += "<label for='computador'>"
			   + "Computador: </label>"
		       + "<select name='computador' id='computador' style='width: 18vw;'>";
		
		Laboratorio laboratorio, laboratorioAnterior = null;
		
		// Options computador
		for(var computador : computadores)
		{	
			laboratorio = Laboratorio.getLaboratorioById(computador.getIdlaboratorio());
			
			if(laboratorio == null)
			{ continue; }
			
			if(!laboratorio.compareById(laboratorioAnterior))
			{ fields += "<optgroup label='" + laboratorio.getNome() + "'>"; }
			
			fields += "<option value='" + computador.getIdcomputador() + "'>" + computador.getNome()
					+ "</option>";
			
			if(!laboratorio.compareById(laboratorioAnterior))
			{ fields += "</optgroup>"; }
			
			laboratorioAnterior = laboratorio;
			
		}

		// Select tipo
		fields += "</select> <br/>"
				+ "<label for='tipoProblema'>Tipo do problema: </label>"
				+ "<select name='tipoProblema' id='tipoProblema' style='width: 18vw;'>"
				+ " 	<option value='software'>SOFTWARE</option>"
				+ " 	<option value='hardware'>HARDWARE</option>"
				+ "</select> <br/>";

		// Texto descrição
		fields += "<label for='descricao' style='vertical-align: top; margin-top: 10px;'>"
				+ "		Descrição do problema: "
				+ "</label>"
				+ "<textarea name='descricao' id='descricao' rows='7' cols='30' style='resize: none; margin: 5px'"
				+ "maxlength='300' placeholder='Descreva o problema encontrado...' required></textarea> <br/>";

		return fields;
	}
}
