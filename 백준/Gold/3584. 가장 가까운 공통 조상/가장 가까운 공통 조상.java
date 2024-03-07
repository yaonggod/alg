import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static StringTokenizer st;
    static int[] ancestor;
    static int n;

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            // 노드 개수
            n = Integer.parseInt(br.readLine());
            // 내 바로 위 조상
            ancestor = new int[n + 1];
            // 간선
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                ancestor[b] = a;
            }
            // 테스트할 노드
            st = new StringTokenizer(br.readLine());
            int testA = Integer.parseInt(st.nextToken());
            int testB = Integer.parseInt(st.nextToken());

            sb.append(nca(testA, testB));

            if (tc != t - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static int nca(int a, int b) {
        Set<Integer> aSet = new HashSet<>();
        aSet.add(a);
        aSet.add(b);
        while (true) {
            a = ancestor[a];
            b = ancestor[b];
            if (a == b && a == 0) break;
            if (a == b) return a;
            if (a != 0) {
                if (aSet.contains(a)) return a;
                aSet.add(a);
            }
            if (b != 0) {
                if (aSet.contains(b)) return b;
                aSet.add(b);
            }
        }
        return -1;
    }

}