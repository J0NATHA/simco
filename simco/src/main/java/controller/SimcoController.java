package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SimcoController
 */
@WebFilter("/*")
public class SimcoController extends HttpFilter implements Filter
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public SimcoController()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		String requestPath = ((HttpServletRequest) request).getServletPath();
		
		ArrayList<String> validPaths = new ArrayList<String>(
			Arrays.asList(
				"/index.html", "/styles.css", "/CadastroComputador", "/CadastroProblema", "/CadastroUsuario",
				"/cadastrar.html", "/relatorios.html", "/InsereCadastroComputador", "/InsereCadastroUsuario",
				"/RelatorioSoftware", "/ProblemaGeral", "/relatoriosProblemas.html", "/ProblemaEspecifico",
				"/InsereCadastroProblema", "/TodosHardwares", "/TodosSoftwares", "/RelatorioHardware",
				"/RelatorioProblemaEspecifico")
		);
		
		if(validPaths.contains(requestPath))
		{
			chain.doFilter(request, response);			
		}
		
		else if(requestPath.equals("/RelatorioProblema"))
		{
			ArrayList<String> geral = new ArrayList<String>(
				Arrays.asList("todos", "hardware", "software")
			);
			
			ArrayList<String> especifico = new ArrayList<String>(
				Arrays.asList("usuario", "computador", "laboratorio", "datas")
			);

			String tipo = request.getParameter("tipo");
			request.setAttribute("tipo", tipo);
			
			String servletDestino = "index.html";
			
			if(geral.contains(tipo))
			{ servletDestino = "ProblemaGeral"; }
			
			if(especifico.contains(tipo))
			{ servletDestino = "ProblemaEspecifico"; }
			
			((HttpServletResponse) response).sendRedirect(servletDestino 
					+ "?tipo=" + URLEncoder.encode(tipo, "UTF-8"));
		}
		
		else
		{
			((HttpServletResponse) response).sendRedirect("index.html");			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}
}
