import java.util.ArrayList;
import java.util.List;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */

public class Main {

    public static void main(String[] arg){
        int limite = (int)Math.sqrt(600851475143L);
        List<Integer> listePremiers = new ArrayList<Integer>();
        listePremiers.add(2);
        List<Integer> listeFacteurs = new ArrayList<Integer>();
        long result = 600851475143L;

        for(int i=3; i<limite+1; i++){
            if(isPremier(i, listePremiers)){
                listePremiers.add(i);
            }
        }

        for(int i : listePremiers){
            while(result % i == 0){
                listeFacteurs.add(i);
                result = result / i;
            }
        }

         System.out.print(listeFacteurs.get(listeFacteurs.size()-1));
    }

    private static boolean isPremier(int number, List<Integer> list){
        boolean premier = true;

        for(int i : list){
            if(number % i == 0){
               premier = false;
                break;
            }
        }

        return premier;
    }
}
