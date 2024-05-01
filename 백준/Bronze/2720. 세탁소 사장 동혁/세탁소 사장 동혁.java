import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < t; tc++) {
            int c = Integer.parseInt(br.readLine());
            int[] coins = new int[4];

            coins[0] = c / 25;
            c = c % 25;

            coins[1] = c / 10;
            c = c % 10;

            coins[2] = c / 5;
            c = c % 5;

            coins[3] = c;
            for (int coin : coins) {
                sb.append(coin).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}