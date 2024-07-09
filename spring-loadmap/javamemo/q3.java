import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class q3 {
    public static void main(String[] args) {
        int ret = 0;

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 2; i <= n; i++) {
            ret += primeNo(i);
        }

        System.out.println(ret);
    }

    public static int primeNo(int num) {

        if(num == 2) {
            return num;
        }

        for(int i = 2; i <= Math.sqrt(num); i++) {

            if(num % i == 0) {
                return 0;
            }
        }

        return num;
    }
}
