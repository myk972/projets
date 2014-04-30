import java.util.ArrayList;
import java.util.List;

/**
 * Euler discovered the remarkable quadratic formula:

 n² + n + 41

 It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.

 The incredible formula  n² − 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79. The product of the coefficients, −79 and 1601, is −126479.

 Considering quadratics of the form:

 n² + an + b, where |a| < 1000 and |b| < 1000

 where |n| is the modulus/absolute value of n
 e.g. |11| = 11 and |−4| = 4
 Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.
 */
public class Main {

    public static void main(String[] args){
        List<Integer> listPremiers = getPremiers();
        int a = 1;
        int b = 1;

        for(int n = 0; n < )
    }

    private static List<Integer> getPremiers() {
        List<Integer> listePremiers = new ArrayList<>();
        listePremiers.add(2);
        int number = 2;

        while(listePremiers.size() < 1000){
            number++;
            if(isPremier(number, listePremiers)){
                listePremiers.add(number);
            }
        }
        return listePremiers;
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
