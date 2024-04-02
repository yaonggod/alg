import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        for (int b : B) {
            pq.offer(b);
        }
        
        int count = 0;
        int target = pq.poll();
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] < target) {
                count++;
                if (count != A.length) {
                    target = pq.poll();
                }
            } 
        }
        return count;
    }
}