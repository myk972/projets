import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: marc
 * Date: 05/07/13
 * Time: 05:05
 * To change this template use File | Settings | File Templates.
 */
public class Noeud
{
    int numero;
    String valeurBinaire;
    String Texte;
    Noeud filsGauche;
    Noeud filsDroit;
    boolean Feuille;
    public static Noeud Racine;
    public static ArrayList<Noeud> Liste=new ArrayList<Noeud>();
    static int numeroCourant=1;

    Noeud(String txt)
    {
        this.numero=numeroCourant;
        this.Texte=txt;
        numeroCourant++;
    }
    public static Noeud getRacine()
    {
        return Noeud.Racine;
    }
    public static void setRacine(Noeud r)
    {
        Noeud.Racine=r;
    }
    public void addFilsG(String txt)
    {
        Noeud n=new Noeud(txt);
        Liste.add(n);
        this.filsGauche=n;
    }
    // methode qui ajoute un fils sur le coté droit du noeud
    public void addFilsD(String txt)
    {
        Noeud n=new Noeud(txt);
        Liste.add(n);
        this.filsDroit=n;
    }
}