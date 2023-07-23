import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println(0);
        } else if (n == 1) {
            System.out.println(1);
        } else {
            long[] pivo = new long[n + 1];
            pivo[0] = 0;
            pivo[1] = 1;
            for (int i = 2; i <= n; i++) {
                pivo[i] = (pivo[i - 1] + pivo[i - 2]) % 1000000007;
            }
            System.out.println(pivo[n]);
        }
    }
}
