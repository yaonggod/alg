import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        // taller[a][b] = a가 b보다 크다
        boolean[][] taller = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            taller[a][b] = true;
        }
        // 플로이드워셜
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    // i가 k보다 크고 k가 j보다 크면 i가 j보다 크다
                    if (i != j && taller[i][k] && taller[k][j]) {
                        taller[i][j] = true;
                    }
                }
            }
        }
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            int tall = 0;
            int small = 0;
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    // i보다 작은 친구들 - 내가 얘보단 크다
                    if (taller[i][j]) tall++;
                    // i보다 큰 친구들
                    if (taller[j][i]) small++;
                    // 관계가 명확한 친구들이 정확히 n - 1명이다
                    if (tall + small == n - 1) cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}