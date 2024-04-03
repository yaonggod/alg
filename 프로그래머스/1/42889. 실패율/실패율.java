import java.util.*;

class Solution {
    class FailRate implements Comparable<FailRate> {
        int stage;
        double rate;
        
        FailRate(int stage, double rate) {
            this.stage = stage;
            this.rate = rate;
        }
        
        @Override
        public int compareTo(FailRate fr) {
            if (this.rate > fr.rate) return -1;
            if (this.rate == fr.rate && this.stage < fr.stage) return -1;
            return 1;
        }
    }
    
    
    public int[] solution(int N, int[] stages) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int s : stages) {
            pq.offer(s);
        }
        
        // 지금 도전하고 있는 사람
        double current = stages.length;
        
        PriorityQueue<FailRate> frpq = new PriorityQueue<FailRate>();
        
        for (int i = 1; i <= N; i++) {
            int fail = 0;
            while (!pq.isEmpty()) {
                if (pq.peek() == i) {
                    pq.poll();
                    fail++;
                } else {
                    break;
                }
            }
            if (current > 0) {
                frpq.offer(new FailRate(i, fail / current));
            } else {
                frpq.offer(new FailRate(i, 0.0));
            }
            current -= fail;
        }
        
        int[] result = new int[N];
        int index = 0;
        while (!frpq.isEmpty()) {
            FailRate fr = frpq.poll();
            result[index++] = fr.stage;
        }
        
        return result;
    }
}