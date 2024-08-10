import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        // 내 앞에 와야 할 사람
        int[] before = new int[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count - 1; j++) {
                int next = Integer.parseInt(st.nextToken());
                // 내 뒤에 얘가 와야하고
                graph[first].add(next);
                // 내 앞에 몇 명이 있어야함
                before[next]++;
                first = next;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            // 내 앞에 아무도 안 와도 돼
            if (before[i] == 0) {
                queue.offer(i);
                ans.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer from = queue.poll();
            for (Integer to : graph[from]) {
                before[to]--;
                if (before[to] == 0) {
                    queue.offer(to);
                    ans.add(to);
                }
            }
        }

        if (ans.size() != n) {
            System.out.println(0);
        } else {
            for (Integer a : ans) {
                System.out.println(a);
            }
        }
    }

}