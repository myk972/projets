package formulaires;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.ClientDao;
import dao.DaoException;
import entities.Client;

public class Login {
	
	private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String SESSION_CLIENTS = "clients";
    
    private Map<String, String> erreurs = new HashMap<String, String>();
    private ClientDao clientDao;
    
    public Login(ClientDao clientDao) {
        this.clientDao= clientDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public Client LoginClient(HttpServletRequest request) {
    	String email = this.getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = this.getValeurChamp(request, CHAMP_PASS);
        Client client = new Client();
        
        try {
			client = chercherCompte(email, motDePasse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(client != null){
        	HttpSession session = request.getSession();
        	Map<String, Client> clients = (HashMap<String, Client>) session.getAttribute(SESSION_CLIENTS);
        	
        	if(clients == null){
        		clients = new HashMap<String, Client>();
        	}
        	
        	clients.put(email, client);
        	session.setAttribute(SESSION_CLIENTS, clients);
        }
        return client;
    }
    
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    private String getValeurChamp(HttpServletRequest req, String nomChamp){
        String donnee = req.getParameter(nomChamp);
        return donnee.trim();
    }
    
    private Client chercherCompte(String email, String motDePasse) throws Exception {
    	Client client = clientDao.trouverClient(email);
    	
    	if(client == null){
    		setErreur(CHAMP_EMAIL, "compte inexistant");
    	}
    	
    	else if(!client.getMotDePasse().equals(ClientDao.encrypt(motDePasse))) {
    		setErreur(CHAMP_PASS, "mot de passe incorrect");
    	}
    	
    	return client;
    }

}
