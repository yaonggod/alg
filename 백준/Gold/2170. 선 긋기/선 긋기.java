import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
                return Integer.compare(o1[0], o2[0]);
            }
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.offer(new Integer[] {x, y});
        }

        Integer[] first = pq.poll();
        int s = first[0];
        int e = first[1];
        long answer = e - s;

        while (!pq.isEmpty()) {
            Integer[] dot = pq.poll();

            // 겹치지않는다
            if (e <= dot[0]) {
                answer += (dot[1] - dot[0]);
                e = dot[1];
            }
            // 겹친다
            else {
                if (e < dot[1]) {
                    answer += (dot[1] - e);
                    e = dot[1];
                }
            }
        }
        System.out.println(answer);
    }

}