import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if (o1[0] < o2[0]) return -1;
                if (o1[0] == o2[0] && o1[1] < o2[1]) return -1;
                return 1;
            }
        });
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new Integer[] {start, end});
        }

        // 강의실마다 끝나는 시각
        PriorityQueue<Integer> room = new PriorityQueue<>();
        while (!pq.isEmpty()) {
            Integer[] course = pq.poll();
            if (room.isEmpty()) {
                room.offer(course[1]);
            } else {
                // 가장 먼저 끝나는 강의실 꺼내서
                int first = room.peek();
                // 이어서 할 수 있는지 알아보기
                if (first <= course[0]) {
                    room.poll();
                }
                room.offer(course[1]);
            }
        }

        System.out.println(room.size());
    }


}