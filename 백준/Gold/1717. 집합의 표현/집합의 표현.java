import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (c == 1) {
                int x = findParent(a);
                int y = findParent(b);
                if (x != y) {
                    System.out.println("NO");
                } else {
                    System.out.println("YES");
                }
            } else {
                int x = findParent(a);
                int y = findParent(b);
                if (x != y) {
                    unite(x, y);
                }
            }
        }
    }

    static int findParent(int a) {
        if (a == parent[a]) return a;
        return parent[a] = findParent(parent[a]);
    }

    static void unite(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}