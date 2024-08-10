import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] people = new String[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            people[i] = st.nextToken();
        }
        Arrays.sort(people);
        Map<String, Integer> idx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idx.put(people[i], i);
        }

        // 나의 조상님은 몇명인지
        int[] ancestor = new int[n];
        // 내 자손들
        ArrayList<Integer>[] son = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            son[i] = new ArrayList<>();
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String after = st.nextToken();
            String before = st.nextToken();

            int bi = idx.get(before);
            int ai = idx.get(after);

            son[bi].add(ai);
            ancestor[ai]++;
        }


        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            if (ancestor[i] == 0) {
                start.add(i);
            }
        }

        for (Integer s : start) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(s);
            while (!queue.isEmpty()) {
                Integer from = queue.poll();
                for (Integer to : son[from]) {
                    ancestor[to]--;
                    // 자식
                    if (ancestor[to] == 0) {
                        tree[from].add(to);
                        queue.offer(to);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(start.size()).append("\n");
        for (Integer s : start) {
            sb.append(people[s]).append(" ");
        }
        sb.append("\n");

        for (int i = 0; i < n; i++) {
            tree[i].sort((o1, o2) -> o1 - o2);
            sb.append(people[i]).append(" ");
            sb.append(tree[i].size()).append(" ");
            for (Integer t : tree[i]) {
                sb.append(people[t]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

}