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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            // 어디서부터 왔는지
            int[] parent = new int[10000];
            // 어떻게 왔는지 
            String[] visited = new String[10000];

            // 이번 tc의 커맨드 모음 
            StringBuilder sub = new StringBuilder();

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(A);
            while (!queue.isEmpty()) {
                int now = queue.poll();

                int toD = now * 2 > 9999 ? now * 2 % 10000 : now * 2;
                if (toD == B) {
                    parent[toD] = now;
                    visited[toD] = "D";
                    break;
                }
                if (visited[toD] == null) {
                    parent[toD] = now;
                    visited[toD] = "D";
                    queue.offer(toD);
                }

                int toS = now - 1 == -1 ? 9999 : now - 1;
                if (toS == B) {
                    parent[toS] = now;
                    visited[toS] = "S";
                    break;
                }
                if (visited[toS] == null) {
                    parent[toS] = now;
                    visited[toS] = "S";
                    queue.offer(toS);
                }

                int toL = now % 1000 * 10 + now / 1000;
                if (toL == B) {
                    parent[toL] = now;
                    visited[toL] = "L";
                    break;
                }
                if (visited[toL] == null) {
                    parent[toL] = now;
                    visited[toL] = "L";
                    queue.offer(toL);
                }

                int toR = now / 10 + now % 10 * 1000;
                if (toR == B) {
                    parent[toR] = now;
                    visited[toR] = "R";
                    break;
                }
                if (visited[toR] == null) {
                    parent[toR] = now;
                    visited[toR] = "R";
                    queue.offer(toR);
                }
            }
            // 역추적하기 
            int start = B;
            while (start != A) {
                String com = visited[start];
                sub.append(com);
                start = parent[start];
            }
            sb.append(sub.reverse()).append("\n");
        }
        System.out.println(sb);
    }
}