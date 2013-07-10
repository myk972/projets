package servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClientDao;
import dao.DaoFactory;

import entities.Client;
import formulaires.Inscription;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CLIENT = "client";
    private static final String FORMULAIRE = "formulaire";
	private static final String VUE = "/WEB-INF/inscription.jsp";
	private static final String SESSION_USER = "sessionClient";
	private static final String HOME = "/WEB-INF/home.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		ClientDao clientDao = daoFactory.CreateClientDao();
		Inscription inscription = new Inscription(clientDao);
		Client client = inscription.inscrireUtilisateur(request);
		HttpSession session = request.getSession();
		
		if(client != null){
			session.setAttribute(SESSION_USER, client);
			request.setAttribute(FORMULAIRE, inscription);
	        request.setAttribute(CLIENT, client);
	        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
		else{
			this.getServletContext().getRequestDispatcher(HOME).forward(request, response);
		}
	}

}
