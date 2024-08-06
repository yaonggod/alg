import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 정방향 고려 ㄴㄴ
        // 역방향 선분들을 그어서 겹치는 부분만 빼고 계산
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if (o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
                return Integer.compare(o1[0], o2[0]);
            }
        });
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (s > e) {
                pq.offer(new Integer[]{e, s});
            }
        }
        long answer = m;
        Integer[] first = pq.poll();
        answer += (first[1] - first[0]) * 2;
        int end = first[1];
        while (!pq.isEmpty()) {
            Integer[] taxi = pq.poll();
            if (taxi[0] >= end) {
                answer += (taxi[1] - taxi[0]) * 2;
                end = taxi[1];
            } else {
                if (taxi[1] > end) {
                    answer += (taxi[1] - end) * 2;
                    end = taxi[1];
                }
            }
        }
        System.out.println(answer);
    }

}