import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] result = new int[n];
        for (int i = 0; i < n / 2; i++) {
            result[2 * i] = 1;
            result[2 * i + 1] = 2;
        }
        if (n % 2 != 0) {
          result[n - 1] = 3;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i]);
            sb.append(" ");
        }
        System.out.println(sb);
    }
}