import java.util.*;

class Solution {
    static class Target implements Comparable<Target>{
        int start;
        int end;
        public Target(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Target target) {
            if (this.start > target.start) {
                return 1;
            } else if (this.start < target.start) {
                return -1;
            } else {
                if (this.end > target.end) {
                    return 1;
                }
            }
            return -1;
        }
    }
    public int solution(int[][] targets) {
        PriorityQueue<Target> pq = new PriorityQueue<>();
        for (int i = 0; i < targets.length; i++) {
            pq.offer(new Target(targets[i][0], targets[i][1]));
        }
        int missile = 0;
        int shoot = 0;
        while (!pq.isEmpty()) {
            Target t = pq.poll();
            if (shoot > t.start) {
                shoot = Math.min(shoot, t.end);
            } else {
                shoot = t.end;
                missile++;
            }
        }
        return missile;
    }
}