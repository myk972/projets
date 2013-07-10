package formulaires;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import dao.ShortUmlDao;
import entities.Client;
import entities.ShortUml;

public class UmlCreation {

	private ShortUmlDao shortUmlDao;
	private Client client;
	private HttpServletRequest request;
	
	private  static String INPUT = "nameUrl";
	private static String NAME_URL = "name";
	private static String DEBUT_URL="www.SupLink/";
	
	public UmlCreation(Client client, HttpServletRequest request, ShortUmlDao shortUmlDao){
		this.client = client;
		this.request = request;
		this.shortUmlDao = shortUmlDao;
	}
	
	public ShortUml createUml(){
		ShortUml newUml = new ShortUml();
		String initialUml = request.getParameter(INPUT);
		String shortUml = UUID.randomUUID().toString();
		shortUml = shortUml.substring(0, 7);
		shortUml = DEBUT_URL + shortUml;
		String name = request.getParameter(NAME_URL);
		newUml.setInitialUml(initialUml);
		newUml.setIsEnable(true);
		newUml.setNom(name);
		newUml.setNumberOfUse(0);
		newUml.setShortUml(shortUml);
		newUml.setClient(client);
		shortUmlDao.addShortUml(newUml);
		return newUml;
	}
}
