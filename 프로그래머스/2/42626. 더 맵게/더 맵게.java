import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.offer(s);
        }
        int result = 0;
        while (true) {
            int first = pq.poll();
            // 한개밖에 없는데
            if (pq.isEmpty()) {
                // 그마저도 작음
                if (first < K) {
                    result = -1;
                }
                break;
            }
            int second = pq.poll();
            // 다했다
            if (first >= K) break;
            // 작업
            pq.offer(first + second * 2);
            result++;
        }
        return result;
    }
}