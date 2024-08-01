import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static boolean exists = false;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> friends = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            friends.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends.get(a).add(b);
            friends.get(b).add(a);
        }

        for (int i = 0; i < n; i++) {
            visited[i] = true;
            connect(i, 0);
            visited[i] = false;
        }

        System.out.println(exists ? 1 : 0);
    }

    static void connect(int me, int count) {
        if (exists) return;

        if (count == 4) {
            exists = true;
            return;
        }

        for (Integer you : friends.get(me)) {
            if (!visited[you]) {
                visited[you] = true;
                connect(you, count + 1);
                visited[you] = false;
            }
        }
    }

}