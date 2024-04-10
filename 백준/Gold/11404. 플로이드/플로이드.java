import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] route = new int[n][n];

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            if (route[a][b] == 0) {
                route[a][b] = c;
            } else {
                if (route[a][b] > c) {
                    route[a][b] = c;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j && i != k && j != k) {
                        if (route[i][k] != 0 && route[k][j] != 0) {
                            if (route[i][j] == 0 || route[i][j] > route[i][k] + route[k][j]) {
                                route[i][j] = route[i][k] + route[k][j];
                            }
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(route[i][j]);
                if (j != n - 1) sb.append(" ");
            }
            if (i != n - 1) sb.append("\n");
        }
        System.out.println(sb);
    }
}