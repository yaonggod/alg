import java.util.*;

class Solution {
    static int[] partSum;
    static int n;
    
    public int solution(int[] elements) {
        Set<Integer> sums = new HashSet<>();
        
        n = elements.length;
        // 부분합
        partSum = new int[n];
        partSum[0] = elements[0];
        for (int i = 1; i < n; i++) {
            partSum[i] = partSum[i - 1] + elements[i];
        }
        // 길이가 1부터 n까지 나옴
        for (int l = 1; l <= n; l++) {
            // 다 더하는 경우
            if (l == n) {
                sums.add(partSum[n - 1]);
            // 부분수열이 전체 수열보다 짧음
            } else {
                for (int i = 0; i < n; i++) {
                    int start = i;
                    int end = i + l;
                    sums.add(calc(start, end));
                }
            }
        }
        return sums.size();
    }
    
    public int calc(int start, int end) {
        int result = 0;
        // 2 ~ 6 == 2, 3, 4, 5 == 2, 3, 4, 0 == 4 - 1 + 0
        if (end > n) {
            result = partSum[n - 1] + partSum[end - n - 1];
        // 2 ~ 5 == 2, 3, 4 == 4 - 1
        } else {
            result = partSum[end - 1];
        }
        // 0부터 시작할 경우에는 앞에거 뺄 게 없음
        if (start != 0) result -= partSum[start - 1];
        return result;
    }
}