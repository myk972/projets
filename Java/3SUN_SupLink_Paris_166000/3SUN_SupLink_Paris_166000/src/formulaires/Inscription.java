package formulaires;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.ClientDao;
import entities.Client;

public class Inscription {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_CONF = "confirmation";
    private static final String SESSION_CLIENTS = "clients";

    private Map<String, String> erreurs = new HashMap<String, String>();
    private ClientDao clientDao;

    public Inscription(ClientDao clientDao) {
        this.clientDao= clientDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Client inscrireUtilisateur(HttpServletRequest req) {
        String email = this.getValeurChamp(req, CHAMP_EMAIL);
        String motDePasse = this.getValeurChamp(req, CHAMP_PASS);
        String confirmation = this.getValeurChamp(req, CHAMP_CONF);

        Client client = new Client();

        try {
            this.validationEmail(email, client);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }

        client.setEmail(email);

        try {
            this.validationMotDePasse(motDePasse, confirmation, client);
        } catch (Exception e) {
            setErreur(CHAMP_PASS, e.getMessage());
        }

        try {
			client.setMotDePasse(motDePasse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if(erreurs.isEmpty()){
        	clientDao.createClient(client);
        	HttpSession session = req.getSession();
        	Map<String, Client> clients = (HashMap<String, Client>) session.getAttribute(SESSION_CLIENTS);
        	
        	if(clients == null){
        		clients = new HashMap<String, Client>();
        	}
        	
        	clients.put(email, client);
        	session.setAttribute(SESSION_CLIENTS, clients);
        }
        
        return client;
    }


    private void validationEmail(String email, Client client) throws Exception {
        if (email != null && email.trim().length() != 0) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("addresse invalide");
            }
            else if(clientDao.trouverClient(email) != null) {
        		throw new Exception("ce compte existe deja");
            }
            client.setEmail(email);
        } else if(email == null || email.trim().length() == 0){
            throw new Exception("veuillez entrer une addresse valide");
        }
    }

    private void validationMotDePasse(String motDePasse, String confirmation, Client client) throws Exception {
        if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
            if (!motDePasse.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont\n" +
                        "différents, merci de les saisir à nouveau.");
            } else if (motDePasse.trim().length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
            client.setMotDePasse(motDePasse);
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    private String getValeurChamp(HttpServletRequest req, String nomChamp){
        String donnee = req.getParameter(nomChamp);
        return donnee.trim();
    }
}

