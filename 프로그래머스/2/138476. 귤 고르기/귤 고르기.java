import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> tan = new HashMap<>();
        for (int i = 0; i < tangerine.length; i++) {
            if (!tan.containsKey(tangerine[i])) {
                tan.put(tangerine[i], 1);
            } else {
                tan.put(tangerine[i], tan.get(tangerine[i]) + 1);
            }
        }
        PriorityQueue<Integer[]> pq = new PriorityQueue(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] t1, Integer[] t2) {
                return t2[1] - t1[1];
            }
        });
        for (int t : tan.keySet()) {
            pq.offer(new Integer[] {t, tan.get(t)});
        }
        
        int count = 0;
        while (k > 0) {
            k -= pq.poll()[1];
            count++;
        }
        return count;
    }
}