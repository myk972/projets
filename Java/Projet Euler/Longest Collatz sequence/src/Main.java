/**
 * The following iterative sequence is defined for the set of positive integers:

 n  n/2 (n is even)
 n  3n + 1 (n is odd)

 Using the rule above and starting with 13, we generate the following sequence:

 13  40  20  10  5  16  8  4  2  1
 It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

 Which starting number, under one million, produces the longest chain?

 NOTE: Once the chain starts the terms are allowed to go above one million.
 */

public class Main {
    public static void main(String[] args){
        long first = 2;
        long temp;
        long maxLength = 0;

        for (long i=2; i<1_000_000; i++){
            temp = i;
            long actualTerm = i;
            long lenght = 0;
            while (findNextTerm(actualTerm) != 1){
                actualTerm = findNextTerm(actualTerm);
                lenght++;
            }
            if(lenght > maxLength){
                maxLength = lenght;
                first = temp;
            }
        }
        System.out.println(first);
    }

    private static long findNextTerm(long term){
        long nextTerm;
        if(term % 2 == 0)
            nextTerm = term/2;
        else
            nextTerm = (3 * term) + 1;
        return nextTerm;
    }
}
