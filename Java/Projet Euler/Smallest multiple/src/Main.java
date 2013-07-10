/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */

public class Main {

    public static void main(String[] args){
        boolean search = true;
        int number = 0;
        while(search){
            number++;
            search = false;
            for(int i = 1; i <= 20; i++){
                if(number % i != 0){
                    search = true;
                    break;
                }
            }
        }
        System.out.println(number);
    }
}
