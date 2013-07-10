import java.util.ArrayList;
import java.util.List;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 */

public class Main {

    public static void main(String[] args){
        List<Long> listePremiers = new ArrayList<>();
        listePremiers.add(2L);
        long sum = 2L;

        for(long i = 3L; i < 2_000_000L; i++){
            if(isPremier(i, listePremiers)){
                listePremiers.add(i);
                sum += i;
            }
        }

        System.out.println(sum);
    }

    private static boolean isPremier(long number, List<Long> list){
        boolean premier = true;

        for(long i : list){
            if(number % i == 0){
                premier = false;
                break;
            }
        }

        return premier;
    }
}
