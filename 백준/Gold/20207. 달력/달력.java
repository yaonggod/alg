import java.io.*;
import java.util.*;

public class Main {
    static int n, s, e;
    static int count = 0;
    // 몇 번째 일정 검사하고 있는지
    static int idx = 0;
    static int ans = 0;
    static int[][] schedule;
    // pq에서 진행되고 있는 일정들 정리하고
    // 이미 끝난 일정을 다 뽑아내고 새로운 일정을 추가했을 때 최대 일정 겹치는 수 캡쳐
    // 일정이 다 비워지는 순간 연속된 일정이 끝났음
    static PriorityQueue<Integer> queue = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        schedule = new int[n][2];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(schedule, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        // 첫 번째 일정
        s = schedule[0][0];
        e = schedule[0][1];
        count = 1;

        queue.offer(e);

        for (int i = 1; i < n; i++) {
            // 새로 들어올 일정
            int start = schedule[i][0];
            int end = schedule[i][1];

            // 새로 들어올 일정들과 겹치지 않으면 큐에서 다 삭제
            while (!queue.isEmpty()) {

                // 이전 일정
                int before = queue.peek();

                // 겹침
                if (before >= start) {
                    // 그만 뽑기
                    break;
                }

                // 이전 일정 끝났음
                queue.poll();
            }

            // 이전 일정들 사각형이랑 연결되지 않음
            if (e + 1 < start) {

                // 사각형
                ans += (e - s + 1) * count;

                // 지금 위치에서 다시 시작
                s = start;
                count = 1;

            }
            queue.offer(end);
            e = Math.max(e, end);

            // 겹치는 일정들의 이 순간의 크기를 캡쳐
            count = Math.max(count, queue.size());
        }

        // 마지막 사각형
        ans += (e - s + 1) * count;
        System.out.println(ans);
    }
}