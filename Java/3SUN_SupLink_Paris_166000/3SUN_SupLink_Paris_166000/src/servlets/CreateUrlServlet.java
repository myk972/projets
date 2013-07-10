package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.ShortUmlDao;
import entities.Client;
import entities.ShortUml;
import formulaires.UmlCreation;

/**
 * Servlet implementation class CreateUrlServlet
 */
@WebServlet("/home")
public class CreateUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/home.jsp";
	private static final String SESSION_USER = "sessionClient";
	private static final String FORM = "form";
	private static final String NEW_UML = "newUml";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUrlServlet() {
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
		ShortUmlDao umlDao = daoFactory.CreateShortUmlDao();
		HttpSession session = request.getSession();
		Client client = (Client) session.getAttribute(SESSION_USER);
		UmlCreation umlCreation = new UmlCreation(client, request, umlDao);
		ShortUml newUml = umlCreation.createUml();
		request.setAttribute(NEW_UML, newUml);
		request.setAttribute(FORM, umlCreation);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
