import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] triangle = new int[n][n];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i; j++) {
                triangle[i - 1][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                // 제일 왼쪽거다
                if (j == 0) {
                    triangle[i][j] += triangle[i - 1][j];
                // 제일 오른쪽거다
                } else if (j == i) {
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else {
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (triangle[n - 1][i] > answer) answer = triangle[n - 1][i];
        }
        System.out.println(answer);
    }


}