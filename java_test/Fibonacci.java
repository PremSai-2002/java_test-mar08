
import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {

        int firstTerm = 0;
        int secondTerm = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the number :");
        int num = sc.nextInt();
        System.out.println("Fibonacci Series till " + num + " terms:");

        for (int i = 1; i <= num; ++i) {
            System.out.print(firstTerm + ", ");

            int nextTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = nextTerm;
        }
    }
}
