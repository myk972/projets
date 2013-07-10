package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClientDao;
import dao.DaoFactory;
import entities.Client;
import entities.ShortUml;
import formulaires.Inscription;
import formulaires.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CLIENT = "client";
    private static final String FORMULAIRE = "formulaire";
	private static final String VUE = "/WEB-INF/login.jsp";
	private static final String HOME = "/WEB-INF/home.jsp";
	private static final String SESSION_USER = "sessionClient";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		HttpSession session = request.getSession();
		session.invalidate();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		ClientDao clientDao = daoFactory.CreateClientDao();
		Login login = new Login(clientDao);
		Client client = login.LoginClient(request);
		HttpSession session = request.getSession();
		
		if(client != null){
			session.setAttribute(SESSION_USER, client);
			this.getServletContext().getRequestDispatcher(HOME).forward(request, response);
		}
		else{
		request.setAttribute(FORMULAIRE, login);
        request.setAttribute(CLIENT, client);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

}
