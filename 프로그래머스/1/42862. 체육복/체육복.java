import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);
        int[] count = new int[n + 2];
        Arrays.fill(count, 1);
        count[0] = 0;
        count[n + 1] = 0;
        // 잃어버림
        for (int l : lost) {
            count[l] -= 1;
        }
        // 여분
        for (int r : reserve) {
            count[r] += 1;
        }
        int result = n - lost.length;
        for (int l : lost) {
            // 잃어버렸는데 여분 있음
            if (count[l] == 1) {
                result++;
            // 앞에 친구가 있음
            } else if (count[l - 1] == 2) {
                count[l - 1] -= 1;
                result++;
            // 뒤에 친구가 있음
            } else if (count[l + 1] == 2) {
                count[l + 1] -= 1;
                result++;
            }
        }
        return result;
    }
}