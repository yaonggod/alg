import java.io.*;
import java.util.*;


public class Main {
    static int[] parent;
    static boolean[] cycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        int caseIdx = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;

            parent = new int[n + 1];
            cycle = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                cycle[i] = true;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                unite(a, b);
            }

            Set<Integer> canBeTree = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                if (cycle[find(parent[i])]) {
                    canBeTree.add(find(parent[i]));
                }
            }
            int count = canBeTree.size();

            String caseNo = "Case " + caseIdx + ": ";
            String result = "A forest of " + count + " trees.";
            if (count == 1) {
                result = "There is one tree.";
            } else if (count == 0) {
                result = "No trees.";
            }
            bw.write(caseNo + result + "\n");
            caseIdx++;
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int find(int a) {
        if (a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    static void unite(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) cycle[a] = false;
        if (a < b) {
            parent[b] = a;
            if (!cycle[b]) cycle[a] = false;
        }
        else {
            parent[a] = b;
            if (!cycle[a]) cycle[b] = false;
        }
    }

}