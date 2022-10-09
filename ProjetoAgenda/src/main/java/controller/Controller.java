package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = {"/Controller", "/main", "/insert"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		if(action.equals("/main")) {
		    contatos(request, response);
		}
		else if(action.equals("/insert")) {
		    novoContato(request, response);
		}else {
		    response.sendRedirect("index.jsp");
		}
	}

	//Listagem de contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Objeto JavaBeans
	    ArrayList<JavaBeans> listaContatos = dao.buscarContatos();
	    
	    //Encaminhando a lista ao documento jsp
	    request.setAttribute("contatos", listaContatos);
	    RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
	    rd.forward(request, response);
	}
	
	//Novo contatos
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //Instanciando o objeto contato e setando seus atributos
        JavaBeans contato = new JavaBeans();
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        
        //Passando o objeto contato por parametro para o dao.inserirContato
        dao.inserirContato(contato);
        
        //Redirecionando o usuário com o request à /main
        response.sendRedirect("main");
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
