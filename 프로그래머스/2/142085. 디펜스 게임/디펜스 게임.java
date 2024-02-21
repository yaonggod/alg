import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        if (k >= enemy.length) return enemy.length;
        PriorityQueue<Integer> mjk = new PriorityQueue<>((a, b) -> b - a);
        
        int answer = 0;
        for (int i = 0; i < enemy.length; i++) {
            mjk.offer(enemy[i]);
            n -= enemy[i];
            if (n < 0) {
                if (k > 0) {
                    int before = mjk.poll();
                    n += before;
                    k--;
                } else {
                    return i;
                }
            }
        }
        return enemy.length;
    }

}