import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        Arrays.sort(d);
        int index = 0;
        while (index < d.length) {
            if (budget >= d[index]) {
                budget -= d[index];
            } else {
                break;
            }
            index++;
        }
        return index;
    }
}