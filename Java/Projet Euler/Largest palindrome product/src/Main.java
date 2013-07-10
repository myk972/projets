/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */

public class Main {

    public static void main(String arg[]){
        boolean isDone = false;
        for(int i = 999; i > 99; i--){
            isDone = false;
            for(int j = 999; j > 99; j--){
                if(isPalindrome(i * j)){
                    System.out.println(i + " * " + j + " = " + i*j );
                    isDone = true;
                    break;
                }
                if(isDone)
                    break;
            }
        }
    }

    private static boolean isPalindrome(int x){
        boolean bool = false;
        String s = Integer.toString(x);
        StringBuilder builder = new StringBuilder(s);
        String reverse = builder.reverse().toString();
        if(s.equals(reverse)){
           bool = true;
        }
        return bool;
    }
}
