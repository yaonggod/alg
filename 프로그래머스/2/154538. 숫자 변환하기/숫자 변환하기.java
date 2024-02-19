import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        Map<Integer, Integer> tries = new HashMap<>();
        tries.put(x, 0);
        Queue<Integer[]> route = new LinkedList<>();
        route.offer(new Integer[] {x, 0});
        
        // x -> y는 커지기만함
        while (!route.isEmpty()) {
            Integer[] from = route.poll();
            if (from[0] == y) return from[1];
            if (from[0] + n <= y && !tries.containsKey(from[0] + n)) {
                route.offer(new Integer[] {from[0] + n, from[1] + 1});
                tries.put(from[0] + n, from[1] + 1);
            }
            if (from[0] * 2 <= y && !tries.containsKey(from[0] * 2)) {
                route.offer(new Integer[] {from[0] * 2, from[1] + 1});
                tries.put(from[0] * 2, from[1] + 1);
            } 
            if (from[0] * 3 <= y && !tries.containsKey(from[0] * 3)) {
                route.offer(new Integer[] {from[0] * 3, from[1] + 1});
                tries.put(from[0] * 3, from[1] + 1);
            }
        }
        
        return -1;
    }
}