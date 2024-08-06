import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        ArrayList<Integer[]>[] route = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            route[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            route[a].add(new Integer[] {b, c});
            route[b].add(new Integer[] {a, c});
        }

        // 민준이 최단 경로
        int[] minjun = new int[v + 1];
        Arrays.fill(minjun, Integer.MAX_VALUE);
        minjun[1] = 0;
        PriorityQueue<Integer[]> mq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        mq.offer(new Integer[] {1, 0});
        while (!mq.isEmpty()) {
            Integer[] from = mq.poll();
            for (Integer[] to : route[from[0]]) {
                int newlen = from[1] + to[1];
                if (minjun[to[0]] > newlen) {
                    minjun[to[0]] = newlen;
                    mq.offer(new Integer[] {to[0], newlen});
                }
            }
        }

        // 건우 최단 경로
        int[] gunwoo = new int[v + 1];
        gunwoo[p] = 0;
        Arrays.fill(gunwoo, Integer.MAX_VALUE);
        PriorityQueue<Integer[]> gq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        gq.offer(new Integer[] {p, 0});
        while (!gq.isEmpty()) {
            Integer[] from = gq.poll();
            for (Integer[] to : route[from[0]]) {
                int newlen = from[1] + to[1];
                if (gunwoo[to[0]] > newlen) {
                    gunwoo[to[0]] = newlen;
                    gq.offer(new Integer[] {to[0], newlen});
                }
            }
        }

        // 민준이 최단거리
        int minlen = minjun[v];
        // 건우 거치기
        int saveGunwoo = minjun[p] + gunwoo[v];
        if (minlen == saveGunwoo) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }

}