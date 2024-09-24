import java.io.*;
import java.util.*;

public class Main {
    static int[] good;
    static List<Integer>[] buha;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 직원
        n = Integer.parseInt(st.nextToken());
        // 칭찬
        m = Integer.parseInt(st.nextToken());

        good = new int[n + 1];
        buha = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            buha[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int sangsa = Integer.parseInt(st.nextToken());
            // 내 후배
            if (sangsa != -1) {
                buha[sangsa].add(i);
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());
            good[from] += point;
        }

        dfs(1);

        for (int i = 1; i <= n; i++) {
            System.out.print(good[i] + " ");
        }
    }

    static void dfs(int parent) {

        for (int hubae : buha[parent]) {
            if (good[parent] > 0) {
                good[hubae] += good[parent];
            }
            dfs(hubae);
        }
    }
}