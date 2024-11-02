import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // x번만에 갈 수 있어요 == x + 2번만에 == x + 4번만에 ...

        if (n == k) {
            System.out.println(0);
            return;
        }

        int away = 1;
        int ans = -1;
        int[][] visited = new int[500001][2];
        for (int i = 0; i <= 500000; i++) {
            Arrays.fill(visited[i], -1);
        }
        visited[n][0] = 0;
        Set<Integer> set = new HashSet<>();
        set.add(n);
        while (true) {
            // 동생 ㄱㄱ
            k += away;
            if (k > 500000) break;

            if (visited[k][away % 2] != -1) {
                ans = away;
                break;
            }

            Set<Integer> newSet = new HashSet<>();
            for (int s : set) {
                if (s + 1 <= 500000) {
                    // 지금 동생이랑 만남
                    if (s + 1 == k) {
                        ans = away;
                        break;
                    } else {
                        if (visited[s + 1][away % 2] == -1) {
                            visited[s + 1][away % 2] = away;
                            newSet.add(s + 1);
                        }
                    }
                }

                if (s - 1 >= 0) {
                    if (s - 1 == k) {
                        ans = away;
                        break;
                    } else {
                        if (visited[s - 1][away % 2] == -1) {
                            visited[s - 1][away % 2] = away;
                            newSet.add(s - 1);
                        }
                    }
                }

                if (s != 0 && s * 2 <= 500000) {
                    if (s * 2 == k) {
                        ans = away;
                        break;
                    } else {
                        if (visited[s * 2][away % 2] == -1) {
                            visited[s * 2][away % 2] = away;
                            newSet.add(s * 2);
                        }
                    }
                }
            }
            if (ans != -1) break;
            set = newSet;
            away++;
        }

        System.out.println(ans);
    }
}