import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[][] matrix;
    static int[][] dp;

    static int INF = 16000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 저는 지금 i에 있는데,
        // 지금까지 지나온 경로가 이렇게 돼요,
        // 그리고 지금부터 다 순환할 때까지 dp[i][j]의 비용이 들거에요
        dp = new int[N][(1 << N) - 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 0번 도시부터 시작할거에요, 경로는 0번 도시를 지나왔으니 (1 << 0)
        System.out.println(tsp(0, 1));
    }

    static int tsp(int now, int route) {
        // 모든 도시를 다 찍음
        if (route == (1 << N) - 1) {
            // 되돌아가는 길이 있나요
            // 없으면 INF값을 이용해서 최신을 갱신 못하게 됨
            if (matrix[now][0] == 0) return INF;
            return matrix[now][0];
        }

        // 이미 해당 경로를 개발했다면 그 정보를 가져다가 쓸래
        if (dp[now][route] != -1) return dp[now][route];

        dp[now][route] = INF;

        // 다음 도시
        for (int i = 0; i < N; i++) {
            // 안 가본 도시이고, 경로가 있다
            if ((route & (1 << i)) == 0 && matrix[now][i] != 0) {
                // 후속 경로를 dp[0][1]에 계속 적립해서 갱신해나가는 방식
                // 저는 0에서 다음으로 갈건데
                // i에서 그 다음으로 갔던 최소 비용 정보가 있어요
                // 그럼 그 비용에다가 지금에서 i로 가는 비용을 더해서 비교할래요
                dp[now][route] = Math.min(tsp(i, route | (1 << i)) + matrix[now][i], dp[now][route]);
            }
        }

        return dp[now][route];
    }
}