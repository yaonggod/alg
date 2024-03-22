import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        for (int i = 0; i < works.length; i++) {
            pq.offer(works[i]);
        }
        boolean done = false;
        for (int i = 0; i < n; i++) {
            int w = pq.poll();
            if (w == 0) {
                done = true;
                break;
            }
            pq.offer(w - 1);
        }
        
        if (done) return 0;
        
        long answer = 0;
        while (!pq.isEmpty()) {
            int w = pq.poll();
            answer += w * w;
        }
        return answer;
    }
}