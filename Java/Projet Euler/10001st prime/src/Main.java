import java.util.ArrayList;
import java.util.List;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10 001st prime number?
 */

public class Main {

    public static void main(String[] args){
        List<Integer> listePremiers = new ArrayList<Integer>();
        listePremiers.add(2);
        int number = 2;

        while(listePremiers.size() < 10001){
            number++;
            if(isPremier(number, listePremiers)){
                listePremiers.add(number);
            }
        }

        System.out.println(listePremiers.get(10000));
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
