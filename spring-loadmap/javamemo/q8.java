import java.util.Scanner;

public class q8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int r = scanner.nextInt();

        int ret = pNo(n, r);

        System.out.println(ret);
    }

    public static int pNo(int n, int r) {
        int temp = 1;
        for (int i = n-r+1; i <= n; i++) {
            temp *= i;
        }
        return temp;
    }
}
